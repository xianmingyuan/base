package com.example.base.log.sql;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
        , @Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
public class MybatisSQLPerformanceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (!(target instanceof RoutingStatementHandler)) {
            return invocation.proceed();
        }
        RoutingStatementHandler handler = (RoutingStatementHandler) target;
        String sql = handler.getBoundSql().getSql();
        Object args = handler.getBoundSql().getParameterObject();
        log.info("SQL = [{}], args = [{}]", sql, args);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
