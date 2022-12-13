package jr.temp.postgres.cmn.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 测试用表Entity
 */
@Data
@Entity
public class Person {

    /**
     * ID
     */
    @Id
    int id;

    /**
     * 姓名
     */
    String name;

    /**
     * 年龄
     */
    int age;

    /**
     * 联系地址
     */
    String address;
}
