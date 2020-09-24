package com.ibm.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.entity.Department;
import com.ibm.entity.Manager;

/**
 * 一队一映射
 */
public class OneToOne {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("sessionFactory创建失败：" + ex);
			throw new ExceptionInInitializerError(ex);
		}

		OneToOne oneToOne = new OneToOne();

		/**
		 * 增加一个经理
		 */
		Manager manager = oneToOne.addManager("Peter", 36);

		/**
		 * 添加一个部门
		 */
		Integer departmentId = oneToOne.addDepartment("开发部", manager);

		/**
		 * 再增加一个经理
		 */
		Manager manager01 = oneToOne.addManager("Tom", 38);

		/**
		 * 再次添加一个部门
		 */
		Integer departmentId01 = oneToOne.addDepartment("销售部", manager01);

		/**
		 * 查询所有的部门
		 */
		oneToOne.listDepartments();

		/**
		 * 更新部门
		 */
		oneToOne.updateDepartment(departmentId, "技术部");

		/**
		 * 查询更新过后的部门
		 */
		oneToOne.listDepartments();

	}

	/**
	 * 增加一个经理
	 * 
	 * @param name
	 * @param age
	 * @return
	 */
	public Manager addManager(String name, int age) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer manageId = null;
		Manager manager = null;

		try {
			tx = session.beginTransaction();
			manager = new Manager(name, age);
			manageId = (Integer) session.save(manager);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return manager;
	}

	/**
	 * 添加部门
	 * 
	 * @param name
	 * @param manager
	 * @return
	 */
	public Integer addDepartment(String name, Manager manager) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer departmentID = null;

		try {
			tx = session.beginTransaction();
			Department department = new Department(name, manager);
			departmentID = (Integer) session.save(department);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return departmentID;
	}

	/**
	 * 查看所有部门
	 */
	public void listDepartments() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List departments = session.createQuery("FROM Department").list();
			for (Iterator iterator = departments.iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				System.out.print("ame: " + department.getName());
				Manager manager = department.getManager();
				System.out.println("Address ");
				System.out.println("name: " + manager.getName());
				System.out.println("age: " + manager.getAge());
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
	 * 修改部门信息
	 * 
	 * @param departmentId
	 * @param departmentName
	 */
	public void updateDepartment(Integer departmentId, String departmentName) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Department department = (Department) session.get(Department.class, departmentId);
			department.setName(departmentName);
			session.update(department);
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
