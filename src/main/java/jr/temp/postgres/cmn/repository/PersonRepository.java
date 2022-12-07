package jr.temp.postgres.cmn.repository;

import jr.temp.postgres.cmn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findById(int id);
    Person findByName(String name);
}
