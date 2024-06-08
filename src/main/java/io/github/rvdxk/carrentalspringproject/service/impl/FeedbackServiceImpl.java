package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.FeedbackMapper;
import io.github.rvdxk.carrentalspringproject.repository.FeedbackRepository;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<FeedbackDto> getAllFeedback() {
        List<Feedback> getAllFeedback = feedbackRepository.findAll();
        List<FeedbackDto> getAllFeedbackDto = getAllFeedback.stream()
                .map((feedback)->FeedbackMapper.mapToFeedbackDto(feedback))
                .collect(Collectors.toUnmodifiableList());
        return getAllFeedbackDto;
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        FeedbackDto feedbackDto = FeedbackMapper.mapToFeedbackDto(feedback);
        return feedbackDto;
    }


    @Override
    public void addFeedback(Feedback feedback) {
        feedback.setFeedbackDate(LocalDateTime.now().toLocalDate());
        feedbackRepository.save(feedback);
    }

    @Override
    public void editFeedback(Feedback feedback, Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedback.setId(id);
        feedback.setFeedbackDate(LocalDateTime.now().toLocalDate());
        feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedbackRepository.deleteById(id);
    }
}
