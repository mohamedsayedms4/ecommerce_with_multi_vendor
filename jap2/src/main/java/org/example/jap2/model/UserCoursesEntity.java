package org.example.jap2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCoursesEntity {

    @Id
    @Column(name = "uc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ucId;

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "course_id")
    private Integer courseId;


    @Transient
    private String courseName;

    public String getCourseName() {
        return this.course != null ? this.course.getCourseName() : null;
    }

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "course_id",insertable = false,updatable = false)
    private Course course;
}
