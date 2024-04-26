package codegym.mod04.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name = "tasks")
public class EmployeeTask {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;
    private String name;
    private LocalDate deadline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmployeeTask.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("employee=" + employee)
                .add("name='" + name + "'")
                .add("deadline=" + deadline)
                .toString();
    }
}
