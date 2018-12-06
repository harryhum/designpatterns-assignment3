package logic;

import business.StudentGroupMatchLogic;
import hthurow.tomcatjndi.TomcatJNDI;
import dto.StudentGroupMatch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * a demo of JUnit for running a test on DAO with Tomcat using TomcatJNDI. for netbeans add Junit and Hamcrest to TestLibraries. then add the 2 jar
 * files tomcat8jndi and tomcat-juli to same folder. for eclipse follow the above steps but just add them to your project by right clicking then
 * adding as library.
 *
 * @author Shahriar (Shawn) Emami
 * @date November 11, 2018
 * @see <a href="https://www.youtube.com/watch?v=N8uZnPR5QVw">JUnit tutorial(youtube)</a>
 * @see <a href="https://github.com/h-thurow/TomcatJNDI">TomcatJNDI for JUnit</a>
 */
public class TestCoursesLogic {

    private static TomcatJNDI tomcatJNDI;
    private StudentGroupMatchLogic logic;

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
        logic = new StudentGroupMatchLogic();
    }

    @Test
    public void testGetAllMatches() {
        assertEquals(4, logic.getAllMatches().size());
    }
    
    @Test
    public void testGetMatchByStudentID() {
        assertEquals(4, logic.getMatchByStudentID(4).getStudentID());
        assertEquals(3, logic.getMatchByStudentID(4).getGroupID());
    }
    
    /* Must manually delete match from database to run test again
        as there is not yet a delete match function in logic.
       Assumes that getMatchByStudentID() works.
    */
    @Test
    public void testAddMatch() {
        Map<String, String[]> testMap = new HashMap<>();
        testMap.put(StudentGroupMatch.COL_GROUP_ID, new String[] {"5"});
        testMap.put(StudentGroupMatch.COL_STUDENT_ID, new String[] {"5"});
        
        logic.addMatch(testMap);
        
        assertEquals(5, logic.getMatchByStudentID(5).getStudentID());
        assertEquals(5, logic.getMatchByStudentID(5).getGroupID());
    }

    @After
    public void tearDown() {
        logic = null;
    }

    @AfterClass
    public static void tearDownClass() {
        tomcatJNDI.tearDown();
    }
}
