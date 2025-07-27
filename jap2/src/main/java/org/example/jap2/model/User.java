package org.example.jap2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="is_active")
    private Integer isActive;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private UserAddress userAddress;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "a")
    private List<UserNote>  userNotes;



    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<UserCoursesEntity> userCoursesEntities;
}
