package codegym.mod04.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    private String id;
    private String name;
    private String occupation;
    private BigDecimal salary;

    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Set<EmployeeTask> tasks;

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("occupation='" + occupation + "'")
                .add("salary=" + salary)
                .add("birthdate=" + birthdate)
                .toString();
    }
}
