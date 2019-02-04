 package lind001.jds.tree;

import lind001.jds.exception.JDSException;

/**
 * 树类（抽象类）
 * 
 * @author lind001
 * @date 2019/01/31
 */
public abstract class Tree {

    public Object root;

    public abstract boolean isEmpty();

    public abstract void preOrder() throws JDSException;

    public abstract void inOrder() throws JDSException;

    public abstract void postOrder() throws JDSException;

    public abstract void levelOrder() throws JDSException;

}
