package BenoitLorcy.pollback.poll;

import BenoitLorcy.pollback.pollOption.PollOptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PollDto {
    private String url;
    private String pollName;
    private String authorName;
    private List<PollOptionDto> pollOption;
}
