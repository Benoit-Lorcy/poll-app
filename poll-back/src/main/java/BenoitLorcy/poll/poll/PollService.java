package BenoitLorcy.poll.poll;

import BenoitLorcy.poll.pollOption.PollOption;
import BenoitLorcy.poll.pollOption.PollOptionDto;
import BenoitLorcy.poll.pollOption.PollOptionRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PollService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int URL_LENGTH = 6;
    private final Random rng = new Random();

    @Autowired
    public PollService(PollRepository pollRepository, PollOptionRepository pollOptionRepository){
        this.pollRepository = pollRepository;
        this.pollOptionRepository = pollOptionRepository;
    }

    public List<Poll> getPolls(){
        return pollRepository.findAll();
    }

    public PollDto getPollByUrl(String url){
        Poll poll = pollRepository.findById(url).orElseThrow(() -> new EntityNotFoundException("No poll found with URL " + url));
        return toDto(poll);
    }

    public PollDto addNewPoll(Poll poll) {
        String url = generateUrl();
        while(pollRepository.findById(url).isPresent()) {url = generateUrl();}
        poll.setUrl(url);

        List<PollOption> pollOptions = poll.getPollOptions();
        for (int i = 0; i < pollOptions.size(); i++) {
            PollOption pollOption = pollOptions.get(i);
            pollOption.setOptionId(i);
            pollOption.setVoteCount(0);
        }

        pollRepository.save(poll);
        return toDto(poll);
    }

    private String generateUrl(){
        StringBuilder sb = new StringBuilder(URL_LENGTH);
        for (int i = 0; i < URL_LENGTH; i++) {
            int index = rng.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    public void addVote(String url, Integer optionID) {
        Optional<Poll> poll = pollRepository.findById(url);
        if (poll.isPresent()) {
            List<PollOption> pollOptions = poll.get().getPollOptions();
            for (PollOption pollOption : pollOptions) {
                if (pollOption.getOptionId().equals(optionID)) {
                    pollOption.setVoteCount(pollOption.getVoteCount() + 1);
                    pollOptionRepository.save(pollOption);
                    break;
                }
            }
        } else {
            throw new InvalidRequestStateException();
        }
    }

    private PollDto toDto(Poll poll) {
        List<PollOptionDto> pollOptionDtos = poll.getPollOptions().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PollDto(
                poll.getUrl(),
                poll.getPollName(),
                poll.getAuthorName(),
                pollOptionDtos
        );
    }

    private PollOptionDto toDto(PollOption option) {
        return new PollOptionDto(
                option.getOptionId(),
                option.getLabel(),
                option.getVoteCount()
        );
    }
}
