package codegym.mod04.repository;

import codegym.mod04.config.HibernateConfiguration;
import codegym.mod04.model.Employee;
import codegym.mod04.model.EmployeeTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class EmployeeRepository {

    public Employee getById(String employeeId1, String employeeId2){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        Employee employee = session.find(Employee.class, employeeId1);
        Employee employee2 = session.find(Employee.class, employeeId2);

        Employee employee3 = session.find(Employee.class, employeeId1);
        Employee employee4 = session.find(Employee.class, employeeId2);

        txn.commit();

        return employee;
    }

    public Employee getById(String employeeId){
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        Employee employee = session.find(Employee.class, employeeId);

        txn.commit();

        return employee;
    }

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

        query.setCacheable(true);

        query.setParameter("empName", name);

        List<Employee> list = query.list();

        txn.commit();

        return list;
    }

    public List<Employee> getAllGreaterThanSalaryHQL(BigDecimal salary) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        String queryString = "from Employee where salary > :salary ";

        Query<Employee> query = session.createQuery(queryString, Employee.class);

        query.setParameter("salary", salary);

        List<Employee> list = query.list();

        txn.commit();

        return list;
    }

    public List<Employee> getAllGreaterThanSalaryCriteria(BigDecimal salary) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction txn = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("salary"), salary));

        Query<Employee> query = session.createQuery(criteriaQuery);

        List<Employee> list = query.list();

        txn.commit();

        return list;
    }

    public List<Employee> getAllGreaterThanSalaryAndOccupation(BigDecimal salary, String occupation) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction txn = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        Predicate predicateSalary = criteriaBuilder.gt(root.get("salary"), salary);
        Predicate predicateOccupation = criteriaBuilder.like(root.get("occupation"), occupation);

        criteriaQuery.select(root).where(criteriaBuilder.and(predicateSalary, predicateOccupation));

        Query<Employee> query = session.createQuery(criteriaQuery);

        List<Employee> list = query.list();

        txn.commit();

        return list;
    }

    public Double getAverageSalary() {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction txn = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);

        criteriaQuery.select(criteriaBuilder.avg(root.get("salary")));

        Query<Double> query = session.createQuery(criteriaQuery);

        Double averageSalary = query.uniqueResult();

        txn.commit();

        return averageSalary;
    }
}
