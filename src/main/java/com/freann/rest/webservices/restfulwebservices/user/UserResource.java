package com.freann.rest.webservices.restfulwebservices.user;

import com.freann.rest.webservices.restfulwebservices.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public User findById(@PathVariable Integer id) {
        User user = this.userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = this.userDaoService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        User deletedUser = this.userDaoService.deleteById(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }

}
