package HiberNate.HibernateCache;

import javax.persistence.Cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import HiberNate.HibernateCache.beans.Employee;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		Employee emp1 = new Employee();
//		emp1.setEmpId(101);
//		emp1.setEmpName("Kousik");
//		emp1.setSalary(10000);
//
//		Employee emp2 = new Employee();
//		emp2.setEmpId(202);
//		emp2.setEmpName("KD");
//		emp2.setSalary(15000);

		Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
		SessionFactory sf = con.buildSessionFactory();
		
		Session session1 = sf.openSession();
		session1.beginTransaction();
		
		/*
		 * This is called 1st level Cache. Here the query will be executed only once.
		 * 1st level cache is provided by hibernate by default.
		 * It is done within same session.
		 *///		
//		Employee e1;
//		e1=session.get(Employee.class, 101);
//		System.out.println(e1);
//		
//		Employee e2;
//		e2=session.get(Employee.class,101);
//		System.out.println(e2);
		
		
		
//		session.save(emp1);
//		session.save(emp2);
		
		Employee e1;
//		e1=session1.get(Employee.class, 101);
		/* Caching level 2 with Query */
		Query q1=session1.createQuery("from Employee_Details where empid=101");
		q1.setCacheable(true);
		e1=(Employee) q1.uniqueResult();
		System.out.println(e1);
		session1.getTransaction().commit();
		session1.close();
		
		Session session2=sf.openSession();
		session2.beginTransaction();
		Employee e2;
//		e2=session2.get(Employee.class, 101);
		Query q2=session2.createQuery("from Employee_Details where empid=101");
		q2.setCacheable(true);
		e2=(Employee) q2.uniqueResult();
		System.out.println(e2);
		session2.getTransaction().commit();
		session2.close();
		
	}
}
