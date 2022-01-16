package com.freann.rest.webservices.restfulwebservices.user;

import com.freann.rest.webservices.restfulwebservices.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("jpa/users")
public class UserJPAResource {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired PostRepository postRepository;

    @GetMapping("")
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> findById(@PathVariable Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        this.userRepository.deleteById(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostsFromUser(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get().getPosts();
        } else {
            throw new UserNotFoundException("no user found with id: " + id);
        }
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createNewPostForUser(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            post.setUser(user);
            this.postRepository.save(post);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(post.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            throw new UserNotFoundException("no user found with id: " + id);
        }
    }


}
