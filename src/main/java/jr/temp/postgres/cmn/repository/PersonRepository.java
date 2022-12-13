package jr.temp.postgres.cmn.repository;

import jr.temp.postgres.cmn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 测试用表Repository
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    /**
     * 通过ID获取个人信息
     * @param id ID
     * @return 个人信息
     */
    Person findById(int id);

    /**
     * 通过姓名获取个人信息
     * @param name 姓名
     * @return 个人信息
     */
    Person findByName(String name);
}
