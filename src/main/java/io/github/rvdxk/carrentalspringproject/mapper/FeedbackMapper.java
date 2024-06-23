package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;

public class FeedbackMapper {

    public static FeedbackDto mapToFeedbackDto(Feedback feedback){
        return FeedbackDto.builder()
                .id(feedback.getId())
                .email(feedback.getUser().getEmail())
                .feedbackDate(feedback.getFeedbackDate())
                .rating(feedback.getRating())
                .comments(feedback.getComments())
                .build();
    }

    public static Feedback mapToFeedback(FeedbackDto feedbackDto){
        return Feedback.builder()
                .id(feedbackDto.getId())
                .feedbackDate(feedbackDto.getFeedbackDate())
                .rating(feedbackDto.getRating())
                .comments(feedbackDto.getComments())
                .build();

    }
}
