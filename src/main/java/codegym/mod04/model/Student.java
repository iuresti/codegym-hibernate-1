package codegym.mod04.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Student extends Person {

    private int generation;
    private String code;
    private String level;

}
