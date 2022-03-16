package com.mpi.gestionhos.repository;

import com.mpi.gestionhos.model.Role;
import com.mpi.gestionhos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
