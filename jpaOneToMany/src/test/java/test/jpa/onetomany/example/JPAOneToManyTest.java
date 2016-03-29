package test.jpa.onetomany.example;

import jpa.onetomany.example.dao.School;
import jpa.onetomany.example.dao.Student;
import org.junit.Before;
import org.junit.Test;
import test.jpa.onetomany.example.dao.manager.InMemoryUnit;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rahulc on 3/29/16.
 */
public class JPAOneToManyTest extends InMemoryUnit {
    private String schoolName;

    @Before
    public void setup() throws Exception {
        super.setup();
        schoolName = "test School";
        School school = createNewSchool();
        addStudentsToSchool(school);
    }

    @Test
    public void table_should_have_only_one_school() {
        List<School> schoolList = searchSchoolUsingSchoolName();

        int onlyOneSchoolInList = 1;

        assertEquals(onlyOneSchoolInList, schoolList.size());
    }

    @Test
    public void should_find_two_students_in_school_entity() {
        List<School> schoolList = searchSchoolUsingSchoolName();

        School searchedSchool = schoolList.get(0);
        int twoStudentsInSchool = 2;

        assertEquals(twoStudentsInSchool, searchedSchool.getStudentList().size());
    }

    @Test
    public void should_remove_stored_oneToMany_relationship_entity() {
        List<School> schoolList = searchSchoolUsingSchoolName();

        entityManager.remove(schoolList.get(0));

        schoolList = searchSchoolUsingSchoolName();

        int emptyList = 0;
        assertEquals(emptyList, schoolList.size());
    }

    private List<School> searchSchoolUsingSchoolName() {
        return entityManager.createQuery("select s from School s where s.name = :schoolName")
                .setParameter("schoolName", schoolName)
                .getResultList();
    }

    private School createNewSchool() {
        School school = new School();
        school.setName(schoolName);
        entityManager.persist(school);
        return school;
    }

    private void addStudentsToSchool(School school) {
        for (int i = 1; i <= 2; i++) {
            Student student = new Student();
            student.setName(Integer.toString(i));
            student.setSchool(school);
            school.getStudentList().add(student);
            entityManager.persist(school);
        }
        entityManager.flush();
    }
}
