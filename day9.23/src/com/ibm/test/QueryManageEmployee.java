package com.ibm.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ibm.entity.Employee;

/**
 * HQL 检索方式:使用query对象
 * 
 * @author QiuYangZhang
 *
 */
public class QueryManageEmployee {

	private SessionFactory factory = new Configuration().configure().buildSessionFactory();

	/**
	 * 查询所有记录
	 */
	@Test
	public void findAll() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("From Employee");
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 根据全类名查询所有记录（避免类的冲突）
	 */
	@Test
	public void findAll02() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("From com.ibm.entity.Employee");
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * AS子句可用于为HQL查询中的类分配别名
	 */
	@Test
	public void findAll03() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
//		Query query = session.createQuery("From com.ibm.entity.Employee AS E");
		Query query = session.createQuery("From com.ibm.entity.Employee E");//AS关键字是可选的，可以直接在类名之后指定别名
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 查询某些字段(正常情况：返回Object[]，使用不方便)
	 */
	@Test
	public void searchSomeField() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Select E.firstName, E.lastName From Employee E");
		List<Object[]> objectArrayList = query.list();
		for (Object[] objectArray : objectArrayList) {
			System.out.println(Arrays.asList(objectArray));
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 查询某些字段(把查询出来的字段值封装到对象中)
	 * 注意:使用此种方法需声明对应的带参构造器和一个无参构造器
	 */
	@Test
	public void searchSomeField02() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Select new Employee(E.firstName, E.lastName) From Employee E");
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.println("  Last Name: " + employee.getLastName());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 带有条件的查询(where条件)
	 */
	@Test
	public void searchByConditions() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Select new Employee(E.firstName, E.lastName) From Employee E where E.id = :id");
		query.setParameter("id", 7);
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.println("  Last Name: " + employee.getLastName());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 带有排序的查询(order by)
	 */
	@Test
	public void searchByOrderBy() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		//降序排序，若要升序用asc或者不写
		Query query = session.createQuery("Select new Employee(E.firstName, E.lastName, E.salary) From Employee E order by E.salary desc");
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 根据多个字段排序
	 */
	@Test
	public void searchByOrderBy02() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		//降序排序，若要升序用asc或者不写
		Query query = session.createQuery("Select new Employee(E.firstName, E.lastName, E.salary) From Employee E order by E.salary desc, E.firstName asc");
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 *	聚合方法
	 */
	@Test
	public void aggregateMethods() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("SELECT count(distinct E.firstName) FROM Employee E");
		Object result = query.uniqueResult();
		System.out.println(result);
		transaction.commit();
		session.close();
	}
	
	/**
	 * 分组查询(group by)
	 */
	@Test
	public void searchByGroupBy() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Select sum(E.salary), E.firstName FROM Employee E group by E.firstName");
		List<Object[]> objectArrayList = query.list();
		for (Object[] object : objectArrayList) {
			System.out.println(Arrays.asList(object));
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 * 分页查询
	 */
	@Test
	public void searchByPagination() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Employee");
		query.setFirstResult(0);
		query.setMaxResults(2);
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  salary: " + employee.getSalary());
		}
		transaction.commit();
		session.close();
	}
	
	/**
	 *更新员工信息
	 */
	@Test
	public void updateEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("Update Employee set salary = :salary where id = :employee_id");
		query.setParameter("salary", 6666);
		query.setParameter("employee_id", 7);
		int result = query.executeUpdate();
		System.out.println("影响结果：" + result);
		transaction.commit();
		session.close();
	}
	
	/**
	 *新增员工信息
	 */
	@Test
	public void addEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("insert into Employee(firstName, lastName, salary) SELECT firstName, lastName, salary FROM Employee where id = :id");
		query.setParameter("id", 18);
		int result = query.executeUpdate();
		System.out.println("影响结果：" + result);
		transaction.commit();
		session.close();
	}
	
	/**
	 *删除员工信息
	 */
	@Test
	public void deleteEmployee() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete FROM Employee where id = :id");
		query.setParameter("id", 17);
		int result = query.executeUpdate();
		System.out.println("影响结果：" + result);
		transaction.commit();
		session.close();
	}
}
