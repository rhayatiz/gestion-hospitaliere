package com.mpi.gestionhos;

import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.model.UserLoginRequestBody;
import com.mpi.gestionhos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void registerStudent(@RequestBody User u){
        this.userService.createUser(u);
    }

    @GetMapping("find/{id}")
    public User find(@PathVariable int id){
        System.out.println(id);
        Optional<User> u = this.userService.find((long) id);
        System.out.println(u.toString());
        if(u.isPresent()){
            return u.get();
        }else{
            return new User(
                    "faker",
                    "faker",
                    "faker@mail.com",
                    LocalDate.of(1976, 01, 01),
                    "123+aze"
            );
        }
    }

    @GetMapping()
    public List<User> findAll(){
        List<User> users = this.userService.findAll();
        for(User user : users){
            System.out.println(user.toString());
        }
        return users;
    }

    @DeleteMapping("{userId}")
    public void DeleteUser(@PathVariable("userId") Long id){
        userService.delete(id);
    }

    @PutMapping("{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName
    ){
        userService.updateUser(
                userId,
                firstName,
                lastName
        );
    }

    @PostMapping("login")
    public String login(
            @RequestBody UserLoginRequestBody user
    ){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return "hello";
    }
}
