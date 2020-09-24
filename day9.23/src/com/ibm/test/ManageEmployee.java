package com.ibm.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.entity.Certificate;
import com.ibm.entity.Employee;

public class ManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageEmployee manageEmployee = new ManageEmployee();

		/**
		 * 第一个员工的证书
		 */
		HashSet set1 = new HashSet();
		set1.add(new Certificate("MCA"));
		set1.add(new Certificate("MBA"));
		set1.add(new Certificate("PMP"));

		/**
		 * 第二个员工的证书
		 */
		HashSet set2 = new HashSet();
		set2.add(new Certificate("BCA"));
		set2.add(new Certificate("BA"));

		/**
		 * 第三个员工的证书
		 */
		HashSet set3 = new HashSet();
		set3.add(new Certificate("A"));

		/* 添加一些员工 */
		Integer empID1 = manageEmployee.addEmployee("Zara", "Ali", 1000, set1);
		Integer empID2 = manageEmployee.addEmployee("Daisy", "Das", 5000, set2);
		Integer empID3 = manageEmployee.addEmployee("John", "Paul", 10000, set3);

		/* 查询所有员工 */
		manageEmployee.listEmployees();

		/* 修改员工记录 */
		manageEmployee.updateEmployee(empID1, 5000);

		/* 删除员工 */
		manageEmployee.deleteEmployee(empID2);

		/* 重新查寻员工记录 */
		manageEmployee.listEmployees();
	}

	/**
	 * 添加员工
	 * 
	 * @param fname
	 * @param lname
	 * @param salary
	 * @return
	 */
	public Integer addEmployee(String fname, String lname, int salary, Set certificates) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(fname, lname, salary);
			employee.setCertificates(certificates);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/**
	 * 修改员工信息
	 * 
	 * @param EmployeeID
	 * @param salary
	 */
	public void updateEmployee(Integer employeeID, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, employeeID);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询所有员工
	 */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print("  Last Name: " + employee.getLastName());
				System.out.println("  Salary: " + employee.getSalary());
				Set certificates = employee.getCertificates();
				for (Iterator iterator2 = certificates.iterator(); iterator2.hasNext();) {
					Certificate certName = (Certificate) iterator2.next();
					System.out.println("Certificate: " + certName.getName());
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 删除员工
	 * 
	 * @param EmployeeID
	 */
	public void deleteEmployee(Integer employeeID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, employeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
