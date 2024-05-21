package codegym.mod04.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//@MappedSuperclass
@Entity
@Table(name = "people")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Person {
    @Id
    private String id;
    private String name;
    private LocalDate birthdate;
    private String genre;
    private String estadoCivil;

}
