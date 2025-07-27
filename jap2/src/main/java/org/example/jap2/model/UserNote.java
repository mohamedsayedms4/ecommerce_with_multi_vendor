package org.example.jap2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_notes")
@Data
public class UserNote {

    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note ;

    @Column(name = "user_id")
    private Integer userId;

    @JsonBackReference
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    @ManyToOne()
    private User a;


}
