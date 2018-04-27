package com.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.Area;
import com.service.AreaService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/superadmin")//创建路由
public class AreaController {
	Logger logger=(Logger) LoggerFactory.getLogger(AreaController.class);
	@Autowired
	@Qualifier("AreaService")
	private AreaService areaService;
	@RequestMapping(value="/listarea",method=RequestMethod.GET)//ctrol+shift+x变大写，ctrol+shift+y变小写
	@ResponseBody//将modelMap自动转化为json对象
	private Map<String,Object> listArea(){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		List<Area> list=new ArrayList<Area>();
		try {
			list=areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		}catch(Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			logger.error(e.getMessage());
		}
		return modelMap;
	}
	
}
