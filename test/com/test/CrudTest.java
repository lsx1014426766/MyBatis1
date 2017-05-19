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
        System.out.println("������before����");
		
	}
	@Test
	public void testInsert(){
		
		Map<String,Object>userMap= new HashMap<String,Object>();
		userMap.put("name", "����1");
		userMap.put("age", 10);
		list.add(userMap);
		//�����и����⣬��Ϊ�õ�һ��userMap�����������շŽ�����2�������족��
		//��������Ҫ���´���һ���µĶ��󣬸ı�userMap�����������ָ��
		userMap= new HashMap<String,Object>();
		userMap.put("name", "����1");
		userMap.put("age", 12);
		list.add(userMap);
	
		/*User u1=new User();
		u1.setName("����");
		u1.setAge(10);
		User u2=new User();
		u2.setName("����");
		u2.setAge(12);
		list.add(u1);
		list.add(u2);*/
		session.insert("insertbatch", list);
		//ע����Ҫ�ֶ��ύ���񣬷���ִ��db�ύ
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
		 System.out.println("������after����");
	}
    
}
