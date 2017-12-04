package university.courier;

public class User {
    public String name;
    public String login;
    public String password;
    public String role;
    public String phone;

    public User(String name, String login, String password, String phone, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
