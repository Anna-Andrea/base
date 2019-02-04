package lind001.jds.queue;

import lind001.jds.tree.BinarySortTree;

/**
 * @author lind001
 * @date 2018/09/25
 */
public class Node {
    public double data;

    public Node next;

    // user for generate a HuffmanTree
    public BinarySortTree tree;

    public Node(BinarySortTree tree) {
        this.tree = tree;
    }

    // constructor
    public Node() {
        this.next = null;
    }

    public Node(double data) {
        this.data = data;
        this.next = null;
    }

    // get rank : node.tree.root.rank
    public int getRank() {
        return this.tree.root.rank;
    }
}
