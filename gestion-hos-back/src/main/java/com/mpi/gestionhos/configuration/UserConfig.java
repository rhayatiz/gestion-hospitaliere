package com.mpi.gestionhos.configuration;

import com.mpi.gestionhos.model.Role;
import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.repository.RoleRepository;
import com.mpi.gestionhos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class UserConfig {
    //Dummy data, inserted each time the server starts

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return args -> {
            Role admininistration = new Role(
                    "Administrateur"
            );
            Role patient = new Role(
                    "Patient"
            );
            Role docteur = new Role(
                    "Docteur"
            );
            Role infirmier = new Role(
                    "Infirmier"
            );
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(admininistration);
            roles.add(patient);
            roles.add(docteur);
            roles.add(infirmier);
            roleRepository.saveAll(
                roles
            );

            User u1 = new User(
                    "John",
                    "Doe",
                    "johndoe@mail.com",
                    LocalDate.of(1996, 12, 01),
                    "123+aze"
            );
            u1.setRole(admininistration);
            User u2 = new User(
                    "Alex",
                    "Roe",
                    "alexroe@mail.com",
                    LocalDate.of(1980, 10, 01),
                    "123+aze"
            );
            u2.setRole(patient);
            User u3 = new User(
                    "Simon",
                    "Buisson",
                    "simonbuisson@mail.com",
                    LocalDate.of(1976, 01, 01),
                    "123+aze"
            );
            u3.setRole(patient);
            ArrayList<User> users = new ArrayList<>();
            users.add(u1);
            users.add(u2);
            users.add(u3);
            userRepository.saveAll(
                    users
            );
        };
    }

}
