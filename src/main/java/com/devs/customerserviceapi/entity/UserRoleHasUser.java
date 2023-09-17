package com.devs.customerserviceapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role_has_user")
public class UserRoleHasUser implements Serializable {

    @EmbeddedId
    private UserRoleHasUserKey id = new UserRoleHasUserKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id",nullable = false)
    private UserRole userRole;


}