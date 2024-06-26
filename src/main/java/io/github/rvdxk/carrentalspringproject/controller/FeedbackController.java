package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/{userId}/feedbacks/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFeedback(@PathVariable("userId") Long userId,
                              @RequestBody @Valid Feedback feedback){
        feedbackService.addFeedback(feedback, userId);
        return "Feedback successfully added!";
    }
    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackDto>> findAllFeedback(){
        List<FeedbackDto> feedbackList = feedbackService.findAllFeedback();
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("/{userId}/feedbacks/{feedbackId}")
    public FeedbackDto findFeedbackById(@PathVariable("userId") Long userId,
                                        @PathVariable("feedbackId") Long feedbackId){
        return feedbackService.findFeedbackById(userId, feedbackId);
    }

    @PutMapping("/{userId}/feedbacks/{feedbackId}")
    public String editFeedback(@PathVariable("userId") Long userId,
                               @PathVariable("feedbackId") Long feedbackId,
                               @RequestBody @Valid FeedbackDto feedbackDto){
        feedbackService.editFeedback(feedbackDto, userId, feedbackId);
        return "Feedback successfully edited!";
    }

    @DeleteMapping("/{userId}/feedbacks/{feedbackId}")
    public String deleteFeedback(@PathVariable("userId") Long userId,
                                 @PathVariable("feedbackId") Long feedbackId){
        feedbackService.deleteFeedback(userId, feedbackId);
        return "Feedback successfully deleted!";
    }
}
