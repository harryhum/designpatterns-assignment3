/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import business.FileStudnetGroupMatchLogic;
import business.ValidationException;
import dto.FileStudnetGroupMatch;
import hthurow.tomcatjndi.TomcatJNDI;
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
 *
 * @author tdesj
 */
public class TestFileStudnetGroupMatch {
    
    private static TomcatJNDI tomcatJNDI;
	private FileStudnetGroupMatchLogic logic;

	@BeforeClass
	public static void setUpClass(){
		tomcatJNDI = new TomcatJNDI();
		String webFolder = "web";
		if( !Files.exists( Paths.get( webFolder))){
			webFolder = "WebContent";
		}
		tomcatJNDI.processContextXml( Paths.get( webFolder + "\\META-INF\\context.xml").toFile());
		tomcatJNDI.processWebXml( Paths.get( webFolder + "\\WEB-INF\\web.xml").toFile());
		tomcatJNDI.start();
	}
	
	@Before
	public void setup(){
		logic = new FileStudnetGroupMatchLogic();
	}
	
	@Test
	public void testGetAll(){
		assertEquals( 4, logic.getAll().size());
	}
        
        @Test
        public void testGetById(){
            assertEquals(4, logic.getById(4).getStudentId());
        }
        
        @Test
        public void testAddFSGM() throws ValidationException{
            Map<String, String[]> map = new HashMap<>();
            map.put(FileStudnetGroupMatch.STUDENT, new String[]{"5"});
            map.put(FileStudnetGroupMatch.GROUP, new String[]{"5"});
            map.put(FileStudnetGroupMatch.FILE, new String[]{"5"});
            
            logic.addFSGM(map);
            assertEquals(5, logic.getById(5).getStudentId());
            assertEquals(5, logic.getById(5).getGroupId());
            assertEquals(5, logic.getById(5).getFileId());
            
        }
	
	@After
	public void tearDown(){
		logic = null;
	}

	@AfterClass
	public static void tearDownClass(){
		tomcatJNDI.tearDown();
	}
    
}
