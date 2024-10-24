package com.tadaah.freelancer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadaah.freelancer.entity.Freelancer;
import com.tadaah.freelancer.repository.FreelancerRepository;

@Service
public class FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;

    //persist new freelancer registrations
    public Freelancer createFreelancer(Freelancer freelancer){
        //create enum/constants
        freelancer.setStatus("NEW_FREELANCER");
        return freelancerRepository.save(freelancer);
    }

    //take in any updates to the freelancer data after registration
    public Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerUpdate){
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
        freelancer.setFirstName(freelancerUpdate.getFirstName());
        freelancer.setLastName(freelancerUpdate.getLastName());
        freelancer.setGender(freelancerUpdate.getGender());
        freelancer.setDateOfBirth(freelancerUpdate.getDateOfBirth());
        return freelancerRepository.save(freelancer);
    }

    //updates the freelancer data after verification
    public Freelancer verifyFreelancer(Long freelancerId){
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
        freelancer.setStatus("VERIFED");
        return freelancerRepository.save(freelancer);
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
        //try catch
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
        freelancer.setStatus("DELETE");
        freelancer.setDeletionTime(LocalDateTime.now());
        freelancerRepository.save(freelancer);
        return "Freelancer data for "+freelancerId+" marked for deletion successfully";
    }

    //fetch freelancers marked for deletion in the past 7 days
    public List<Freelancer> getFreelancersMarkedForDeletionLast7Days(){
        LocalDateTime elapsedTime = LocalDateTime.now().minusDays(7);
        return freelancerRepository.findByDeletionTime(elapsedTime);
    }

}
