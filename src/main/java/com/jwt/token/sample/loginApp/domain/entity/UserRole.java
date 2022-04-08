package com.jwt.token.sample.loginApp.domain.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Long userId;
    private Long roleId;
}
