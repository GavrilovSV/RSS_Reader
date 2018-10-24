package edu.gavrilov.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {

    @NotNull
    @Email(message = "Email определен в неверном формате")
    private String username;

    @NotNull
    @Size(min = 5, max = 30, message = "Размер должен быть между 5 и 30 символами")
    private String password;

    @NotNull
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
