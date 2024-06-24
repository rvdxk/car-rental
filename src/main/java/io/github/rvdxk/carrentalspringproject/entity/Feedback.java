package io.github.rvdxk.carrentalspringproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "email")
    @JsonIgnore
    private String email;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate feedbackDate;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column
    private int rating;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 500, message = "Content cannot be more than 500 characters")
    @Column
    private String comments;
}
