package io.github.rvdxk.carrentalspringproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FeedbackDto {

    private String email;
    private LocalDate feedbackDate;
    private int rating;
    private String comments;
}