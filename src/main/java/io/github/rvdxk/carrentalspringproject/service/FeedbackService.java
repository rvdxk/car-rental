package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> getAllFeedback();
    FeedbackDto getFeedbackById(Long id);
    void addFeedback(Feedback feedback);
    void editFeedback(Feedback feedback, Long id);
    void deleteFeedback(Long id);
}
