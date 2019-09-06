package com.perkins.config;

import com.perkins.aop.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = {"com.perkins.mapper3"}) // 扫描DAO
public class DynamicDataSourceConfig {
    @Value("${mybatis.mapper-locations:}")
    String mapperLocation;

    @Autowired
    DataSource specialDataSource;
    @Autowired
    DataSource baseDataSource;
/*

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.db4")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }
*/

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", baseDataSource);
        dataSourceMap.put("slave", specialDataSource);
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(baseDataSource);
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.perkins.beans");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperLocation));    // 扫描映射文件
        return sessionFactory;
    }

/*    @Bean(name = "dynaminTransactionManager")
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/
}