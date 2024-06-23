package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import io.github.rvdxk.carrentalspringproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;



    @PostMapping("/users/{userId}/feedbacks/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFeedback(@PathVariable("userId") Long userId,
                              @RequestBody @Valid Feedback feedback){
        User user = userService.getUserById(userId);
        feedback.setUser(user);
        feedbackService.addFeedback(feedback);
        return "Feedback successfully added!";
    }
    @GetMapping("/users/feedbacks")
    public ResponseEntity<List<FeedbackDto>> getAllFeedback(){
        List<FeedbackDto> feedbackList = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/feedbacks/{feedbackId}")
    public FeedbackDto getFeedbackById(@PathVariable("userId") Long userId,
                                       @PathVariable("feedbackId") Long feedbackId){
        userService.getUserById(userId);
        FeedbackDto feedbackDto = feedbackService.getFeedbackById(feedbackId);
        return feedbackDto;
    }

    @PutMapping("/users/{userId}/feedbacks/{feedbackId}")
    public String editFeedback(@PathVariable("userId") Long userId,
                               @PathVariable("feedbackId") Long feedbackId,
                               @RequestBody @Valid FeedbackDto feedbackDto){
        userService.getUserById(userId);
        feedbackService.editFeedback(feedbackDto, feedbackId);
        return "Feedback successfully edited!";
    }

    @DeleteMapping("/users/{userId}/feedbacks/{feedbackId}")
    public String deleteFeedback(@PathVariable("userId") Long userId,
                                 @PathVariable("feedbackId") Long feedbackId){
        userService.getUserById(userId);
        feedbackService.deleteFeedback(feedbackId);
        return "Feedback successfully deleted!";
    }
}
