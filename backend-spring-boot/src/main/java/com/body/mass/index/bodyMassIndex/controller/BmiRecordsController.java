package com.body.mass.index.bodyMassIndex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import com.body.mass.index.bodyMassIndex.model.BMIRecords;
import com.body.mass.index.bodyMassIndex.repository.BmiRecordsRepository;

@CrossOrigin    
@RestController
@RequestMapping("/api/bmi-records")

public class BmiRecordsController {

    @Autowired
    private final BmiRecordsRepository BmiRecordsRepository;

    
    public BmiRecordsController(BmiRecordsRepository BmiRecordRepository) {
        this.BmiRecordsRepository = BmiRecordRepository;
    }
    @PostMapping("/adduser")
    public BMIRecords addBmiRecords(@RequestBody BMIRecords bmiRecord){
        return BmiRecordsRepository.save(bmiRecord);
    }

    @GetMapping("/listUser")
    public List<BMIRecords> getAllBmiRecords() {
        return BmiRecordsRepository.findAll();
    }
    @PutMapping("/updateData/{id}")
    public ResponseEntity<?> update(@PathVariable long id){
        return ResponseEntity.ok(((BmiRecordsController) BmiRecordsRepository).update(id));
    } 

    @DeleteMapping("deleteData/{id}")
    public void delete(@PathVariable long id){
        BmiRecordsRepository.deleteById(id); 
    }
   
}
