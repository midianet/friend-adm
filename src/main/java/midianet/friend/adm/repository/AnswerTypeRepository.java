package midianet.friend.adm.repository;

import midianet.friend.adm.domain.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerTypeRepository extends JpaRepository<AnswerType,Long>, JpaSpecificationExecutor{

}