package jpa.onetoone.example.dao;

import javax.persistence.*;

/**
 * Created by rahulc on 3/29/16.
 */

@Entity
@Table(name = "chairman")
public class Chairman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chairmanId;

    @OneToOne
    @JoinColumn(name = "schoolId")
    private School school;

    @Column(name = "name", length = 100, nullable = false, unique = false)
    private String name;

    public long getChairmanId() {
        return chairmanId;
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
