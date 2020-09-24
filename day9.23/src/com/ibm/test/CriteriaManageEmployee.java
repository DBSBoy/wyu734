package com.ibm.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.ibm.entity.Employee;

/**
 * QBC 检索:通过使用 Hibernate提供的Criteria API来查询对象
 * 
 * @author QiuYangZhang
 *
 */
public class CriteriaManageEmployee {

	private SessionFactory factory = new Configuration().configure().buildSessionFactory();

	/**
	 * 最简单查询，查询所有员工记录
	 */
	@Test
	public void findAll() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employees = criteria.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 添加条件的查询
	 */
	@Test
	public void searchWithRestrictions() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
//		criteria.add(Restrictions.eq("salary",3000));//salary等于3000
//		criteria.add(Restrictions.gt("salary", 3000));//salary大于3000
//		criteria.add(Restrictions.ge("salary", 3000));//salary大于等于3000
//		criteria.add(Restrictions.lt("salary", 3000));//salary小于3000
//		criteria.add(Restrictions.le("salary", 3000));//salary小于等于3000
//		criteria.add(Restrictions.like("firstName", "%an%"));//firstName中有"an"
		criteria.add(Restrictions.between("salary", 3000, 6666));//3000到6666，包含3000和6666
		List<Employee> employees = criteria.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 添加条件的查询(and和or)
	 */
	@Test
	public void searchWithRestrictions02() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		//and使用Conjunction表示，Conjunction本身就是一个Criterion对象
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.between("salary", 3000, 6666));
		conjunction.add(Restrictions.like("firstName", "%an%"));
		System.out.println(conjunction);
		
		//or使用Disjunction表示，Disjunction本身就是一个Criterion对象
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("firstName", "%an%"));
        disjunction.add(Restrictions.like("lastName", "%a%"));
        
        criteria.add(conjunction);
        criteria.add(disjunction);
		List<Employee> employees = criteria.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 分页查询
	 */
	@Test
	public void searchWithPagination() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		List<Employee> employees = criteria.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 查询带有排序
	 */
	@Test
	public void searchWithSorting() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.gt("salary", 2000));
//		criteria.addOrder(Order.asc("salary"));//按照salary升序排序
		criteria.addOrder(Order.desc("salary"));//按照salary降序排序
		List<Employee> employees = criteria.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 统计查询
	 * 统计查询用Projection来表示，可以由Projections的静态方法得到
	 */
	@Test
	public void searchWithAggregations() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
//		criteria.setProjection(Projections.rowCount());//获取总行数
//		criteria.setProjection(Projections.avg("salary"));//获取salary的平均数
//		criteria.setProjection(Projections.countDistinct("firstName"));//去重计数
//		criteria.setProjection(Projections.max("salary"));//获取salary的最大值
//		criteria.setProjection(Projections.min("salary"));//获取salary的最小值
		criteria.setProjection(Projections.sum("salary"));//求salary的和
		Object obj = criteria.uniqueResult();
		System.out.println(obj);
		transaction.commit();
		session.close();
	}
}
