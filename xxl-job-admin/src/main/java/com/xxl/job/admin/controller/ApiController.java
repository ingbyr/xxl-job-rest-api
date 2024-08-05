package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.service.ApiService;
import com.xxl.job.admin.service.XxlJobPageDataDTO;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.DateUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ingby
 */
@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Resource
    private ApiService apiService;

    @Resource
    private XxlJobService xxlJobService;

    @Resource
    private JobInfoController jobInfoController;

    @RequestMapping("/job-info/list")
    public ReturnT<List<XxlJobInfo>> findJobInfo(@RequestBody List<String> idList) {
        return apiService.findJobInfo(idList);
    }

    @RequestMapping("/job-info/add")
    public ReturnT<String> add(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobService.add(jobInfo);
    }

    @RequestMapping("/job-info/update")
    public ReturnT<String> update(@RequestBody XxlJobInfo jobInfo) {
        return xxlJobService.update(jobInfo);
    }

    @RequestMapping("/job-info/remove")
    public ReturnT<String> remove(int id) {
        return xxlJobService.remove(id);
    }

    @RequestMapping("/job-info/stop")
    public ReturnT<String> pause(int id) {
        return xxlJobService.stop(id);
    }

    @RequestMapping("/job-info/start")
    public ReturnT<String> start(int id) {
        return xxlJobService.start(id);
    }

    @RequestMapping("/job-info/trigger")
    public ReturnT<String> triggerJob(int id, String executorParam, String addressList) {
        return jobInfoController.triggerJob(id, executorParam, addressList);
    }

    @RequestMapping("/job-info/next-trigger-time")
    public ReturnT<List<String>> nextTriggerTime(String scheduleConf) {
        return jobInfoController.nextTriggerTime(scheduleConf);
    }

    @RequestMapping("/job-log/page")
    public ReturnT<XxlJobPageDataDTO<List<XxlJobLog>>> findJobLogs(@RequestParam(required = false, defaultValue = "0") int start,
                                                                   @RequestParam(required = false, defaultValue = "10") int length,
                                                                   int jobGroup, int jobId,
                                                                   String triggerTimeStart, String triggerTimeEnd,
                                                                   int logStatus) {
        return apiService.findJobLogs(start, length, jobGroup, jobId,
                DateUtil.parseDateTime(triggerTimeStart), DateUtil.parseDateTime(triggerTimeEnd),
                logStatus);
    }

}
