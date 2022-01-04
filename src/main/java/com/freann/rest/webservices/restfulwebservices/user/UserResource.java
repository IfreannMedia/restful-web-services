package com.freann.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return this.userDaoService.findById(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        this.userDaoService.saveUser(user);
    }

}
