package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAllFeedback();
    void addFeedback(Feedback feedback);
    void editFeedback(Feedback feedback, Long id);
    void deleteFeedback(Long id);
}
