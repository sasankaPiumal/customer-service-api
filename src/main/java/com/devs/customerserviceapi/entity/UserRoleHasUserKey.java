package com.devs.customerserviceapi.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserKey implements Serializable {

    @Column(name = "user_id",length = 45)
    private String userId;

    @Column(name = "user_role_id",length = 45)
    private String roleId;

}
