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



    @PostMapping("/user/{userId}/feedback/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFeedback(@PathVariable("userId") Long userId,
                              @RequestBody @Valid Feedback feedback){
        User user = userService.getUserById(userId);
        feedback.setUser(user);
        feedbackService.addFeedback(feedback);
        return "Feedback successfully added!";
    }
    @GetMapping("/user/feedback/all")
    public ResponseEntity<List<FeedbackDto>> getAllFeedback(){
        List<FeedbackDto> feedbackList = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/feedback/{feedbackId}")
    public FeedbackDto getFeedbackById(@PathVariable("userId") Long userId,
                                       @PathVariable("feedbackId") Long feedbackId){
        userService.getUserById(userId);
        FeedbackDto feedbackDto = feedbackService.getFeedbackById(feedbackId);
        return feedbackDto;
    }

    @PutMapping("/user/{userId}/feedback/{feedbackId}")
    public String editFeedback(@PathVariable("userId") Long userId,
                               @PathVariable("feedbackId") Long feedbackId,
                               @RequestBody @Valid Feedback feedback){
        userService.getUserById(userId);
        feedbackService.editFeedback(feedback, feedbackId);
        return "Feedback successfully edited!";
    }

    @DeleteMapping("/user/{userId}/feedback/{feedbackId}")
    public String deleteFeedback(@PathVariable("userId") Long userId,
                                 @PathVariable("feedbackId") Long feedbackId){
        userService.getUserById(userId);
        feedbackService.deleteFeedback(feedbackId);
        return "Feedback successfully deleted!";
    }
}
