package midianet.friend.adm.repository;

import midianet.friend.adm.domain.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {

}