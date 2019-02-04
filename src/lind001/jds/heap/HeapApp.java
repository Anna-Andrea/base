 package lind001.jds.heap;

import lind001.jds.exception.JDSException;
import lind001.jds.tree.TreeNode;

/**
 * 堆（插入、删除、遍历、堆排序）测试用例
 * 
 * @author lind001
 * @date 2019/01/24
 */
public class HeapApp {
    public static void main(String[] args) throws JDSException {
        System.out.println("\n******************ArrayHeap******************\n");
        Heap heap = new Heap(40);
        // batch insert0
        HeapNode[] nodes = {new HeapNode(10), new HeapNode(23), new HeapNode(40), new HeapNode(12), new HeapNode(35), new HeapNode(70)};
        heap.batchInsert(nodes);
        heap.insert(new HeapNode(100));
        heap.remove();
        // check the use of pointer [heapNum]
        heap.insert(new HeapNode(82));
        heap.display_tree();
        System.out.println("\n******************ArrayHeapSort******************\n");
        int[] test = {40, 20, 54, 87, 23, 41, 15, 16, 74, 15};
        Heap sortHeap = new Heap(test.length);
        int[] result = sortHeap.heapSort(test);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        // TreeHeap
        System.out.println("\n\n******************TreeHeap******************\n");
        TreeNode root = new TreeNode(10);
        TreeHeap treeHeap = new TreeHeap(root);
        int[] keys = {23, 40, 12, 35, 70, 85, 20};
        for (int i = 0; i < keys.length; i++) {
            treeHeap.insert(keys[i]);
        }
        treeHeap.remove();
        treeHeap.insert(38);
        treeHeap.display();
        System.out.println("\n\n******************TreeHeapSort******************\n");
        root = new TreeNode(test[0]);
        treeHeap = new TreeHeap(root);
        for (int i = 1; i < test.length; i++) {
            treeHeap.insert(test[i]);
        }
        while (!treeHeap.tree.isEmpty()) {
            System.out.print(treeHeap.getTopNode().key + " ");
            treeHeap.remove();
        }
    }
}
