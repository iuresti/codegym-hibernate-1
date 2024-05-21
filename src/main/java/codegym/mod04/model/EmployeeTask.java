package codegym.mod04.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class EmployeeTask {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;
    private String name;
    private LocalDate deadline;


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
