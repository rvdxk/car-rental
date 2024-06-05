package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate feedbackDate;
    //akutalny czas dodania

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column
    private int rating;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 500, message = "Content cannot be more than 500 characters")
    @Column
    private String comments;
}
