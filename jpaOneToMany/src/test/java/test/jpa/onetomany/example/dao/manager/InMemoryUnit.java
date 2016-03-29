package test.jpa.onetomany.example.dao.manager;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InMemoryUnit {
    private EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @Before
    public void setup() throws Exception {
        entityManagerFactory = JPAEntityManager.getManager();
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        createTables();
    }

    @After
    public void destroy() {
        entityManager.getTransaction().rollback();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createTables() throws Exception {
        for (File file : getFiles()) {
            executeSQL(getFileData(file.getPath()));
        }
    }

    private List<File> getFiles() {
        URL url = InMemoryUnit.class.getClassLoader().getResource("sql");
        File directory = new File(url.getFile());
        List<File> filesInDirectory = Arrays.asList(directory.listFiles());
        Collections.sort(filesInDirectory, new SortBySQLFileIndex());

        return filesInDirectory;
    }

    private String getFileData(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }

    private void executeSQL(String str) {
        Query q = entityManager.createNativeQuery(str);
        q.executeUpdate();
    }
}

class SortBySQLFileIndex implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return getFileIndex((File) o1) > getFileIndex((File) o2) ? 1 : -1;
    }

    private int getFileIndex(File file) {
        return Integer.parseInt(file.getName().split("\\.")[0]);
    }
}