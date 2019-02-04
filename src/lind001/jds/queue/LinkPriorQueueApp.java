package lind001.jds.queue;

import lind001.jds.tree.BinarySortTree;
import lind001.jds.tree.TreeNode;

/**
 * 优先级队列测试用例
 * 
 * @author lind001
 * @date 2018/09/25
 */
public class LinkPriorQueueApp {
    public static void main(String args[]) {
        TreeNode n1 = new TreeNode(1, "A");
        TreeNode n2 = new TreeNode(10, "E");
        TreeNode n3 = new TreeNode(5, "Space");
        TreeNode n4 = new TreeNode(4, "D");
        // for every TreeNode, create a Tree
        BinarySortTree t1 = new BinarySortTree(n1);
        BinarySortTree t2 = new BinarySortTree(n2);
        BinarySortTree t3 = new BinarySortTree(n3);
        BinarySortTree t4 = new BinarySortTree(n4);
        // insert all trees as queueNode into the priorQueue
        LinkPriorQueue queue = new LinkPriorQueue();
        queue.insert(t1);
        queue.insert(t2);
        queue.insert(t3);
        queue.insert(t4);
        // display
        queue.display();
    }
}
