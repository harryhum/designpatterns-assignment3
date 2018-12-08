/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.Group;

/**
 * hides action of creating objects
 * @author Claire
 */
public class GroupBuilder {
    
    /**
     * the group we're making
     */
    private final Group group = new Group();

    /**
     * sets group name
     * @param name string to set
     * @return 
     */
    public GroupBuilder setName( String name) {
        group.setName(name);
        return this;
    }
    


    /**
     * 
     * @param code ID to set
     * @return 
     */
    public GroupBuilder setId(int code) {
        group.setId(code);
        return this;
    }

    /**
     * 
     * @return the group
     */
    public Group get() {
        return group;
    }

    
}
