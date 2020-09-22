package cn.wyu.Student;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.wyu.Student.Dao.Studao;
import cn.wyu.Student.model.student;


/**
 * 
 *
 */
public class App 
{
	

	@Test
		public void find() throws Exception {

		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sqlSessionFactory.openSession();
		Studao sdao = session.getMapper(Studao.class);
		System.out.println("根据特定id查找：");
		student stu = sdao.findstuById(2);
		System.out.println(stu.toString());
		System.out.println("---------------------");
		List<student>list=sdao.findAllStus();
		System.out.println("查找所有并遍历：");
		for(student su:list) {
			System.out.println(su);
		}
		System.out.println("-----------------------");
	}
	@Test
	public void insert() throws Exception {

		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sqlSessionFactory.openSession();
		Studao sdao = session.getMapper(Studao.class);
		System.out.println("没插入前，数据库如下：");
		List<student>list=sdao.findAllStus();
		
		for(student stu1:list) {
			System.out.println(stu1);
		}
		student stu=new student();
		stu.setId(8);
		stu.setName("Tom");
		stu.setAge(25);
		sdao.insertUser(stu);
		 session.commit();
		System.out.println("插入新学生,数据库返回如下：");
		list=sdao.findAllStus();
		for(student stu1:list) {
			System.out.println(stu1);
		}
		
		
	}
	@Test
	public void delete() throws Exception {
		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sqlSessionFactory.openSession();
		Studao sdao = session.getMapper(Studao.class);
		System.out.println("删除前");
		List<student>list=sdao.findAllStus();
		for(student su:list) {
			System.out.println(su);
		}
		System.out.println("删除后：");
		sdao.deleteStuById(5);
		session.commit();
		list=sdao.findAllStus();
		for(student su:list) {
			System.out.println(su);
		}
	}
	@Test
	public void update() throws Exception {
		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sqlSessionFactory.openSession();
		Studao sdao = session.getMapper(Studao.class);
		System.out.println("2号同学修改前：");
		student su1=new student();
		su1=(student)sdao.findstuById(2);
		System.out.println(su1.toString());
		System.out.println("修改后");
		su1.setAge(25);
		sdao.updateStuName(su1);
		System.out.println(su1.toString());
	}
}
