package com.tadaah.freelancer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadaah.freelancer.entity.Freelancer;
import com.tadaah.freelancer.repository.FreelancerRepository;

@Service
public class FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;
    public static final Logger logger = LoggerFactory.getLogger(FreelancerService.class);
    public static final String NEW_FREELANCER = "NEW_FREELANCER";
    public static final String VERIFIED_BY_STAFF = "VERIFIED";
    public static final String MARKED_TO_DELETE = "DELETE";

    //persist new freelancer registrations
    public Freelancer createFreelancer(Freelancer freelancer){
        try{
            freelancer.setStatus(NEW_FREELANCER);
            return freelancerRepository.save(freelancer);
        }catch(Exception e){
            logger.error("Error creating freelancer: {}", e.getMessage(), e);
            throw new ServiceException("Error creating freelancer", e);
        }
        
    }

    //take in any updates to the freelancer data after registration
    public Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerUpdate){
        try{
            Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
            freelancer.setFirstName(freelancerUpdate.getFirstName());
            freelancer.setLastName(freelancerUpdate.getLastName());
            freelancer.setGender(freelancerUpdate.getGender());
            freelancer.setDateOfBirth(freelancerUpdate.getDateOfBirth());
            return freelancerRepository.save(freelancer);
        }catch(Exception e){
            logger.error("Error updating freelancer: {}", e.getMessage(), e);
            throw new ServiceException("Error updating freelancer", e);
        }
        
    }

    //updates the freelancer data after verification
    public Freelancer verifyFreelancer(Long freelancerId){
        try{
            Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
            freelancer.setStatus(VERIFIED_BY_STAFF);
            return freelancerRepository.save(freelancer);
        }catch(Exception e){
            logger.error("Error verifying freelancer: {}", e.getMessage(), e);
            throw new ServiceException("Error verifying freelancer", e);
        }
        
    }

    //get new freelancers for staff user
    public List<Freelancer> getFreelancersByStatus(String status){
        return freelancerRepository.findByStatus(status);
    }

    //get freelancer by id
    public List<Freelancer> getFreelancersByFreelancerId(Long id){
        return freelancerRepository.findByFreelancerId(id);
    }

    //mark freelancer for deletion by id
    public String deleteFreelancer(Long freelancerId){
        try{
            Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
            freelancer.setStatus(MARKED_TO_DELETE);
            freelancer.setDeletionTime(LocalDateTime.now());
            freelancerRepository.save(freelancer);
            return "Freelancer data for "+freelancerId+" marked for deletion successfully";
        }catch(Exception e){
            logger.error("Error deleting freelancer: {}", e.getMessage(), e);
            throw new ServiceException("Error deleting freelancer");
        }

    }

    //fetch freelancers marked for deletion in the past 7 days
    public List<Freelancer> getFreelancersMarkedForDeletionLast7Days(){
        LocalDateTime elapsedTime = LocalDateTime.now().minusDays(7);
        return freelancerRepository.findByDeletionTime(elapsedTime);
    }

}
