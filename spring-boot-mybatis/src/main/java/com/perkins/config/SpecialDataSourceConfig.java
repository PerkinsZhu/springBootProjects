package com.perkins.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 这里的配置是指定这些包下的mapper使用这个数据源
@MapperScan(basePackages = "com.perkins.mapper2", sqlSessionTemplateRef = "specialSqlSessionTemplate")
public class SpecialDataSourceConfig {

    @Value("${mybatis.mapper-locations:}")
    String mapperLocation;

    @Bean(name = "specialDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource setDataSource() {
        return DataSourceBuilder.create().build();
    }

/*    @Bean(name = "specialTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("specialDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "specialSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("specialDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return bean.getObject();
    }

    @Bean(name = "specialSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("specialSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}