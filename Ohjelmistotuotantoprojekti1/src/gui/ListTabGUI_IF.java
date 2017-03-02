/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;

/**
 *
 * @author joosiika
 * @param <E>
 */
public interface ListTabGUI_IF<E> {
    public abstract E getElement();
    public abstract void setElement(E e);
    public abstract void setList(List<E> list);
}
