package codegym.mod04.repository;

import codegym.mod04.config.HibernateConfiguration;
import codegym.mod04.model.Employee;
import codegym.mod04.model.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class EmployeeRepository {

    public Employee save(Employee employee) {


        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        if (employee.getId() == null) {
            employee.setId(UUID.randomUUID().toString());
            session.save(employee);
        } else {
            session.update(employee);
        }

        txn.commit();

        return employee;
    }

    public List<Employee> getAllByName(String name) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        String queryString = "from Employee where name = :empName ";

        Query<Employee> query = session.createQuery(queryString, Employee.class);

        query.setParameter("empName", name);

        List<Employee> list = query.list();

        txn.commit();

        return list;
    }
}
