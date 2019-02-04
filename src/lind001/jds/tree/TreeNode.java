package lind001.jds.tree;

/**
 * @author lind001
 * @date 2018/09/26
 */
public class TreeNode {
    // key value
    public int key;
    // other data
    public double data;
    // for HuffmanTree
    public int rank;
    public String name;

    public TreeNode lchild;
    public TreeNode rchild;
    public TreeNode next;
    public TreeNode previous;

    /**
     * 标记：用于判断非递归遍历时是否被访问过
     */
    public boolean hasVisited;

    /**
     * 维护与父亲节点的关联信息
     */
    public TreeNode parent;

    public TreeNode() {
        this.lchild = null;
        this.rchild = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * 构造函数（堆树用）
     */
    public TreeNode(int key) {
        this.key = key;
        this.lchild = null;
        this.rchild = null;
        this.parent = null;
        this.hasVisited = false;
    }

    /**
     * 构造函数（含数据域）
     * 
     * @param key
     * @param data
     */
    public TreeNode(int key, double data) {
        this.key = key;
        this.data = data;
        this.lchild = null;
        this.rchild = null;
        this.next = null;
        this.previous = null;
    }

    public TreeNode(int rank, String name) {
        this.rank = rank;
        this.name = name;
        this.lchild = null;
        this.rchild = null;
        this.next = null;
        this.previous = null;
    }

    /**
     * 是否为满节点（拥有左右孩子）
     * 
     * @return
     */
    public boolean isFull() {
        if (this.lchild != null && this.rchild != null) {
            return true;
        }
        return false;
    }

    /**
     * 是否为叶子节点
     * 
     * @return
     */
    public boolean isLeaf() {
        if (this.lchild == null && this.rchild == null) {
            return true;
        }
        return false;
    }

}
