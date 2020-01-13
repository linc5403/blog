package club.banyuan.form;

import club.banyuan.bean.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserRegisterForm {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, max = 30)
    private String name;
    @Size(min = 6, max = 30)
    private String password;
    @Email
    private String email;
    public User toUser() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPassword(this.password);
        return user;
    }
}
