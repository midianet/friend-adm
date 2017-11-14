package midianet.friend.adm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

}