package com.epam.smyrnov.users.model;

public interface User {

    Long getId();

    void setId(Long id);

    String getEmail();

    void setEmail(String email);

//    String getPasswordHash();
//
//    void setPasswordHash(String passwordHash);
//
//    String getPasswordSalt();
//
//    void setPasswordSalt(String passwordSalt);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

//    Boolean getBlocked();
//
//    void setBlocked(Boolean blocked);

    Boolean getVerified();

    void setVerified(Boolean verified);

//    Role getRole();
//
//    void setRole(Role role);
}
