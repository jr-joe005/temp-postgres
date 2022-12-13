package jr.temp.postgres.index;

import jr.temp.postgres.cmn.entity.Person;
import jr.temp.postgres.cmn.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试用画面Controller
 */
@Slf4j
@Controller
public class IndexController {

    /** 测试用表Repository */
    @Autowired
    private PersonRepository personRepository;

    /** Index页 */
    @GetMapping("/")
    public String index(){

        /* 获取DB数据 */
        Person person = personRepository.getReferenceById(100001);
        log.info("---- [IndexController.index] -- id     : {}", person.getId());
        log.info("---- [IndexController.index] -- name   : {}", person.getName());
        log.info("---- [IndexController.index] -- age    : {}", person.getAge());
        log.info("---- [IndexController.index] -- address: {}", person.getAddress());

        return "index";
    }
}