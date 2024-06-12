package io.github.rvdxk.carrentalspringproject.mapper;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;

public class FeedbackMapper {
    public static FeedbackDto mapToFeedbackDto(Feedback feedback){
        FeedbackDto feedbackDto = new FeedbackDto(
                feedback.getUser().getUsername(),
                feedback.getFeedbackDate(),
                feedback.getRating(),
                feedback.getComments()
        );
        return feedbackDto;
    }
}
