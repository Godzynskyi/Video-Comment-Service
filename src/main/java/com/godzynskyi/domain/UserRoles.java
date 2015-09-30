package com.godzynskyi.domain;

import javax.persistence.*;

/**
 * Created by Java Developer on 30.09.2015.
 */
@Entity
@Table(name = "user_roles")
public class UserRoles {
    public static final Short USER_ROLE = 1;
    public static final Short ADMIN_ROLE = 2;
    public static final Short GUEST_ROLE = 3;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private long userId;

    @Column
    private short role;


}
