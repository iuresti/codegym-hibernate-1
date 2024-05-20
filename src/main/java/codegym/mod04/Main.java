package codegym.mod04;

import codegym.mod04.config.HibernateConfiguration;
import codegym.mod04.model.Person;
import codegym.mod04.model.Professor;
import codegym.mod04.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Transaction txn = session.beginTransaction();

        session.persist(createProfessor("ABC", "Lenguajes", "1000"));
        session.persist(createProfessor("XYZ", "IA", "2000"));

        session.persist(createStudent("Juan", "97249", 2015, "A"));
        session.persist(createStudent("Francisco", "97249", 2015, "A"));
        session.persist(createStudent("Mirna", "97249", 2015, "A"));

        txn.commit();

        session = sessionFactory.getCurrentSession();

        txn = session.beginTransaction();

        Query<Person> professorQuery = session.createQuery("from Person", Person.class);

        List<Person> people = professorQuery.list();

        txn.commit();

        System.out.println("Mensaje");
    }

    private static Student createStudent(String name, String code, int generation, String level) {
        Student student = new Student();

        student.setId(UUID.randomUUID().toString());
        student.setName(name);
        student.setCode(code);
        student.setGeneration(generation);
        student.setLevel(level);

        return student;
    }

    private static Professor createProfessor(String name, String academy, String rpe) {
        Professor professor = new Professor();

        professor.setId(UUID.randomUUID().toString());
        professor.setName(name);
        professor.setAcademy(academy);
        professor.setRpe(rpe);

        return professor;
    }
}
