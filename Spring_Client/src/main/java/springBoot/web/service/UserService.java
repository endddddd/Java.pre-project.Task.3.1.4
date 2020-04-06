package springBoot.web.service;

import org.springframework.http.ResponseEntity;
import springBoot.web.model.Role;
import springBoot.web.model.UserDTO;

import java.util.Set;

public interface UserService {
    public ResponseEntity<String> getAllUsers();

    public void addUser(UserDTO userDTO);

     public void removeUser(UserDTO userDTO);

    public void updateUser(UserDTO userDTO);

//    public User getUserById(long id);



//    public String ifPasswordNull(Long id, String password);



}
