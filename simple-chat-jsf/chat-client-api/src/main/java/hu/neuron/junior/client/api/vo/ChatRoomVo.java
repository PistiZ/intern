package hu.neuron.junior.client.api.vo;

import java.io.Serializable;
import java.util.List;

public class ChatRoomVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private List<UserVo> users;

    public String getUserNames() {
        StringBuilder stringBuilder = new StringBuilder();

        for (UserVo user : users) {
            stringBuilder.append(user.getFullName()).append(", ");
        }

        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserVo> getUsers() {
        return users;
    }

    public void setUsers(List<UserVo> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
