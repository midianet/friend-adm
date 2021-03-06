package midianet.friend.adm.resource;

import midianet.friend.adm.domain.User;
import midianet.friend.adm.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionResource {

    @Autowired
    private QuestionRepository repository;

    @GetMapping("/list")
    public ResponseEntity<User> list(){
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1L).name("Teste").build());
        list.add(User.builder().id(2L).name("Outro").build());
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
