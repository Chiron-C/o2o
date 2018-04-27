package com.Service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.BaseTest;
import com.domain.Area;
import com.service.AreaService;

public class ServiceTest extends BaseTest {
	@Autowired//自动导入
	private AreaService areaService;//会把实现类自动注入到AreaService中
	@Test
	public void getAreaList(){
		List<Area> list=areaService.getAreaList();
//		assertEquals("理工", list.get(0).getAreaName());
		System.out.println(list.size());
	}
}
