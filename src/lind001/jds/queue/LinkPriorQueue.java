package lind001.jds.queue;

import lind001.jds.tree.BinarySortTree;

/**
 * @author lind001
 * @date 2018/09/25
 */
public class LinkPriorQueue {

    private Node first;

    public LinkPriorQueue() {
        this.first = null;
    }

    public boolean isEmpty() {
        if (this.first == null)
            return true;
        else {
            return false;
        }
    }

    // insert after rear of the queue, and sort by tree.root.rank
    public void insert(BinarySortTree tree) {
        Node newNode = new Node(tree);
        if (this.isEmpty()) {
            this.first = newNode;
        } else {
            Node current = this.first;
            // Node current can not back
            if (current.next == null) {
                if (newNode.getRank() < current.getRank()) {
                    newNode.next = current;
                    this.first = newNode;
                } else {
                    current.next = newNode;
                }
                // end if-else
            } else {
                while (current.next.getRank() < newNode.getRank()) {
                    // n / 2
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
            // end if-else
        }
        // end if-else
    }

    // Application for HuffmanTree: get two elements from the priority queue
    public BinarySortTree removeFirst() throws Exception {

        return first.tree;
    }

    public void display() {
        Node current = this.first;
        while (current != null) {
            System.out.println(current.getRank() + " ");
            current = current.next;
        }
    }

}
