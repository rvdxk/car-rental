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
    public ResponseEntity<String> addFeedback(@PathVariable("userId") Long userId,
                                              @RequestBody @Valid Feedback feedback){
        feedbackService.addFeedback(feedback, userId);
        return new ResponseEntity<>("Feedback successfully added!", HttpStatus.CREATED);
    }
    @GetMapping("/feedbacks")
    public ResponseEntity<List<FeedbackDto>> findAllFeedback(){
        List<FeedbackDto> feedbackList = feedbackService.findAllFeedback();
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("/{userId}/feedbacks/{feedbackId}")
    public ResponseEntity<FeedbackDto> findFeedbackById(@PathVariable("userId") Long userId,
                                                        @PathVariable("feedbackId") Long feedbackId){
        FeedbackDto feedbackDto = feedbackService.findFeedbackById(userId, feedbackId);
        return new ResponseEntity<>(feedbackDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}/feedbacks/{feedbackId}")
    public ResponseEntity<String> editFeedback(@PathVariable("userId") Long userId,
                                               @PathVariable("feedbackId") Long feedbackId,
                                               @RequestBody @Valid FeedbackDto feedbackDto){
        feedbackService.editFeedback(feedbackDto, userId, feedbackId);
        return new ResponseEntity<>("Feedback successfully edited!", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/feedbacks/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("userId") Long userId,
                                                 @PathVariable("feedbackId") Long feedbackId){
        feedbackService.deleteFeedback(userId, feedbackId);
        return new ResponseEntity<>("Feedback successfully deleted!", HttpStatus.NO_CONTENT);
    }
}
