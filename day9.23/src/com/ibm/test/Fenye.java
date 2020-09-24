package com.ibm.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.ibm.entity.Employee;
import com.ibm.entity.Student;

public class Fenye {
	
	private SessionFactory factory = new Configuration().configure().buildSessionFactory();

	@Test
	public void insertStudnet() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "insert into student(name, sex, age) values(?,?,:age)";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, "Mark");
		sqlQuery.setParameter(1, "man");
		sqlQuery.setParameter("age", 20);
		int result = sqlQuery.executeUpdate();
		System.out.println("受影响行数：" + result);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void searchWithPagination() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Student.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		List<Student> stu = criteria.list();
		for (Student student : stu) {
			System.out.print("name: " + student.getName());
			System.out.print("  age: " + student.getAge());
			System.out.println("  sex: " + student.getSex());
		}
		transaction.commit();
		session.close();
	}
	//排序
	@Test
	public void searchWithSorting() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.gt("age", 18));
//		criteria.addOrder(Order.asc("age"));//按照salary升序排序
		criteria.addOrder(Order.desc("age"));//按照salary降序排序
		List<Student> students = criteria.list();
		System.out.println("降序排序：");
		for (Student stu : students) {
			System.out.print(" Name: " + stu.getName());
			System.out.print(" sex: " + stu.getSex());
			System.out.println("  age: " +stu.getAge());
		}
		System.out.println("----------------------------");
		Criteria criteria2 = session.createCriteria(Student.class);
		criteria2.add(Restrictions.gt("age", 20));
		criteria2.addOrder(Order.asc("age"));
		System.out.println("升序排序：");
		List<Student> student = criteria2.list();
		for (Student stu : student) {
			System.out.print(" Name: " + stu.getName());
			System.out.print(" sex: " + stu.getSex());
			System.out.println("  age: " +stu.getAge());
		}
		transaction.commit();
		session.close();
	}
	@Test
	public void searchWithAggregations() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Student.class);
//		criteria.setProjection(Projections.rowCount());//获取总行数
//		criteria.setProjection(Projections.avg("salary"));//获取salary的平均数
//		criteria.setProjection(Projections.countDistinct("firstName"));//去重计数
//		criteria.setProjection(Projections.max("salary"));//获取salary的最大值
//		criteria.setProjection(Projections.min("salary"));//获取salary的最小值
		criteria.setProjection(Projections.sum("grade"));//求salary的和
		Object obj = criteria.uniqueResult();
		System.out.println("学生表成绩汇总："+obj);
		transaction.commit();
		session.close();
}
}