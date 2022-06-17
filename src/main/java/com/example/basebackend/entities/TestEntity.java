package com.example.basebackend.entities;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
@Builder
public class TestEntity extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
