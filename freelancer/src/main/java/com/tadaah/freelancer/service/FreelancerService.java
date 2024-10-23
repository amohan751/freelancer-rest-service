package com.tadaah.freelancer.service;

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
        freelancer.setStatus("NEW_FREELANCER");
        return freelancerRepository.save(freelancer);
    }

    //take in any updates to the freelancer data after registration
    public Freelancer updatFreelancer(Long freelancerId, Freelancer freelancerUpdate){
        Freelancer freelancer = freelancerRepository.findById(freelancerId).orElseThrow();
        freelancer.setFirstName(freelancerUpdate.getFirstName());
        freelancer.setLastName(freelancerUpdate.getLastName());
        freelancer.setGender(freelancerUpdate.getGender());
        freelancer.setDateOfBirth(freelancerUpdate.getDateOfBirth());
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

    //delete freelancer by id
    public String deleteFreelancer(Long id){
        //try catch
        freelancerRepository.deleteById(id);
        return "deleted freelancer data for "+id+" successfully";
    }

}
