package com.split;

import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
//攔截器
public class DynamicDataSourceInterceptor implements Interceptor{
	private Logger logger=LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);
	private static final String REGEX=".*insert\\u0020.*|.*update\\u0020.*|.*delete\\u0020.*";//u0020表示空格
	@Override
	@Transactional
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		boolean synchronizationActive=TransactionSynchronizationManager.isActualTransactionActive();//是否是同步事务
		String lookupKey=DynamicDataSourceHolder.DB_MASTER;
		Object[] objects=invocation.getArgs();
		MappedStatement ms=(MappedStatement)objects[0];//使用查询语句的第一个参数
		if(synchronizationActive!=true) {
			//读方法
			if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
				//selectKey 为自增id查询主键（SELECT LAST——INSERT——ID（））方法，使用主库
				if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
					lookupKey=DynamicDataSourceHolder.DB_MASTER;
				}else {
					BoundSql boundSql=ms.getSqlSource().getBoundSql(objects[1]);
					String sql=boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\]t\\n\\r", " ");
					if(sql.matches(REGEX)) {
						lookupKey=DynamicDataSourceHolder.DB_MASTER;
					}else {
						lookupKey=DynamicDataSourceHolder.DB_SLAVE;
					}
				}
			}
		}else {
			lookupKey=DynamicDataSourceHolder.DB_MASTER;
		}
		logger.debug("設置方法[{}]use[{}]Strategy,SqlCommanType[{}]..",ms.getId(),lookupKey,ms.getSqlCommandType().name());
		DynamicDataSourceHolder.setDBType(lookupKey);
		return invocation.proceed();
	}
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		if(target instanceof Executor) {//如果是mybatis的Executor则拦截
			return Plugin.wrap(target, this);
		}else {
			return target;
		}
	}
	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
}
