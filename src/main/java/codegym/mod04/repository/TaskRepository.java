package codegym.mod04.repository;

import codegym.mod04.config.HibernateConfiguration;
import codegym.mod04.model.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class TaskRepository {

    public List<EmployeeTask> getAllByEmployeeName(String employeeName){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        String queryString = "from EmployeeTask where employee.name = :empName ";

        Query<EmployeeTask> query = session.createQuery(queryString, EmployeeTask.class);

        query.setParameter("empName", employeeName);

        List<EmployeeTask> list = query.list();

        txn.commit();

        return list;
    }

    public EmployeeTask save(EmployeeTask employeeTask) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        if (employeeTask.getId() == null) {
            employeeTask.setId(UUID.randomUUID().toString());
            session.save(employeeTask);
        } else {
            session.update(employeeTask);
        }

        txn.commit();

        return employeeTask;
    }
}
