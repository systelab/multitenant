package com.werfen.laboratory.features.user.model;

import com.werfen.laboratory.core.model.ModelBase;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Entity
@Audited
@Table(name = "users")
public class User  extends ModelBase{

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_surname")
    private String surname;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "user_login", length = 10, nullable = false, unique = true)
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @NotNull
    private UserRole role;

    public User() {
        super();
        this.role = UserRole.USER;
    }

    public User(UUID id, String name, String surname, String login) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.role = UserRole.USER;
    }
}