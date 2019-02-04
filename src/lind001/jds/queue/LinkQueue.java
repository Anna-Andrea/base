 package lind001.jds.queue;

import lind001.jds.exception.JDSException;
import lind001.jds.tree.TreeNode;

/**
 * 链队
 * 
 * @author lind001
 * @date 2019/01/31
 */
public class LinkQueue {
    /**
     * 队首指针，指向队首节点
     */
    public TreeNode head;

    /**
     * 队尾指针，指向队尾节点（还有一种方式不维护队尾指针，而是以队首指针为起点检索至队尾指针，空间效率更高，时间效率更低）
     */
    public TreeNode last;

    /**
     * 构造函数（构造空队）
     */
    public LinkQueue() {
        this.head = null;
        this.last = null;
    }

    /**
     * 构造函数（构造含有队首节点的非空队）
     * 
     * @param head
     */
    public LinkQueue(TreeNode head) {
        this.head = head;
        this.last = head;
    }

    /**
     * 入队 （与顺序队不同，只要内存空间足够，不存在队满上溢的异常。维护previous指针域，是为了能够在出队时初始化出队元素的指针域）
     * 
     * @param node
     */
    public void insert(TreeNode node) {
        if (this.isEmpty()) {
            this.head = node;
            this.last = node;
        } else {
            this.last.next = node;
            node.previous = this.last;
            this.last = node;
        }
    }

    /**
     * 出队
     * 
     * @throws JDSException
     */
    public void remove() throws JDSException {
        if (this.isEmpty()) {
            throw new JDSException("the queue is empty");
        }
        this.head = this.head.next;
        if (this.head != null) {
            this.head.previous.next = null;
            this.head.previous = null;
        } else {
            this.last = null;
        }
    }

    /**
     * 获取队首元素
     * 
     * @return
     */
    public TreeNode getHead() throws JDSException {
        if (this.isEmpty()) {
            throw new JDSException("the queue is empty");
        }
        return this.head;
    }

    /**
     * 判断链队是否为空
     * 
     * @return
     */
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }
}
