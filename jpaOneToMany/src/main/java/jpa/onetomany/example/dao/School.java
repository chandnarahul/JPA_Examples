package jpa.onetomany.example.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahulc on 3/29/16.
 */

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schoolId", unique = true, nullable = false)
    private long schoolId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "school", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Student> studentList;

    public List<Student> getStudentList() {
        if (studentList == null) {
            this.studentList = new ArrayList<>();
        }
        return studentList;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
