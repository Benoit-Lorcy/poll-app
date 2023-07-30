package BenoitLorcy.poll.pollOption;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PollOptionDto {
    private Integer optionId;
    private String label;
    private Integer voteCount;
}
