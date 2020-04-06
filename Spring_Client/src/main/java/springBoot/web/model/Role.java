package springBoot.web.model;

import org.springframework.security.core.GrantedAuthority;




public class Role implements GrantedAuthority {

    private long id;
    private String name;
    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
