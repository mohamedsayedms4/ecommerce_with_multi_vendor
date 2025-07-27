package org.example.jap2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_address")
@Data
public class UserAddress {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "user_id")
    private Integer userId;

    @JsonBackReference
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    @OneToOne
    private User user ;
}
