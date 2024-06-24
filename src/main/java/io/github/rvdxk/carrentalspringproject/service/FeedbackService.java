package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> findAllFeedback();
    FeedbackDto findFeedbackById(Long id);
    void addFeedback(Feedback feedback);
    void editFeedback(FeedbackDto feedbackDto, Long id);
    void deleteFeedback(Long id);
}
