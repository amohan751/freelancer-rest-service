package com.tadaah.freelancer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadaah.freelancer.entity.Freelancer;
import com.tadaah.freelancer.service.FreelancerService;
import com.tadaah.freelancer.service.NotificationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/freelancer")
public class FreelancerController {
    @Autowired
    private FreelancerService freelancerService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/register")
    public ResponseEntity<Freelancer> registerFreelancer(@RequestBody Freelancer freelancer) {
        freelancerService.createFreelancer(freelancer);
        notificationService.sendNotification("Freelancer with freelancerId :"+freelancer.getFreelancerId()+" registered");
        //return appropriate http response code
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    
    @PutMapping("/update/{freelancerId}")
    public ResponseEntity<Freelancer> updateFreelancer(@PathVariable Long freelancerId, @RequestBody Freelancer freelancer){
        Freelancer updatedFreelancer = freelancerService.updateFreelancer(freelancerId, freelancer);
        notificationService.sendNotification("Freelancer with freelancerId :"+freelancerId+" updated");
        return ResponseEntity.ok(updatedFreelancer);
    }

    @PutMapping("/verify/{freelancerId}")
    public ResponseEntity<Freelancer> verifyFreelancer(@PathVariable Long freelancerId){
        Freelancer updatedFreelancer = freelancerService.verifyFreelancer(freelancerId);
        notificationService.sendNotification("Freelancer with freelancerId :"+freelancerId+" verified");
        return ResponseEntity.ok(updatedFreelancer);
    }

    @GetMapping("/getFreelancers/{status}")
    public ResponseEntity<List<Freelancer>> getFreelancerByStatus(@PathVariable String status){
        List<Freelancer> freelancers = freelancerService.getFreelancersByStatus(status);
        return ResponseEntity.ok(freelancers);
    }

    @GetMapping("/getFreelancers/{freelancerId}")
    public ResponseEntity<List<Freelancer>> getFreelancerById(@PathVariable Long freelancerId){
        List<Freelancer> freelancers = freelancerService.getFreelancersByFreelancerId(freelancerId);
        return ResponseEntity.ok(freelancers);
    }

    @PutMapping("/deleteFreelancer/{freelancerId}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable Long freelancerId){
        String deleteMessage = freelancerService.deleteFreelancer(freelancerId);
        notificationService.sendNotification("Freelancer with freelancerId :"+freelancerId+" deleted");
        return ResponseEntity.ok(deleteMessage);
    }

    @GetMapping("/deletedInLast7Days")
    public ResponseEntity<List<Freelancer>> getPast7DaysDeleteFreelancers(){
        List<Freelancer> freelancers = freelancerService.getFreelancersMarkedForDeletionLast7Days();
        return ResponseEntity.ok(freelancers);
    }
    

}
