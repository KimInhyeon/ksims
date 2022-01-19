package com.ksinfo.common.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlSessionFactoryService
{
	@Autowired
    SqlSessionTemplate sqlSessionTemplate;
    
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }
    
    
    //////////////////////////
    
    
    private static SqlSessionFactory sessionFactory;
    
    static
    {
      try
      {
        InputStream in = Resources.getResourceAsStream("/SqlMapConfig.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(in);
      }
      catch (Exception e)
      {
        System.err.println("sql error : " + e.getMessage());
      }
    }
    
    public static SqlSessionFactory getsqlFactoryService()
    {
      return sessionFactory;
    }
}
