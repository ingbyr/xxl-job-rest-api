package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ingby
 */
@Mapper
public interface XxlJobApiDao {

	List<XxlJobInfo> selectJobInfo(@Param("idList") List<String> idList);

}
