/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.Student;

/**
 * hides action of object creation
 * @author Claire
 */
public class StudentBuilder {
    /**
     * the student we're making
     */
    private final Student Student = new Student();
    
    /**
     * sets the first name
     * @param name string to set
     * @return 
     */

    public StudentBuilder setfName( String name) {
        Student.setfName(name);
        return this;
    }
    
    /**
     * sets the last name
     * @param name string to set
     * @return 
     */
    public StudentBuilder setlName( String name) {
        Student.setlName(name);
        return this;
    }

    /**
     * sets the id
     * @param code id to set
     * @return 
     */
    public StudentBuilder setId(int code) {
        Student.setId(code);
        return this;
    }

    /**
     * 
     * @return the student object
     */
    public Student get() {
        return Student;
    }

    
}
