package com.lichunliang.huoyunwuliu.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ━━━━━━━━━神兽出没━━━━━━━━━
 * <p>
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ━━━━━━━━━感觉萌萌哒━━━━━━━━━
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.druid.initialSize}")
    private Integer initialSize;
    @Value("${spring.druid.minIdle}")
    private Integer minIdle;
    @Value("${spring.druid.maxActive}")
    private Integer maxActive;
    @Value("${spring.druid.maxWait}")
    private Long maxWait;
    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${spring.druid.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${spring.druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.druid.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${spring.druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${spring.druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.druid.filters}")
    private String filters;
    @Value("${spring.druid.connectionProperties}")
    private Properties connectionProperties;

    /**
     * 配置数据源文件
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        dataSource.setFilters(filters);使用log4j依赖
        dataSource.setConnectProperties(connectionProperties);
        //添加wallFILTER
        List<Filter> filters = new ArrayList<>();
        filters.add(wallFilter());
        //设置代理过滤器
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    /**
     * wall过滤器 , 执行删除功能时若mapper中<delete/>语句有多条，则删除失败，在此设置
     * 允许执行删除功能多条语句setMultiStatementAllow=true，并将过滤器加入到datasource
     *
     * @return
     */
    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        WallConfig config = new WallConfig();
        //设置允许多条sql语句执行
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }

    /**
     * 配置StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");  //设置ip白名单
        servletRegistrationBean.addInitParameter("deny", "192.168.189.132");  //设置ip黑名单
        servletRegistrationBean.addInitParameter("loginUsername", "admin");//设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("resetEnable", "false");//是否可以重置数据
        return servletRegistrationBean;
    }

    /**
     * 配置WebStatFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*"); //设置过滤器过滤路径
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
