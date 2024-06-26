package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.mapper.FeedbackMapper;
import io.github.rvdxk.carrentalspringproject.repository.FeedbackRepository;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public List<FeedbackDto> findAllFeedback() {
        List<Feedback> getAllFeedback = feedbackRepository.findAll();
        List<FeedbackDto> getAllFeedbackDto = getAllFeedback.stream()
                .map((feedback)->FeedbackMapper.mapToFeedbackDto(feedback))
                .collect(Collectors.toUnmodifiableList());
        return getAllFeedbackDto;
    }

    @Override
    public FeedbackDto findFeedbackById(Long userId, Long feedbackId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + feedbackId + " not found"));

        FeedbackDto feedbackDto = FeedbackMapper.mapToFeedbackDto(feedback);

        return feedbackDto;
    }


    @Override
    public void addFeedback(Feedback feedback, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        feedback.setUser(user);
        feedback.setEmail(user.getEmail());
        feedback.setFeedbackDate(LocalDateTime.now().toLocalDate());
        feedbackRepository.save(feedback);
    }

    @Override
    public void editFeedback(FeedbackDto feedbackDto, Long userId, Long feedbackId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        Feedback existingFeedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback with id " + feedbackId + " not found"));

        Feedback feedback = FeedbackMapper.mapToFeedback(feedbackDto);
        feedback.setUser(user);
        feedback.setEmail(user.getEmail());

        existingFeedback.setFeedbackDate(LocalDateTime.now().toLocalDate());
        existingFeedback.setRating(feedbackDto.getRating());
        existingFeedback.setComments(feedbackDto.getComments());

        feedbackRepository.save(existingFeedback);
    }

    @Override
    public void deleteFeedback(Long userId, Long feedbackId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback with id " + feedbackId + " not found"));

        feedbackRepository.deleteById(feedbackId);
    }
}
