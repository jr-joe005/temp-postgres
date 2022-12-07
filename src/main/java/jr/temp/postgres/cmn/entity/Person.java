package jr.temp.postgres.cmn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    int id;

    String name;
    int age;
    String address;
}
