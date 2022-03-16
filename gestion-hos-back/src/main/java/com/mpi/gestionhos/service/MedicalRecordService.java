package com.mpi.gestionhos.service;

import com.mpi.gestionhos.model.MedicalRecord;
import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordService(MedicalRecordRepository medicalRecordRepository){ this.medicalRecordRepository = medicalRecordRepository; }

    public void create(MedicalRecord medicalRecord){
        System.out.println(medicalRecord.getName());
        System.out.println("MedicalServiceService@create-----creating medicalRecord");
        medicalRecordRepository.save(medicalRecord);
    }

    public Optional<MedicalRecord> find(long id){
        return medicalRecordRepository.findById(id);
    }

}
