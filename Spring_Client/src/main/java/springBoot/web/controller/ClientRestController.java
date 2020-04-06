package springBoot.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBoot.web.model.User;
import springBoot.web.model.UserDTO;
import springBoot.web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/*")
public class ClientRestController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public ResponseEntity<String> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    public ResponseEntity<User> removeUser(@RequestParam Long id) {
        userService.removeUser(new UserDTO(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestParam Long id, String firstName, String password, String lastName, String email, int age, String role) {
        userService.updateUser(new UserDTO(id, firstName, lastName, email, age, password, role));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestParam String firstName, String password, String lastName, String email, int age, String role) {
        UserDTO userDTO = new UserDTO(firstName, lastName, email, age, password, role);
        userService.addUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/getUser", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUser(HttpSession session) {
        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}