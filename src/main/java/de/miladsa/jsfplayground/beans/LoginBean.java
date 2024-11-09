package de.miladsa.jsfplayground.beans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    private String phoneNumber;
    private String nationalId;
    
    public String loginWithPhoneNationalId() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("nationalId", nationalId);
            request.login(phoneNumber, nationalId);
            return "/home.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Invalid credentials for phone number and national ID",
                            null));
            return null;
        }
    }
}
