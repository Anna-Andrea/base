package lind001.jds.heap;

import java.util.Vector;

/**
 * 大顶堆（基于数组实现） 特性：①抽象数据结构上是完全二叉树；②父亲节点的关键字总是大于叶子节点的关键字
 * 
 * @author lind001
 * @date 2019/01/23
 */
public class Heap {
    /**
     * 堆节点（以Vector类代替数组，当申请的空间不够时自动扩充）
     */
    public Vector<HeapNode> heapNodes;

    /**
     * 堆节点（以数组作为存储结构）
     */
    public HeapNode[] nodeArray;

    /**
     * 堆的大小
     */
    public int MaxSize;

    /**
     * 当前堆的节点数量（充当伪指针，指向堆的最后一个叶子节点（从左至右方向）的后一个元素）
     */
    public int heapNum;

    /**
     * 构造函数
     * 
     * @param MaxSize
     */
    public Heap(int MaxSize) {
        this.nodeArray = new HeapNode[MaxSize];
        this.heapNodes = new Vector<>();
        this.MaxSize = MaxSize;
        this.heapNum = 0;
    }

    /**
     * 构造函数（不构造空堆）
     * 
     * @param MaxSize
     * @param root
     */
    public Heap(int MaxSize, HeapNode root) {
        this.nodeArray = new HeapNode[MaxSize];
        this.heapNodes = new Vector<>();
        this.MaxSize = MaxSize;
        this.heapNum = 1;
        this.nodeArray[0] = root;
    }

    /**
     * 是否是空堆
     * 
     * @return
     */
    public boolean isEmpty() {
        if (this.heapNum == 0) {
            return true;
        }
        return false;
    }

    /**
     * 插入节点（需要检查调整新插入节点的位置，使数组重新符合堆弱序的规则）
     * 
     * @param node
     */
    public void insert(HeapNode node) {
        // equivalence: this.nodeArray[this.heapNum] = node;this.heapNum++;
        this.nodeArray[this.heapNum++] = node;
        this.moveUp();
    }

    /**
     * 批量插入节点
     * 
     * @param nodes
     */
    public void batchInsert(HeapNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            this.nodeArray[this.heapNum++] = nodes[i];
            this.moveUp();
        }
    }

    /**
     * 向上迭代，直到父亲节点的关键字大于新节点的关键字
     * 
     */
    private void moveUp() {
        // if an empty heap
        if (this.isEmpty()) {
            return;
        }
        int newNodeIndex = this.heapNum - 1;
        int parentIndex = -1;
        // do until move to the root node
        while (newNodeIndex > 0) {
            parentIndex = (newNodeIndex + 1) / 2 - 1;
            // exchange
            if (this.nodeArray[newNodeIndex].getKey() > this.nodeArray[parentIndex].getKey()) {
                // it needs three instructors to exchange, have margin to optimize
                HeapNode tmpNode = this.nodeArray[parentIndex];
                this.nodeArray[parentIndex] = this.nodeArray[newNodeIndex];
                this.nodeArray[newNodeIndex] = tmpNode;
            }
            newNodeIndex = parentIndex;
        }
    }

    /**
     * 删除节点（同样需要调整，使数组符合堆的弱序特性）
     * 
     * @param node
     */
    public void remove() {
        // if an empty tree
        if (this.isEmpty()) {
            return;
        }
        this.nodeArray[0] = this.nodeArray[this.heapNum - 1];
        this.heapNum--;
        this.moveDown();
    }

    /**
     * 向下迭代
     * 
     */
    private void moveDown() {
        // always delete root node, and copy last node to root
        int lastNodeIndex = 0, leftChildIndex = -1, rightChildIndex = -1, choosenChildIndex = -1;
        // do until it do not has child node
        while (lastNodeIndex < this.heapNum / 2) {
            leftChildIndex = (lastNodeIndex + 1) * 2 - 1;
            rightChildIndex = (lastNodeIndex + 1) * 2;
            // should choose the child node which its key is greater to exchange
            choosenChildIndex = rightChildIndex < this.heapNum
                ? (this.nodeArray[rightChildIndex].getKey() > this.nodeArray[leftChildIndex].getKey() ? rightChildIndex
                    : leftChildIndex)
                : leftChildIndex;
            if (this.nodeArray[lastNodeIndex].getKey() < this.nodeArray[choosenChildIndex].getKey()) {
                HeapNode tmpNode = this.nodeArray[choosenChildIndex];
                this.nodeArray[choosenChildIndex] = this.nodeArray[lastNodeIndex];
                this.nodeArray[lastNodeIndex] = tmpNode;
            }
            lastNodeIndex = choosenChildIndex;
        }
    }

    /**
     * 获取堆顶节点（关键字最大/优先级最高的节点）
     * 
     * @return
     */
    public HeapNode getTopNode() {
        if (this.isEmpty()) {
            return null;
        }
        return this.nodeArray[0];
    }

    /**
     * 求底为任意自然数的对数方法（换底公式）
     * 
     * @param base
     * @param value
     * @return
     */
    public double logArithm(double base, double value) {
        return Math.log(value) / Math.log(base);
    }

    /**
     * 层次遍历堆
     */
    public void display_tree() {
        if (this.isEmpty()) {
            return;
        }
        int currentGrade = 0;
        // print by grade
        while (currentGrade < Math.ceil(this.logArithm(2, this.heapNum + 1))) {
            int beginIndex = (int)(Math.pow(2, currentGrade) - 1);
            int endIndex = (int)(Math.pow(2, currentGrade + 1) - 2) + 1 > this.heapNum ? this.heapNum
                : (int)(Math.pow(2, currentGrade + 1) - 2) + 1;
            while (beginIndex < endIndex) {
                System.out.print(this.nodeArray[beginIndex].getKey() + " ");
                beginIndex++;
            }
            System.out.println("");;
            currentGrade++;
        }
    }

    /**
     * 堆排序
     * 
     * @param keys
     * @return
     */
    public int[] heapSort(int[] keys) {
        if (keys.length == 0) {
            return null;
        }
        int[] result = new int[keys.length];
        // transfer keys to nodes first
        HeapNode[] nodes = new HeapNode[keys.length];
        for (int i = 0; i < keys.length; i++) {
            nodes[i] = new HeapNode(keys[i]);
        }
        // insert or remove : O(n*log2(n))
        this.batchInsert(nodes);
        for (int i = 0; i < keys.length; i++) {
            result[i] = this.getTopNode().getKey();
            this.remove();
        }
        return result;
    }
}
