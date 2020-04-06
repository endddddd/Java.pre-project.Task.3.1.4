package springBoot.web.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String password;
    private Set<Role> roles;


    public User(long id, String firstName, String lastName, String email, int age, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.age = userDTO.getAge();
        this.password = userDTO.getPassword();
        this.roles = setRoles(userDTO.getRoles());
    }

    public User(String firstName, String lastName, String email, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles.toString().replaceAll("\\p{P}", "");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

// Принимаем строку с ролями из userDTO и распределяем роли для User
    public Set<Role> setRoles(String role) {
        Set<Role> roles = new HashSet<>();
        try {
            String[] partsRole = role.split("[, ]");
            roles.add(new Role(partsRole[1]));
            roles.add(new Role(partsRole[0]));
            return this.roles = roles;
        } catch (Exception e) {

        }
        roles.add(new Role(role));
        return this.roles = roles;
    }
}
