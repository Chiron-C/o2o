package com.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
	private static Logger logger=LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	private static ThreadLocal<String> contextHolder=new ThreadLocal<String>();
	public static final String DB_MASTER="master";
	public static final String DB_SLAVE="slave";
	public static String getDBType() {
		String db=contextHolder.get();
		if(db==null) {
			db=DB_MASTER;
		}
		return db;
	}
	/**
	 * 设置线程的dbype
	 * @param str
	 */
	public static void setDBType(String str) {
		logger.debug("使用的数据源是："+str);
		contextHolder.set(str);
	}
	/**
	 *清除线程的dbType
	 */
	public static void clearDBType() {
		contextHolder.remove();
	}
}
