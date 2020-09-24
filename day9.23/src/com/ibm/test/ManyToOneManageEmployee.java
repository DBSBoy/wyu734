package com.ibm.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.entity.Address;
import com.ibm.entity.Employee02;

/**
 * 多对一关联关系
 * 
 * @author QiuYangZhang
 *
 */
public class ManyToOneManageEmployee {

	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManyToOneManageEmployee manageEmployee = new ManyToOneManageEmployee();

		Address address = manageEmployee.addAddress("Kondapur", "Hyderabad", "AP", "532");

		Integer empID1 = manageEmployee.addEmployee("Manoj", "Kumar", 4000, address);

		Integer empID2 = manageEmployee.addEmployee("Dilip", "Kumar", 3000, address);

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
	 * 添加一个地址
	 * @param street
	 * @param city
	 * @param state
	 * @param zipcode
	 * @return
	 */
	public Address addAddress(String street, String city, String state, String zipcode) {
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer addressID = null;
	      Address address = null;
	      
	      try {
	         tx = session.beginTransaction();
	         address = new Address(street, city, state, zipcode);
	         addressID = (Integer) session.save(address); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return address;
	   }

	/**
	 * 添加员工
	 * 
	 * @param fname
	 * @param lname
	 * @param salary
	 * @return
	 */
	public Integer addEmployee(String fname, String lname, int salary, Address address){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Employee02 employee = new Employee02(fname, lname, salary, address);
	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
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
			Employee02 employee = (Employee02) session.get(Employee02.class, employeeID);
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
			List employees = session.createQuery("FROM Employee02").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee02 employee = (Employee02) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print("  Last Name: " + employee.getLastName());
				System.out.println("  Salary: " + employee.getSalary());
				Address add = employee.getAddress();
	            System.out.println("Address ");
	            System.out.println("\tStreet: " +  add.getStreet());
	            System.out.println("\tCity: " + add.getCity());
	            System.out.println("\tState: " + add.getState());
	            System.out.println("\tZipcode: " + add.getZipcode());
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
			Employee02 employee = (Employee02) session.get(Employee02.class, employeeID);
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
