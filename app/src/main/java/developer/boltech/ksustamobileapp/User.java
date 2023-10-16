package developer.boltech.ksustamobileapp;

public class User {

    public String fullName, emailAddress, admissionNo, phone, password;

    public User() {

    }

    public User(String fullName, String email, String admissionNo, String phone, String password) {
        this.fullName = fullName;
        this.emailAddress = email;
        this.admissionNo = admissionNo;
        this.phone = phone;
        this.password = password;
    }
}
