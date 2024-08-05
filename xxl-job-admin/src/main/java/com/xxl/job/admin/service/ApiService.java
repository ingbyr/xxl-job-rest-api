package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.dao.XxlJobApiDao;
import com.xxl.job.admin.dao.XxlJobLogDao;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ingby
 */
@Service
public class ApiService {

    @Resource
    private XxlJobApiDao xxlJobApiDao;

    @Resource
    private XxlJobLogDao xxlJobLogDao;

    public ReturnT<List<XxlJobInfo>> findJobInfo(List<String> idList) {
        List<XxlJobInfo> xxlJobInfos = xxlJobApiDao.selectJobInfo(idList);
        return new ReturnT<>(xxlJobInfos);
    }

    public ReturnT<XxlJobPageDataDTO<List<XxlJobLog>>> findJobLogs(int start, int length, int jobGroup, int jobId,
                                                                   Date triggerTimeStart, Date triggerTimeEnd,
                                                                   int logStatus) {
        // page query
        List<XxlJobLog> list = xxlJobLogDao.pageList(start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, logStatus);
        int total = xxlJobLogDao.pageListCount(start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, logStatus);

        // Package
        XxlJobPageDataDTO<List<XxlJobLog>> dto = new XxlJobPageDataDTO<>();
        dto.setTotal(total);
        dto.setData(list);
        return new ReturnT<>(dto);
    }
}
