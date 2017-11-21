package midianet.friend.adm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50,nullable = false)
    private String description;

    @NotNull
    @Column(length = 2000,nullable = false)
    private String value;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private AnswerType type;

    public static Specification<AnswerType> filter(final Long id, final String description , final Long  typeId ){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
                if(id != null){
                predicates.add(criteriaBuilder.equal(root.<Long>get("id"), id));
            }
            if(description != null && !description.isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+ description.toLowerCase() + "%"));
            }
            if(typeId != null){
                predicates.add(criteriaBuilder.equal(root.get("type").<Long>get("id"), typeId));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        };
    }


}