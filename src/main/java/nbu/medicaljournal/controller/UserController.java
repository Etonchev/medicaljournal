package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.User;
import nbu.medicaljournal.api.request.NewUserRequest;
import nbu.medicaljournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@Api(tags = "user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "List users", notes = "List all users")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get user", notes = "Get user")
    public User getUser(
            @PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Add user", notes = "Add a new user")
    public User addUser(@Validated @RequestBody NewUserRequest user) {
        return userService.addUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete user", notes = "Delete a user")
    public void deletePatient(
            @PathVariable("id") String id) {
        userService.deleteUser(id);
    }
}