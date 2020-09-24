package com.ibm.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ibm.entity.Employee;

/**
 * 本地 SQL检索:来完善HQL不能涵盖所有的查询特性（比较复杂的查询以及insert、delete和update语句）
 * 
 * @author QiuYangZhang
 *
 */
public class NativeManageEmployee {

	private SessionFactory factory = new Configuration().configure().buildSessionFactory();

	/**
	 * 增加员工信息
	 */
	@Test
	public void insertEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "insert into employee(first_name, last_name, salary) values(?,?,:salary)";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, "z");
		sqlQuery.setParameter(1, "s");
		sqlQuery.setParameter("salary", 40);
		int result = sqlQuery.executeUpdate();
		System.out.println("受影响行数：" + result);
		transaction.commit();
		session.close();
	}
	
	/**
	 * 查询某些字段
	 */
	@Test
	public void searchSomeParameter() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "SELECT first_name, salary FROM EMPLOYEE";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List data = sqlQuery.list();
		for (Object object : data) {
			Map row = (Map) object;
			System.out.print("First Name: " + row.get("first_name")); 
            System.out.println(", Salary: " + row.get("salary"));
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 查询所有
	 */
	@Test
	public void searchAll() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = "SELECT * FROM EMPLOYEE";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Employee.class);
		List employees = sqlQuery.list();
		for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
		transaction.commit();
		session.close();
	}
	
	/**
	 * 删除员工
	 */
	@Test
	public void deleteEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		String sql = " delete from employee where id  = :id";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter("id", 20);
		int result = sqlQuery.executeUpdate();
		System.out.println("受影响行数：" + result);
		transaction.commit();
		session.close();
	}
}
