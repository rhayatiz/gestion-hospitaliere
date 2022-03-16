package com.mpi.gestionhos.controller;

import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {

    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("get/anon")
    public String getAnonPage(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        if (name != null){
            // Model : Pour passer des variables à la vue
            model.addAttribute("name", name);
        }
        return "anon";
    }

}
