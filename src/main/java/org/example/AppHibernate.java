package org.example;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class AppHibernate {
    private static SessionFactory sessionFactory;
    private static SessionFactory sessionJavaConfigFactory;

    public static void main(String[] args) throws SQLException {
        //buildSessionFactory();

        buildSessionJavaConfigFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        //Query<Employee> query = session.createQuery("select distinct employee from EmployeeTask where deadline < CURDATE()", Employee.class);
        String employeeName = "Empleado 973246";
        Query<Employee> query = session.createQuery("from Employee where name = :employee_algo");

        query.setParameter("employee_algo", employeeName);

        ScrollableResults employeesScroll = query.scroll();
        int count = 0;

        while(employeesScroll.next()){
            Employee employee = (Employee) employeesScroll.get()[0];
            System.out.println(employee);
            count++;
        }

        System.out.println("Fueron: " + count);

        txn.commit();


    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();


            Properties props = new Properties();

            props.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/pruebas");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "Admin00");
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            props.put("hibernate.show_sql", "true");

            configuration.setProperties(props);

            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(EmployeeTask.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
