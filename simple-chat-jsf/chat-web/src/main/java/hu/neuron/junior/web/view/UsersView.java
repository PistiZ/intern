package hu.neuron.junior.web.view;

import hu.neuron.junior.client.api.service.user.UserService;
import hu.neuron.junior.client.api.vo.UserVo;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "usersView")
@ViewScoped
public class UsersView {

    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    private List<UserVo> users;

    @PostConstruct
    public void init() {
        setUsers(userService.findAll());

        WebApplicationContext ctx =  FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        SessionRegistry sessionRegistry = ctx.getBean(SessionRegistry.class);
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<String> userNames = new LinkedList<>();

        for (Object principal : principals) {
            if (principal instanceof User) {
                userNames.add(((User) principal).getUsername());
            }
        }

        for (UserVo user : users) {
            user.setActive(Boolean.FALSE);

            for (String username : userNames) {
                if (username.equals(user.getUsername())) {
                    user.setActive(Boolean.TRUE);
                }
            }
        }
    }

    public UserVo getCurrentUser() {
        UserVo uservo = new UserVo();

        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
            String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            uservo = userService.findByUsername(username);
        }

        return uservo;
    }

    public List<UserVo> getUsers() {
        return users;
    }

    public void setUsers(List<UserVo> users) {
        this.users = users;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
