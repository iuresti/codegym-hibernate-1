package codegym.mod04;

import codegym.mod04.config.HibernateConfiguration;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        System.out.println("Mensaje");
    }
}
