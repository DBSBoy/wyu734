package com.ibm.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.ibm.entity.Employee03;

/**
 * 注解方式验证
 * @author QiuYangZhang
 *
 */
public class AnnotationsManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) {

		try {
			factory = new AnnotationConfiguration().configure().addAnnotatedClass(Employee03.class).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		AnnotationsManageEmployee ME = new AnnotationsManageEmployee();

		Integer empID1 = ME.addEmployee("zhang", "san", 1000);
		Integer empID2 = ME.addEmployee("li", "si", 5000);
		Integer empID3 = ME.addEmployee("wang", "wu", 10000);

		ME.listEmployees();

		ME.updateEmployee(empID1, 5000);

		ME.deleteEmployee(empID2);

		ME.listEmployees();
	}

	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee03 employee = new Employee03();
			employee.setFirstName(fname);
			employee.setLastName(lname);
			employee.setSalary(salary);
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

	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee03").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee03 employee = (Employee03) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print("  Last Name: " + employee.getLastName());
				System.out.println("  Salary: " + employee.getSalary());
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

	public void updateEmployee(Integer employeeID, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee03 employee = (Employee03) session.get(Employee03.class, employeeID);
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

	public void deleteEmployee(Integer employeeID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee03 employee = (Employee03) session.get(Employee03.class, employeeID);
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
