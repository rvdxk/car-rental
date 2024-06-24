package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.FeedbackMapper;
import io.github.rvdxk.carrentalspringproject.repository.FeedbackRepository;
import io.github.rvdxk.carrentalspringproject.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackDto> findAllFeedback() {
        List<Feedback> getAllFeedback = feedbackRepository.findAll();
        List<FeedbackDto> getAllFeedbackDto = getAllFeedback.stream()
                .map((feedback)->FeedbackMapper.mapToFeedbackDto(feedback))
                .collect(Collectors.toUnmodifiableList());
        return getAllFeedbackDto;
    }

    @Override
    public FeedbackDto findFeedbackById(Long id) {
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
    public void editFeedback(FeedbackDto feedbackDto, Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedbackDto.setId(id);
        feedbackDto.setFeedbackDate(LocalDateTime.now().toLocalDate());
        feedbackRepository.save(FeedbackMapper.mapToFeedback(feedbackDto));
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + id + " not found"));
        feedbackRepository.deleteById(id);
    }
}
