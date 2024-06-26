package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> findAllFeedback();
    FeedbackDto findFeedbackById(Long userId, Long feedbackId);
    void addFeedback(Feedback feedback, Long id);
    void editFeedback(FeedbackDto feedbackDto, Long userI, Long feedbackIdd);
    void deleteFeedback(Long userId, Long feedbackId);
}
