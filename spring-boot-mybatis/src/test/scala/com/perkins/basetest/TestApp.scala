package com.perkins.basetest

import com.perkins.mapper.UserMapper
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.junit.Test

object TestApp {
  def main(args: Array[String]): Unit = {
    testMybatis()
  }

  @Test
  def testMybatis(): Unit = {
    val resource = "mybatis/mybatis.cfg.xml"
    val inputStream = Resources.getResourceAsStream(resource)
    val sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream)
    print(sqlSessionFactory)
    val session = sqlSessionFactory.openSession()
    val userMapper = session.getMapper(classOf[UserMapper])
    userMapper.getUser.forEach(println)
  }

}
