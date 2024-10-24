package com.tadaah.freelancer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tadaah.freelancer.entity.Freelancer;
import com.tadaah.freelancer.service.FreelancerService;

@WebMvcTest(FreelancerController.class)
public class FreelancerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FreelancerService freelancerService;

    @Test
    public void testCreateFreelancer() throws Exception {
        //Freelancer freelancer = new Freelancer((Long) null, "John", "Doe", "Male", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);
        Freelancer savedFreelancer = new Freelancer(1L, "John", "Doe", "Male", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);

        when(freelancerService.createFreelancer(any(Freelancer.class))).thenReturn(savedFreelancer);

        mockMvc.perform(post("/freelancers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"gender\":\"Male\",\"status\":\"NEW_FREELANCER\",\"dateOfBirth\":\"1990-01-01\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"freelancerId\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"gender\":\"Male\",\"status\":\"Active\",\"dateOfBirth\":\"1990-01-01\"}"));
    }

    @Test
    public void testUpdateFreelancer() throws Exception {
        Freelancer updatedFreelancer = new Freelancer(1L, "John", "Smith", "Male", "NEW_FREELANCER", LocalDate.of(1990, 1, 1), null);

        when(freelancerService.updateFreelancer(any(Long.class), any(Freelancer.class))).thenReturn(updatedFreelancer);

        mockMvc.perform(put("/freelancers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"Male\",\"status\":\"NEW_FREELANCER\",\"dateOfBirth\":\"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"freelancerId\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"Male\",\"status\":\"NEW_FREELANCER\",\"dateOfBirth\":\"1990-01-01\"}"));
    }
	
	@Test
    public void testVerifyFreelancer() throws Exception {
        Freelancer updatedFreelancer = new Freelancer(1L, "John", "Smith", "Male", "VERIFIED", LocalDate.of(1990, 1, 1), null);

        when(freelancerService.verifyFreelancer(any(Long.class))).thenReturn(updatedFreelancer);

        mockMvc.perform(put("/freelancers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"Male\",\"status\":\"NEW_FREELANCER\",\"dateOfBirth\":\"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"freelancerId\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"gender\":\"Male\",\"status\":\"VERIFIED\",\"dateOfBirth\":\"1990-01-01\"}"));
    }

}
