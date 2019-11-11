package com.epam.smyrnov.entity.user;

import com.epam.smyrnov.entity.Entity;
import com.epam.smyrnov.entity.user.Role;

import java.util.Objects;

public class User extends Entity {

    private static final long serialVersionUID = 985472332498498456L;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isBlocked;
    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isBlocked == user.isBlocked &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, firstName, lastName, isBlocked, role);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User id: ").append(id).append("<br>");
        stringBuilder.append("Email: ").append(email).append("<br>");
        stringBuilder.append("Password: ").append(password).append("<br>");
        stringBuilder.append("First name: ").append(firstName).append("<br>");
        stringBuilder.append("Last name: ").append(lastName).append("<br>");
        stringBuilder.append("Blocked: ").append(isBlocked).append("<br>");
        stringBuilder.append("Role: ").append(role.value()).append("<br>");
        return stringBuilder.toString();
    }
}
