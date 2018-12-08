package logic;

import business.StudentLogic;
import business.ValidationException;
import hthurow.tomcatjndi.TomcatJNDI;
import dto.Student;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the student logic class
 *
 * @author Claire
 */
public class TestStudentLogic {

    private static TomcatJNDI tomcatJNDI;
    private StudentLogic logic;

    @BeforeClass
    public static void setUpClass() {
        tomcatJNDI = new TomcatJNDI();
        String webFolder = "web";
        if (!Files.exists(Paths.get(webFolder))) {
            webFolder = "WebContent";
        }
        tomcatJNDI.processContextXml(Paths.get(webFolder + "\\META-INF\\context.xml").toFile());
        tomcatJNDI.processWebXml(Paths.get(webFolder + "\\WEB-INF\\web.xml").toFile());
        tomcatJNDI.start();
    }

    @Before
    public void setup() {
        logic = new StudentLogic();
    }

    @Test
    public void testGetAllMatches() {
        assertEquals(4, logic.getAllStudents().size());
    }
    
    @Test
    public void testGetById() {
        assertEquals(1, logic.getById(String.valueOf(1)).getId());
        assertEquals("1", logic.getById(String.valueOf(1)).getfName());
        assertEquals("1", logic.getById(String.valueOf(1)).getfName());
    }
    

    @Test
    public void testAddAndDelete() {
        Map<String, String[]> testMap = new HashMap<>();
        testMap.put(Student.ID, new String[] {"5"});
        testMap.put(Student.FIRST_NAME, new String[] {"5"});
        testMap.put(Student.LAST_NAME, new String[] {"5"});
        
        try{
        logic.addStudent(testMap);
        }
        catch (ValidationException e){
            
        }
        
        assertEquals(5, logic.getById(String.valueOf(5)).getId());
        assertEquals("5", logic.getById(String.valueOf(5)).getfName());
        assertEquals("5", logic.getById(String.valueOf(5)).getlName());
        
        
        try{
        logic.deleteStudent(String.valueOf(5));
        }
        catch (ValidationException e){}
        assertNull(logic.getById("5"));
    }
    

  
    //cleaning/validation functions not tested since they were provided in sample code

    @After
    public void tearDown() {

        logic = null;
    }

    @AfterClass
    public static void tearDownClass() {

        tomcatJNDI.tearDown();
    }
}