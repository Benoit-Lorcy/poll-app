package BenoitLorcy.pollback.pollOption;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class PollOption {
    @Id
    @SequenceGenerator(
            name = "poll_answer_id_sequence",
            sequenceName = "poll_answer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "poll_answer_id_sequence"
    )
    private Integer id;
    //to not leak internals i gess... should i really do that tho? Maybe custom request would be better...
    private Integer optionId;
    private String label;
    private Integer voteCount;
}
