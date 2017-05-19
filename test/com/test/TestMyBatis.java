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
	    
	    //ɾ��
//	    int i = session.delete("deleteUser",6);
//	    System.out.println(i);
	    //���Ҷ�Ӧid�Ķ���
//	    User u = (User)session.selectOne("selectUser",3);
//	    System.out.println(u.getName());
//	    
	    //�������еĶ���
		List<User> list =
			session.selectList("selectAll");
		for(User u:list){
			System.out.println(u.getName());
		}
	    
	    //���
//	    User u = new User();
//	    u.setId(4);
//	    u.setName("guojing2");
//	    u.setAge(24);
//	    int i = session.insert("dao.insertUser",u);
//	    System.out.println(i);
	    
	    //�޸�
//	    User u = new User();
//	    u.setId(3);
//	    u.setName("����");
//	    u.setAge(25);
//	    int i = session.update("updateUser",u);
//		System.out.println(i);
	    session.commit();
		session.close();
	}
}
