package io.github.rvdxk.carrentalspringproject.controller;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.mapper.FeedbackMapper;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import io.github.rvdxk.carrentalspringproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/feedbacks/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFeedback(@PathVariable("userId") Long userId,
                              @RequestBody @Valid Feedback feedback){
        User user = userService.findUserById(userId);
        feedback.setUser(user);
        feedback.setEmail(user.getEmail());
        feedbackService.addFeedback(feedback);
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
        userService.findUserById(userId);
        FeedbackDto feedbackDto = feedbackService.findFeedbackById(feedbackId);
        return feedbackDto;
    }

    @PutMapping("/{userId}/feedbacks/{feedbackId}")
    public String editFeedback(@PathVariable("userId") Long userId,
                               @PathVariable("feedbackId") Long feedbackId,
                               @RequestBody @Valid FeedbackDto feedbackDto){
        User user = userService.findUserById(userId);
        Feedback feedback = FeedbackMapper.mapToFeedback(feedbackDto);
        feedback.setUser(user);
        feedback.setEmail(user.getEmail());
        feedbackService.editFeedback(feedbackDto, feedbackId);
        return "Feedback successfully edited!";
    }

    @DeleteMapping("/{userId}/feedbacks/{feedbackId}")
    public String deleteFeedback(@PathVariable("userId") Long userId,
                                 @PathVariable("feedbackId") Long feedbackId){
        userService.findUserById(userId);
        feedbackService.deleteFeedback(feedbackId);
        return "Feedback successfully deleted!";
    }
}
