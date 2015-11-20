package com.forestry.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.MonitorLog;
import com.forestry.service.sys.MonitorLogService;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Controller
@RequestMapping("/sys/monitorlog")
public class MonitorLogController extends ForestryBaseController<MonitorLog> {

	@Resource
	private MonitorLogService monitorLogService;

	@RequestMapping("/getSensorMonitorLog")
	public void getSensorMonitorLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<MonitorLog> sensorMonitorLogList = monitorLogService.querySensorMonitorLog();
		writeJSON(response, sensorMonitorLogList);
	}

}
