package hu.neuron.junior.web.view;

import hu.neuron.junior.client.api.service.user.UserService;
import hu.neuron.junior.client.api.vo.UserVo;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "registrationView")
@ViewScoped
public class RegistrationView {

    private static final Logger LOGGER = Logger.getLogger(RegistrationView.class);

    @ManagedProperty("#{userService}")
    private UserService userService;

    private UserVo userVo;

    private UploadedFile uploadedFile;

    @PostConstruct
    public void init() {
        userVo = new UserVo();
    }

    public void register() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            UserVo check = userService.findByUsername(userVo.getUsername());

            if (check != null) {
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "Username is already in use!"));

            } else {

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String encryptedPass = bCryptPasswordEncoder.encode(userVo.getPassword());
                userVo.setPassword(encryptedPass);

                userService.registerUser(userVo);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Registration!"));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "Registration!"));

        }
    }

    public void imageUpload(FileUploadEvent fileUploadEvent) {
        uploadedFile = fileUploadEvent.getFile();
        userVo.setImage(uploadedFile.getContents());
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
