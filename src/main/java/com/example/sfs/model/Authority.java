package com.example.sfs.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "AUTHORITY")
@ToString
@Getter
public class Authority {

    @Id
    @Column(name = "ROLE", length = 50)
    @NotNull
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return role == authority.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}