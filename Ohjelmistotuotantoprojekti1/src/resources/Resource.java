/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author joosiika
 */
public interface Resource {
    public abstract void refresh();
    public abstract void add(Resource resource);
    public abstract void remove(Resource resource);
    public abstract Resource getChild(int i);
}
