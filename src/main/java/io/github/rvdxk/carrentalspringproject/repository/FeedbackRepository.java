package io.github.rvdxk.carrentalspringproject.repository;

import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
