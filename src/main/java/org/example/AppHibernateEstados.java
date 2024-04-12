package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.Properties;

public class AppHibernateEstados {
    private static SessionFactory sessionFactory;
    private static SessionFactory sessionJavaConfigFactory;

    public static void main(String[] args) throws SQLException {
        //buildSessionFactory();

        buildSessionJavaConfigFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        Employee employee;

        employee = session.find(Employee.class, 4);


        String name = employee.getName();

        session.refresh(employee);

        txn.commit();


    }


    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable ex) {
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
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.hbm2ddl.auto", "create");
            props.put("hibernate.show_sql", "true");

            configuration.setProperties(props);

            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(EmployeeTask.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
