package jpa.onetoone.example.dao;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "school", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Chairman chairman;

    public Chairman getChairman() {
        return chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
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
