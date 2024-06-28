package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.dto.FeedbackDto;
import io.github.rvdxk.carrentalspringproject.entity.Feedback;
import io.github.rvdxk.carrentalspringproject.entity.Role;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.repository.FeedbackRepository;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback1;
    private Feedback feedback2;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .firstName("admin")
                .lastName("admin")
                .email("admin@admin.com")
                .password("admin")
                .role(Role.ADMIN)
                .build();

        feedback1 = Feedback.builder()
                .id(1L)
                .user(user)
                .email(user.getEmail())
                .feedbackDate(LocalDate.now())
                .rating(5)
                .comments("Best car rental ever!")
                .build();

        feedback2 = Feedback.builder()
                .id(1L)
                .user(user)
                .email(user.getEmail())
                .feedbackDate(LocalDate.now())
                .rating(3)
                .comments("Good car rental. Nothing special.")
                .build();
    }

    @Test
    void findAllFeedback() {

        List<Feedback> feedbackList = Arrays.asList(feedback1, feedback2);

        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        List<FeedbackDto> result = feedbackService.findAllFeedback();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(feedbackRepository, times(1)).findAll();

        assertEquals(feedback1.getEmail(), result.get(0).getEmail());
        assertEquals(feedback1.getRating(), result.get(0).getRating());
        assertEquals(feedback1.getComments(), result.get(0).getComments());

        assertEquals(feedback2.getEmail(), result.get(1).getEmail());
        assertEquals(feedback2.getRating(), result.get(1).getRating());
        assertEquals(feedback2.getComments(), result.get(1).getComments());

    }

    @Test
    void findFeedbackById() {

        when(feedbackRepository.findById(feedback1.getId())).thenReturn(Optional.of(feedback1));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        FeedbackDto result = feedbackService.findFeedbackById(feedback1.getId(), user.getId());

        assertNotNull(result);

        verify(feedbackRepository, times(1)).findById(feedback1.getId());

        assertEquals(feedback1.getEmail(), result.getEmail());
        assertEquals(feedback1.getRating(), result.getRating());
        assertEquals(feedback1.getComments(), result.getComments());
    }

    @Test
    void addFeedback() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(feedbackRepository.save(feedback1)).thenReturn(feedback1);

        feedbackService.addFeedback(feedback1, user.getId());

        verify(feedbackRepository, times(1)).save(feedback1);
    }

    @Test
    void editFeedback() {
        FeedbackDto updateFeedback = FeedbackDto.builder()
                .id(1L)
                .email(user.getEmail())
                .feedbackDate(LocalDate.now())
                .rating(4)
                .comments("Good car rental. Cars interior could be cleaner.")
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(feedbackRepository.findById(feedback1.getId())).thenReturn(Optional.of(feedback1));

        feedbackService.editFeedback(updateFeedback, user.getId(), feedback1.getId());

        verify(feedbackRepository, times(1)).findById(feedback1.getId());
        verify(userRepository, times(1)).findById(user.getId());

        verify(feedbackRepository, times(1)).save(feedback1);

        assertEquals(feedback1.getEmail(), updateFeedback.getEmail());
        assertEquals(feedback1.getRating(), updateFeedback.getRating());
        assertEquals(feedback1.getComments(), updateFeedback.getComments());
    }

    @Test
    void deleteFeedback() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(feedbackRepository.findById(feedback1.getId())).thenReturn(Optional.of(feedback1));

        feedbackService.deleteFeedback(user.getId(), feedback1.getId());

        verify(userRepository, times(1)).findById(user.getId());
        verify(feedbackRepository, times(1)).findById(feedback1.getId());
        verify(feedbackRepository, times(1)).deleteById(feedback1.getId());
    }
}