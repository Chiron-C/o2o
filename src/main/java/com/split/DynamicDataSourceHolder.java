package com.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {
	private static Logger logger=LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	private static ThreadLocal<String> contextHolder=new ThreadLocal<String>();
	private static final String DB_MASTER="master";
	private static final String DB_SLAVE="slave";
	public static String getDBType() {
		String db=contextHolder.get();
		if(db==null) {
			db=DB_MASTER;
		}
		return db;
	}
	public static void setDBType(String str) {
		logger.debug("used dataSource is:"+str);
		contextHolder.set(str);
	}
	public static void clearDBType() {
		contextHolder.remove();
	}
}
