package lind001.jds.stack;

import lind001.jds.exception.JDSException;
import lind001.jds.tree.TreeNode;

/**
 * 链栈
 * 
 * @author lind001
 * @date 2018/09/26
 */
public class LinkStack {
    /**
     * 栈顶指针（有next和previous两个指针域，双向链栈，注意与双端链栈区分）
     */
    private TreeNode top;

    public LinkStack() {
        this.top = null;
    }

    /**
     * 入栈（与顺序栈申请连续、大小确定的内存空间不同，链栈动态申请内存，只要内存空间足够，就不会出现栈满上溢的异常）
     * 
     * @param target
     */
    public void push(TreeNode target) {
        if (this.top == null) {
            this.top = target;
        } else {
            // top.next ： reference to target
            this.top.next = target;
            target.previous = this.top;
            this.top = target;
        }
    }

    /**
     * 入栈
     * 
     * @param key
     * @param data
     */
    public void push(int key, double data) {
        TreeNode newNode = new TreeNode(key, data);
        if (this.top == null) {
            this.top = newNode;
        } else {
            this.top.next = newNode;
            newNode.previous = this.top;
            newNode = this.top;
        }
    }

    /**
     * 出栈
     * 
     * @return
     * @throws JDSException
     */
    public TreeNode pop() throws JDSException {
        if (this.top == null) {
            throw new JDSException("Can not pop : this stack is empty!");
        } else {
            TreeNode topElement = this.top;
            top = top.previous;
            // 如果栈顶元素不是最后一个元素，则需要进行初始化
            if (top != null) {
                // 需将出栈节点的previous指针域和栈顶节点的next指针域置空（初始化），避免下一次使用时出错
                // 与链栈不同，顺序栈无需进行此操作。因为顺序栈的栈顶指针是一个整型的伪指针，并不是栈中节点对象的成员，并不像链栈那样，入栈、出栈操作会修改节点的指针域
                top.next.previous = null;
                top.next = null;
            }
            return topElement;
        }
    }

    /**
     * 取栈顶元素（该元素不出栈）
     * 
     * @return
     * @throws JDSException
     */
    public TreeNode getTop() throws JDSException {
        if (this.top == null) {
            throw new JDSException("Can not pop : this stack is empty!");
        } else {
            return top;
        }
    }

    /**
     * 查找（无序链栈，时间效率O（n））
     * 
     * @param key
     * @return
     * @throws JDSException
     */
    public TreeNode getElement(int key) throws JDSException {
        while (this.top != null) {
            if (this.top.key == key) {
                return this.top;
            }
            top = top.previous;
        }
        throw new JDSException("can not find target element");
    }

    /**
     * 判断栈是否为空
     * 
     * @return
     */
    public boolean isEmpty() {
        if (this.top == null) {
            return true;
        }
        return false;
    }

}
