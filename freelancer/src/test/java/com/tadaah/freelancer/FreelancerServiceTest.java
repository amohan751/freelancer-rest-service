package com.tadaah.freelancer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tadaah.freelancer.entity.Freelancer;
import com.tadaah.freelancer.repository.FreelancerRepository;
import com.tadaah.freelancer.service.FreelancerService;

@SpringBootTest
public class FreelancerServiceTest {
    @InjectMocks
    private FreelancerService freelancerService;
    @Mock
    private FreelancerRepository freelancerRepository;

    @Test
    public void testCreateFreelancer() {
        MockitoAnnotations.openMocks(this);
        Freelancer freelancer = new Freelancer(1L, "John", "Doe", "Male", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);
        Freelancer savedFreelancer = new Freelancer(1L, "John", "Doe", "Male", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);

        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(savedFreelancer);

        Freelancer actualFreelancer = freelancerService.createFreelancer(freelancer);
        assertEquals(savedFreelancer, actualFreelancer);
    }

    @Test
    public void testUpdateFreelancer() {
        MockitoAnnotations.openMocks(this);
        Freelancer existingFreelancer = new Freelancer(1L, "Jane", "Doe", "Female", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);
        Freelancer updatedFreelancer = new Freelancer(1L, "Jane", "Smith", "Female", "NEW_FREELANCER", LocalDate.of(1990, 2, 2), null);

        when(freelancerRepository.findById(1L)).thenReturn(java.util.Optional.of(existingFreelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(updatedFreelancer);

        Freelancer actualFreelancer = freelancerService.updateFreelancer(1L, updatedFreelancer);
        assertEquals(updatedFreelancer, actualFreelancer);
    }

    @Test
    public void testVerifyFreelancer() {
        MockitoAnnotations.openMocks(this);
        Freelancer existingFreelancer = new Freelancer(1L, "Jane", "Smith", "Female", "NEW_FREELANCER", LocalDate.of(1990, 2, 2), null);
        Freelancer updatedFreelancer = new Freelancer(1L, "Jane", "Smith", "Female", "VERIFIED", LocalDate.of(1990, 2, 2), null);

        when(freelancerRepository.findById(1L)).thenReturn(java.util.Optional.of(existingFreelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(updatedFreelancer);

        Freelancer actualFreelancer = freelancerService.verifyFreelancer(1L);
        assertEquals(updatedFreelancer, actualFreelancer);
    }

    @Test
    public void testGetFreelancersMarkedForDeletionLast7Days(){
        MockitoAnnotations.openMocks(this);
        LocalDateTime timeElapsed = LocalDateTime.now().minusDays(7);
        List<Freelancer> expectedFreelancers = Arrays.asList(
                new Freelancer(1L, "John", "Doe", "Male", "DELETE", LocalDate.of(2007, 2, 7), LocalDateTime.now().minusDays(1)),
                new Freelancer(2L, "Jane", "Doe", "Female", "DELETE", LocalDate.parse("2006-06-26"),LocalDateTime.now().minusDays(2)),
                new Freelancer(3L, "Jane", "Smith", "Female", "DELETE", LocalDate.parse("2006-06-26"),LocalDateTime.now().minusDays(2))
        );
        when(freelancerRepository.findByDeletionTime(timeElapsed)).thenReturn(expectedFreelancers);
        List<Freelancer> actualFreelancers = freelancerService.getFreelancersMarkedForDeletionLast7Days();
        assertEquals(expectedFreelancers, actualFreelancers);
    }

}
