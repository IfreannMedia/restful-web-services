package com.freann.rest.webservices.restfulwebservices.user;

import com.freann.rest.webservices.restfulwebservices.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

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
    public EntityModel<User> findById(@PathVariable Integer id) {
        User user = this.userDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
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
