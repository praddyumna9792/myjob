package com.pixel7.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll()
    {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJodById(@PathVariable Long id)
    {
        Job job = jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id)
    {
        boolean isDeleted = jobService.delJobById(id);
        if(isDeleted)
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
//    @RequestMapping(value = "jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob)
    {
        boolean isUpdated = jobService.updateJobById(id, updatedJob);
        if(isUpdated)
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
