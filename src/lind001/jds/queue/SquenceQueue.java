 package lind001.jds.queue;

import lind001.jds.exception.JDSException;

/**
 * 顺序存储结构（数组）队列
 * 
 * @author lind001
 * @date 2019/01/11
 */
public class SquenceQueue {
    /**
     * 队列最大长度
     */
    public int MaxSize;

    /**
     * 关键字，队中元素可能有其他数据
     */
    public String[] keys;

    /**
     * 队首伪指针 （possible used）
     */
    public int front;

    /**
     * 队尾伪指针
     */
    public int end;
    
    /**
     * 构造函数
     * 
     * @param MaxSize
     */
    public SquenceQueue(int MaxSize) {
        this.MaxSize = MaxSize;
        this.front = 0;
        this.end = -1;
        this.keys = new String[MaxSize];
    }

    /**
     * 入队
     * 
     * @throws JDSException
     */
    public void insert(String key) throws JDSException {
        if (this.end == this.MaxSize - 1) {
            throw new JDSException("队满溢出");
        }
        this.keys[++end] = key;
    }

    /**
     * 出队（与栈只能单端操作数据不同，出队会对队中其他元素产生影响）
     * 
     * @return
     * @throws JDSException
     */
    public void getOut() throws JDSException {
        if (this.end == -1) {
            throw new JDSException("队空溢出");
        }
        // ①队首后的元素前移
        int i = this.front;
        do {
            // test
            this.keys[i] = this.keys[i + 1];
            i++;
        } while (i < this.end);
        // ②队尾伪指针前移
        this.end--;
    }

    /**
     * 获取队首元素的关键字
     * 
     * @return
     * @throws JDSException
     */
    public String getFrontKey() throws JDSException {
        if (this.end == -1) {
            throw new JDSException("队空溢出");
        }
        return this.keys[0];
    }

    /**
     * 队列是否为空（常被作为循环判断条件）
     * 
     * @return
     */
    public boolean isEmpty() {
        if (this.end == -1) {
            return true;
        }
        return false;
    }


}
