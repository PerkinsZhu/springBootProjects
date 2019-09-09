package com.perkins.test

import java.io.InputStream

import com.perkins.beans.User
import com.perkins.mapper.UserMapper
import javax.transaction.Transactional
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.{SqlSession, SqlSessionFactoryBuilder}
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.junit.Test

class TestApp {


  @Test
  def testMybatis(): Unit = {

    val session: SqlSession = getSession("db1")
    val userMapper = session.getMapper(classOf[UserMapper])
    println(userMapper.getUser.size())

    val session2: SqlSession = getSession("db2")
    val userMapper2 = session2.getMapper(classOf[UserMapper])
    println(userMapper2.getUser.size())
  }

  @Test
  @Transactional
  def testInsert(): Unit = {
    val session: SqlSession = getSession("db1")
    val userMapper = session.getMapper(classOf[UserMapper])
    val transcation = new JdbcTransactionFactory().newTransaction(session.getConnection)

    try {
      val user = new User()
      user.setUsername("aaa")
      user.setAge(100)
      userMapper.addUser(user)
      session.commit()
      10 / 0
      print(user.getId)
    } catch {
      case e: Exception => {
        session.rollback()
        e.printStackTrace()
      }
    }

  }


  private def getSession(environment: String) = {
    val resource = "mybatis/mybatis.cfg.xml"
    val inputStream = Resources.getResourceAsStream(resource)
    val sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment, null)
    val session = sqlSessionFactory.openSession(false)
    session
  }
}
