package midianet.friend.adm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerType{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50,nullable = false)
    private String method;

//
//    public static Specification<AnswerType> id(final Long id) {
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.<Long>get("id"), id);
//    }
//
//    public static Specification<AnswerType> methodStart(final String method) {
//        return (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.like(criteriaBuilder.lower(root.get("method")), method.toLowerCase() + "%");
//    }


//    public static Specification<AnswerType> byExample(final AnswerType example){
//        return new Specification<AnswerType>() {
//            @Override
//            public Predicate toPredicate(Root<AnswerType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                final List<Predicate> predicates = new ArrayList<>();
//                if(example.getId() != null){
//                    predicates.add(criteriaBuilder.equal(root.<Long>get("id"), example.getId()));
//                }
//                if(example.getMethod() != null && !example.getMethod().isEmpty()){/**/
//                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("method")), example.getMethod().toLowerCase() + "%"));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
//            }
//        };
//    }


    public static Specification<AnswerType> filter(final Long id, final String method){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if(id != null){
                predicates.add(criteriaBuilder.equal(root.<Long>get("id"), id));
            }
            if(method != null && !method.isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("method")), "%"+ method.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        };
    }



}