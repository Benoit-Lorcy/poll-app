package BenoitLorcy.pollback.poll;

import BenoitLorcy.pollback.pollOption.PollOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Poll {
    @Id
    @Column(length = 6)
    private String url;

    private String pollName;

    private String authorName;

    @OneToMany(targetEntity = PollOption.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_fk", referencedColumnName = "url")
    @OrderColumn(name = "answer_order")
    private List<PollOption> pollOptions;
}
