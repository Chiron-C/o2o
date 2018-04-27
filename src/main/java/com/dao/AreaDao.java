package com.dao;

import java.util.List;

import com.domain.Area;

public interface AreaDao {
/**
 * 返回区域查询列表
 * @return
 */
	//@Select("SELECT * FROM blog WHERE id = #{id}")注解的方式
	List<Area> queryArea();
	
}
