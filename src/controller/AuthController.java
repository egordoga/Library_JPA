package controller;

import entity.User;
import lombok.Data;
import service.ServiceDB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
@Data
public class AuthController {

    private User user = new User();
    private ServiceDB serviceDB = new ServiceDB();

    public String login() {
        User userFromDb = serviceDB.findUserByName(user.getUsername());
        if (userFromDb != null && userFromDb.getUsername().equals(user.getUsername()) &&
                userFromDb.getPassword().equals(user.getPassword())) {
            return "main";
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Логин или пароль не верны");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        facesContext.addMessage("login_form", message);

        return "index";
    }

    public boolean getIsAdmin() {
        return "admin".equals(user.getUsername());
    }
}
