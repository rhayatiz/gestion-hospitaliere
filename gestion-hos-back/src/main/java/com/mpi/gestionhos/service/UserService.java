package com.mpi.gestionhos.service;

import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> find(long id){
        return userRepository.findById(id);
    }

    public List<User> findByRole(String role){return userRepository.findUsersByRole(role);}

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void delete(long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new IllegalStateException("User with id "+id+" not found.");
        }
    }

    public void createUser(User u){
        System.out.println(u.toString());
        System.out.println("UserService@createUser-----creating new user");
        Optional<User> user = userRepository.findUserByEmail(u.getEmail());

        if(user.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(u);
    }

    @Transactional
    public void updateUser(
            Long id,
            String firstName,
            String lastName
    ){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id "+id+" not found."));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)){
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)){
            user.setLastName(lastName);
        }
    }



}
