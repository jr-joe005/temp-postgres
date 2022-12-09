package jr.temp.postgres.index;

import jr.temp.postgres.cmn.entity.Person;
import jr.temp.postgres.cmn.repository.PersonRepository;
import jr.temp.postgres.cmn.service.VaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private VaultService vaultService;

    @GetMapping("/")
    public String index(){
        log.info("---- index ----");

        Person person = personRepository.getReferenceById(100001);
        log.info("-- id     : {}", person.getId());
        log.info("-- name   : {}", person.getName());
        log.info("-- age    : {}", person.getAge());
        log.info("-- address: {}", person.getAddress());

        //vaultService.getCredentialInfo();

        return "index";
    }

}