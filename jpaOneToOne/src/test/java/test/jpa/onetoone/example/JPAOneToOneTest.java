package test.jpa.onetoone.example;

import jpa.onetoone.example.dao.Chairman;
import jpa.onetoone.example.dao.School;
import org.junit.Before;
import org.junit.Test;
import test.jpa.onetoone.example.dao.manager.InMemoryUnit;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rahulc on 3/29/16.
 */
public class JPAOneToOneTest extends InMemoryUnit {
    private String schoolName;
    private String chairmanName;

    @Before
    public void setup() throws Exception {
        super.setup();
        schoolName = "test School";
        chairmanName = "test chairman";
        School school = createNewSchool();
        addChairmanToSchool(school);
    }

    @Test
    public void table_should_have_only_one_school() {
        List<School> schoolList = searchSchoolUsingSchoolName();

        int onlyOneSchoolInList = 1;

        assertEquals(onlyOneSchoolInList, schoolList.size());
    }

    @Test
    public void should_find_stored_oneToOne_relationship_entity() {
        List<School> schoolList = searchSchoolUsingSchoolName();

        School searchedSchool = schoolList.get(0);

        assertEquals(chairmanName, searchedSchool.getChairman().getName());
    }

    @Test
    public void should_remove_stored_oneToOne_relationship_entity() {
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

    private void addChairmanToSchool(School school) {
        Chairman chairman = new Chairman();
        chairman.setName(chairmanName);
        chairman.setSchool(school);
        school.setChairman(chairman);
        entityManager.persist(school);
        entityManager.flush();
    }
}
