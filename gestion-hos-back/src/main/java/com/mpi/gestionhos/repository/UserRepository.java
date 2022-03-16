package com.mpi.gestionhos.repository;

import com.mpi.gestionhos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN Role r " +
            "WHERE u.role = r " +
            "AND r.name = ?1")
    List<User> findUsersByRole(String role);
}
