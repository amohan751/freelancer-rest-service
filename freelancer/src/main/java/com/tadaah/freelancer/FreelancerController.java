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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/freelancer")
public class FreelancerController {
    @Autowired
    private FreelancerService freelancerService;

    @PostMapping("/register")
    public ResponseEntity<Freelancer> registerFreelancer(@RequestBody Freelancer freelancer) {
        freelancerService.createFreelancer(freelancer);
        //async
        //return appropriate http response code
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    
    @PutMapping("/update/{freelancerId}")
    public ResponseEntity<Freelancer> updateFreelancer(@PathVariable Long id, @RequestBody Freelancer freelancer){
        Freelancer updatedFreelancer = freelancerService.updatFreelancer(id, freelancer);
        //async
        return ResponseEntity.ok(updatedFreelancer);
    }

    @GetMapping("/getFreelancers/{status}")
    public ResponseEntity<List<Freelancer>> getFreelancerByStatus(@PathVariable String status){
        List<Freelancer> freelancers = freelancerService.getFreelancersByStatus(status);
        return ResponseEntity.ok(freelancers);
    }

    @GetMapping("/getFreelancers/{freelancerId}")
    public ResponseEntity<List<Freelancer>> getFreelancerByStatus(@PathVariable Long id){
        List<Freelancer> freelancers = freelancerService.getFreelancersByFreelancerId(id);
        return ResponseEntity.ok(freelancers);
    }

    @DeleteMapping("/deleteFreelancer/{freelancerId}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable Long id){
        String deleteMessage = freelancerService.deleteFreelancer(id);
        //async
        return ResponseEntity.ok(deleteMessage);
    }
    

}
