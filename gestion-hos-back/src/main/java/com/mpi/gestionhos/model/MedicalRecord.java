package com.mpi.gestionhos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    public MedicalRecord(){}

    public MedicalRecord(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnoreProperties("medicalRecords")
    public User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public long id;

    @Column(name = "name")
    public String name;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
