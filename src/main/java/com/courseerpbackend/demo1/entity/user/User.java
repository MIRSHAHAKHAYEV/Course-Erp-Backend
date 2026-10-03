package com.courseerpbackend.demo1.entity.user;

import com.courseerpbackend.demo1.entity.base.BaseEntity;
import com.courseerpbackend.demo1.entity.enums.user.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import lombok.AccessLevel;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "surname", nullable = false, length = 100)
    String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    UserStatus status;

    @Column(name = "role_id", nullable = false)
    Long roleId;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    String email;

    @Column(name = "phone_number", length = 20)
    String phoneNumber;

    @Column(name = "password", nullable = false, length = 255)
    String password;
}