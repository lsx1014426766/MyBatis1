package com.test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bean.User;

public class CrudTest {
	public List<Object> list=null;
	public String resource = "config/myBatis_config.xml";  
    public Reader reader = null;  
    public SqlSession session=null;
    @Before
	public void before(){
		list=new ArrayList<Object>();
        try {  
            reader = Resources.getResourceAsReader(resource);  
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);  
            session = sessionFactory.openSession();  
            //reader.close();  
            //session.close();  
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();  
        } 
        System.out.println("執行了before方法");
		
	}
	@Test
	public void testInsert(){
		
		Map<String,Object>userMap= new HashMap<String,Object>();
		userMap.put("name", "张亮1");
		userMap.put("age", 10);
		list.add(userMap);
		//这里有个问题，因为用的一个userMap对象，所以最终放进入了2个“天天”。
		//所以这里要重新创建一个新的对象，改变userMap这个变量名的指向
		userMap= new HashMap<String,Object>();
		userMap.put("name", "天天1");
		userMap.put("age", 12);
		list.add(userMap);
	
		/*User u1=new User();
		u1.setName("张亮");
		u1.setAge(10);
		User u2=new User();
		u2.setName("天天");
		u2.setAge(12);
		list.add(u1);
		list.add(u2);*/
		session.insert("insertbatch", list);
		//注意需要手动提交事务，否则不执行db提交
		session.commit();
	}
	@After
	public void after(){
		 try {  
             reader.close(); 
             session.close(); 
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
		 System.out.println("執行了after方法");
	}
    
}
