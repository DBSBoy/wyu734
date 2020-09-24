package com.ibm.test;
/**
 * hibernate中查询一个的方法：get()和load()
 * 共同点：都是根居Id查询一个实体
 * 区别：
 *    1.查询时机不一样
 *      get的查询：每次调用get方法时，马上查询          立即加载
 *      load的查询：每次真正使用的时候才会去查询        延迟加载，懒加载    
 *    2.返回结果不一样
 *      get方法返回的对象是实体类类型
 *      load方法返回的对象是实体类类型的代理对象
 * @author QiuYangZhang
 *
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ibm.entity.Employee;

public class GetAndLoad {

	private static SessionFactory factory;
	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		GetAndLoad getAndLoad =new GetAndLoad();
		//get方法
		getAndLoad.getMethod(1);
		
		//load方法
		getAndLoad.loadMethod(1);
	}
	
	/**
	 * get方法
	 */
	public void getMethod(Integer employeeId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = (Employee) session.get(Employee.class, employeeId);
		System.out.println("get方法 : " + employee);
		transaction.commit();
		session.close();
	}
	
	/**
	 * load方法
	 */
	public void loadMethod(Integer employeeId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = (Employee) session.load(Employee.class, employeeId);
		System.out.println("load方法 : " + employee);
		transaction.commit();
		session.close();
	}
}
