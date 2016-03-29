package jpa.onetomany.example.dao;

import javax.persistence.*;

/**
 * Created by rahulc on 3/29/16.
 */

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    private School school;

    @Column(name = "name", length = 100, nullable = false, unique = false)
    private String name;

    public long getStudentId() {
        return studentId;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
