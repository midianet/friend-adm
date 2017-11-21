package midianet.friend.adm.repository;

import midianet.friend.adm.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerRepository extends JpaRepository<Answer,Long>, JpaSpecificationExecutor {

}