/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;

/**
 *
 * @author joosiika
 */
public abstract class CompositeResource implements Resource {
    
    protected List<Resource> resources;

    public CompositeResource() {
    }

    @Override
    public void add(Resource resource) {
        this.resources.add(resource);
    }

    @Override
    public void remove(Resource resource) {
        this.resources.remove(resource);
    }

    @Override
    public Resource getChild(int i) {
        return this.resources.get(i);
    }
    
}
