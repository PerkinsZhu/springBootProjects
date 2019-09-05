package com.perkins

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan(basePackages = Array("com.perkins.mapper"))
class ScalaSpringBootMybatisApplication {

}

object ScalaSpringBootMybatisApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringBootMybatisApplication], args: _*)
  }
}