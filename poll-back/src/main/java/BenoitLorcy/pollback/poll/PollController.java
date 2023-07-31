package BenoitLorcy.pollback.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/v1/polls")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService){
        this.pollService = pollService;
    }

    @PostMapping("/create")
    public ResponseEntity<PollDto> registerPoll(@RequestBody Poll poll) {
        PollDto polldto = pollService.addNewPoll(poll);
        return ResponseEntity.ok(polldto);
    }

    @GetMapping("/{url}")
    public PollDto getPollByUrl(@PathVariable String url){
        return pollService.getPollByUrl(url);
    }

    @PostMapping("/vote/{url}/option/{optionId}")
    public void addVote(@PathVariable String url, @PathVariable Integer optionId){
        System.out.println("voted");
        pollService.addVote(url, optionId);
    }
}
