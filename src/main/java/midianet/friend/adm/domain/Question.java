package midianet.friend.adm.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50,nullable = false)
    private String text;

    @NotEmpty
    @OneToMany
    @Column(nullable = false)
    private List<Answer> answers;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private QuestionType type;

}