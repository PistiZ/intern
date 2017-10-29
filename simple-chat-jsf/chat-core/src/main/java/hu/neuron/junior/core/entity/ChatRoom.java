package hu.neuron.junior.core.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class ChatRoom extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic
    private String name;

    @ManyToMany
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
