/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.Student;

/**
 *
 * @author Claire
 */
public class StudentBuilder {
    private final Student Student = new Student();

    public StudentBuilder setfName( String name) {
        Student.setfName(name);
        return this;
    }
    
    public StudentBuilder setlName( String name) {
        Student.setlName(name);
        return this;
    }

    public StudentBuilder setId(int code) {
        Student.setId(code);
        return this;
    }

    public Student get() {
        return Student;
    }

    
}
