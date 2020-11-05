package common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	public static SqlSession getSqlSession() {
		// SqlSession <- SqlSessionFactory <- SqlSessionFactoryBuilder
		//		openSession()		build(InputStream)
		SqlSession session = null;
		try {
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			
			InputStream inputStream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSessionFactory ssf = ssfb.build(inputStream);
			session = ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
}
