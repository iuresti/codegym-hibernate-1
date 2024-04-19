package codegym.mod04.config;

import codegym.mod04.model.Employee;
import codegym.mod04.model.EmployeeTask;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfiguration {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            buildSessionJavaConfigFactory();
        }

        return sessionFactory;
    }

    private static void buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();


            Properties props = new Properties();

            props.put("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/my_company");
            props.put("hibernate.connection.username", "postgres");
            props.put("hibernate.connection.password", "postgres");
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.hbm2ddl.auto", "create");

            configuration.setProperties(props);

            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(EmployeeTask.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
