package dto;



/**
 * transfer object for Group
 * 
 * @author Shawn
 */
public class Group {

    public static final String NAME = "first_name";
 
    public static final String ID = "id";
    
    private String name;
    
    private int id;

    
    public Group(){}
    
    public Group( String name, int id){
        setName(name);
        
        setId(id);
    }
    
    
        public String getName() {
        return name;
    }

    public void setName(String fName) {
        this.name = fName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}