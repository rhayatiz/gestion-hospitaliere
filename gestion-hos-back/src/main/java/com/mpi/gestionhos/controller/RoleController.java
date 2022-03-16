package com.mpi.gestionhos.controller;

import com.mpi.gestionhos.model.Role;
import com.mpi.gestionhos.model.User;
import com.mpi.gestionhos.service.RoleService;
import com.mpi.gestionhos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public List<Role> findAll(){
        return this.roleService.findAll();
    }


}
