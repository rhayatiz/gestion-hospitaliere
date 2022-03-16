package com.mpi.gestionhos;

import com.mpi.gestionhos.configuration.MyPasswordEncoder;
import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    private MyPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        System.out.println("Authcontroller@addUser");
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthDate());
//        System.out.println(passwordEncoder.encoder().encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/login";
    }

    //Page de connexion
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    //Page d'inscription
    @RequestMapping(value = "/inscription", method = RequestMethod.GET)
    public String getRegisterPagePage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    //Authentification
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RedirectView login(@ModelAttribute User user, RedirectAttributes redir){
        //username not found
        if (!user.getEmail().equalsIgnoreCase("admin")) {
            RedirectView redirectView = new RedirectView("/login", true);
            redir.addFlashAttribute("error", "Identifiants incorrects.");
            return redirectView;
        }else{
//            //compare user hashed pw vs input pw
//            //https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
            if(!user.getPassword().equals("123+aze")) {
                RedirectView redirectView = new RedirectView("/login", true);
                redir.addFlashAttribute("error", "Identifiants incorrects.");
                return redirectView;
            }else{
                //success
                RedirectView redirectView = new RedirectView("/", true);
                return redirectView;
            }
        }
    }

}
