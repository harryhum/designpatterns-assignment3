/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.Group;

/**
 *
 * @author Claire
 */
public class GroupBuilder {
    private final Group group = new Group();

    public GroupBuilder setName( String name) {
        group.setName(name);
        return this;
    }
    


    public GroupBuilder setId(int code) {
        group.setId(code);
        return this;
    }

    public Group get() {
        return group;
    }

    
}
