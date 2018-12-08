package dto;



/**
 * transfer object for Group
 * 
 * @author Claire
 */
public class Group {

    /**
     * db column names
     */
    public static final String NAME = "name";
 
    public static final String ID = "id";
    
    /**
     * name and id of a group
     */
    private String name;
    
    private int id;

    /**
     * default constructor
     */
    public Group(){}
    
    /**
     * makes a group with:
     * @param name
     * @param id 
     */
    public Group( String name, int id){
        setName(name);
        
        setId(id);
    }
    
    /**
     * getter for name
     * @return name string
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param fName string to set
     */
    public void setName(String fName) {
        this.name = fName;
    }


    /**
     * getter for id
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id
     * @param id int to set
     */
    public void setId(int id) {
        this.id = id;
    }

}