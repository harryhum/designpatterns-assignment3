package dto;



/**
 * transfer object for Student
 * 
 * @author Claire
 */
public class Student {

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String ID = "id";
    
    private String fName;
    private String lName;
    private int id;

    
    public Student(){}
    
    public Student( String fName, String lName, int id){
        setfName(fName);
        setlName(lName);
        setId(id);
    }
    
    
        public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}