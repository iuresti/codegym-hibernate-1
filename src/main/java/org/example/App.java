package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    private static SessionFactory sessionFactory;
    private static SessionFactory sessionJavaConfigFactory;

    public static void main(String[] args) throws SQLException {

        String connectionString = "jdbc:mysql://localhost:3306/pruebas";
        String user = "root";
        String password = "Admin00";
        String query = "select id, name, occupation, salary,age, birthdate from employee where name = ?";

        Connection connection = DriverManager.getConnection(connectionString, user, password);

        prepareNewData(connection);

        //Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, "Empleado 18");

        ResultSet resultSet = preparedStatement.executeQuery();


        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();

            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setOccupation(resultSet.getString("occupation"));
            employee.setSalary(resultSet.getBigDecimal("salary"));
            employee.setAge(resultSet.getInt("age"));
            employee.setBirthdate(resultSet.getObject("birthdate", LocalDate.class));

            employees.add(employee);
        }

    }

    private static void prepareNewData(Connection connection) throws SQLException {


        try {
            connection.setAutoCommit(false);

            String query = "insert into employee (id, name, occupation, salary)\n" +
                    "values (2000007, 'ABC', '', 100);";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.addBatch();

            statement.addBatch("insert into employee (id, name, occupation, salary)\n" +
                    "values (2000008, 'XYZ', '', 100);");

            statement.addBatch("insert into employee (id, name, occupation, salary)\n" +
                    "values (2000009, 'XYZ', '', 100);");

            int[] result = statement.executeBatch();

            System.out.println(result);
            connection.commit();
        }catch (SQLException ex){
            connection.rollback();
        }

    }

}
