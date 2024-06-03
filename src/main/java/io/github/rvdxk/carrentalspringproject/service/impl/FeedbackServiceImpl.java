package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.FeedbackRepository;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getAllFeedback() {
        List<Feedback> getAllFeedback = feedbackRepository.findAll();
        return getAllFeedback;
    }

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void editFeedback(Feedback feedback, Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedbackRepository.deleteById(id);
    }
}
