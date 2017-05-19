package com.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.User;


public class TestMyBatis {
	public static void main(String[] args) throws Exception {
		String res = "config/myBatis_config.xml";
	    Reader reader = 
	    	Resources.getResourceAsReader(res);
	    SqlSessionFactory sf = 
	    	new SqlSessionFactoryBuilder().build(reader);
	    SqlSession session = sf.openSession();
	    
	    //删除
//	    int i = session.delete("deleteUser",6);
//	    System.out.println(i);
	    //查找对应id的对象
//	    User u = (User)session.selectOne("selectUser",3);
//	    System.out.println(u.getName());
//	    
	    //查找所有的对象
		List<User> list =
			session.selectList("selectAll");
		for(User u:list){
			System.out.println(u.getName());
		}
	    
	    //添加
//	    User u = new User();
//	    u.setId(4);
//	    u.setName("guojing2");
//	    u.setAge(24);
//	    int i = session.insert("dao.insertUser",u);
//	    System.out.println(i);
	    
	    //修改
//	    User u = new User();
//	    u.setId(3);
//	    u.setName("郭靖");
//	    u.setAge(25);
//	    int i = session.update("updateUser",u);
//		System.out.println(i);
	    session.commit();
		session.close();
	}
}
