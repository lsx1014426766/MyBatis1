package com.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.User;

  
public class UserTest   
{  
    public static void main(String[] args)  
    {  
        String resource = "config/myBatis_config.xml";  
    	//String resource = "config/mybatis.xml";  
        Reader reader = null; 
        SqlSession session =null;
        try {  
            reader = Resources.getResourceAsReader(resource);  
            SqlSessionFactory sessionFactory;
			try {
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	            session = sessionFactory.openSession();  

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            //List<User> users = session.selectList("com.bean.User.selectAll");
			List<User> users = session.selectList("selectAll");
              
            for(User u:users)  
            {  
                System.out.println(u.getName());  
            }  
              
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try {  
            	reader.close();  
                session.close();
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  
