package com.mpi.gestionhos.controller;

import com.mpi.gestionhos.model.MedicalRecord;
import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.service.MedicalRecordService;
import com.mpi.gestionhos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/medicalRecord")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;
    private UserService userService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService, UserService userService) {
        this.medicalRecordService = medicalRecordService;
        this.userService = userService;
    }

    @PostMapping()
    public void createMedicalRecord(
        @RequestBody Map<String, Object> payload
    ){
        Integer userId = (Integer) payload.get("userId");
        MedicalRecord record = new MedicalRecord((String) payload.get("name"));
        System.out.println("Create medical record");
        Optional<User> u = this.userService.find(userId);
        if(u != null){
            record.setUser(u.get());
            medicalRecordService.create(record);
        }
    }

    @GetMapping("find/{id}")
    public Optional<MedicalRecord> find(
            @PathVariable("id") Long medicalRecordId
    ){
        return medicalRecordService.find(medicalRecordId);
    }



}
