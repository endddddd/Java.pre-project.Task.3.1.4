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
public class ServerRestController {

    @Autowired
    private UserService userService;

    // При запросе на авторизацию создаем панель Админа и Юзера для Теста
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ResponseEntity<User> getUser(@RequestParam String email) {
        userService.addAdminAndUserPanel();
        return new ResponseEntity<>(userService.getUserByName(email), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    public ResponseEntity<User> removeUser(@RequestBody UserDTO userDTO) {
        userService.removeUser(userDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(userService.ifPasswordNull(userDTO.getId(),userDTO.getPassword()));
        userService.updateUser(new User(userDTO));
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(new User(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}