package com.perkins.config

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.{Bean, Configuration, Primary}

@Configuration
class DataSourceConfig {

  /*
    @Bean
    @Qualifier(value = "myagenDataSource") //spring装配bean的唯一标识
    @ConfigurationProperties(prefix = "spring.datasource") //application.properties配置文件中该数据源的配置前缀
    def myagenDataSource(): DataSource = {
      return DataSourceBuilder.create.build
    }
  */

/*
  @Primary //配置该数据源为主数据源
  @Bean
  @Qualifier(value = "orderDiscountDataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  def orderDiscountDataSource(): DataSource = {
    return DataSourceBuilder.create.build
  }*/
}