package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Post> posts;
}
