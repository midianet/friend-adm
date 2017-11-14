package midianet.friend.adm.resource;

import midianet.friend.adm.domain.Parameter;
import midianet.friend.adm.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parameter")
public class ParameterResource {

    @Autowired
    private ParameterRepository repository;

    @GetMapping("/list")
    public ResponseEntity<Parameter> list() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

}