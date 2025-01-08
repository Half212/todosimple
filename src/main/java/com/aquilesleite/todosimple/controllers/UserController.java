package com.aquilesleite.todosimple.controllers;
import com.aquilesleite.todosimple.models.User;
import com.aquilesleite.todosimple.models.dto.UserCreateDTO;
import com.aquilesleite.todosimple.models.dto.UserUpdateDTO;
import com.aquilesleite.todosimple.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserCreateDTO obj) {
        User user = this.userService.fromDTO(obj);
        User newUser =this.userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
        .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Valid
        public ResponseEntity<Void> update (@Valid @RequestBody UserUpdateDTO obj, @PathVariable Long id){
        obj.setId(id);
        User user = this.userService.fromDTO(obj);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
