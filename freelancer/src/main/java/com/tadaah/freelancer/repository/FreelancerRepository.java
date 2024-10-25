package com.tadaah.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tadaah.freelancer.entity.Freelancer;
import java.util.List;
import java.time.LocalDateTime;



public interface FreelancerRepository extends JpaRepository <Freelancer, Long> {

    List<Freelancer> findByFreelancerId(long freelancerId);
    List<Freelancer> findByStatus(String status);
    List<Freelancer> findByDeletionTime(LocalDateTime deletionTime);
} 
