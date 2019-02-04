 package lind001.jds.stack;

import lind001.jds.exception.JDSException;

/**
 * 顺序存储结构栈
 * 
 * @author lind001
 * @date 2019/01/11
 */
public class SquenceStack {

    /**
     * 该栈的大小
     */
    public int MaxSize;

    /**
     * 栈内元素（顺序存储结构）
     */
    public String[] valueArray;

    /**
     * 栈顶伪指针（指向栈顶元素）
     */
    public int top;

    /**
     * @param MaxSize
     */
    public SquenceStack(int MaxSize) {
        this.MaxSize = MaxSize;
        // ①初始化内存空间（java无需进行内存申请）
        this.valueArray = new String[MaxSize];
        // ②栈顶指针初始化
        this.top = -1;
    }

    /**
     * @param value
     * @throws JDSException
     */
    public void push(String value) throws JDSException {
        if (this.top >= this.MaxSize -1) {
            throw new JDSException("栈满上溢");
        }
        this.top++;
        this.valueArray[top] = value;
    }

    /**
     * @return
     * @throws JDSException
     */
    public String pop() throws JDSException {
        if (this.top == -1) {
            throw new JDSException("栈空下溢");
        }
        String topValue = this.valueArray[top];
        top--;
        return topValue;
    }

    /**
     * @return
     * @throws JDSException
     */
    public String getTopValue() throws JDSException {
        if (this.top == -1) {
            throw new JDSException("栈空下溢");
        }
        return this.valueArray[top];
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        return false;
    }

    /**
     * 遍历栈
     * 
     * @throws JDSException
     */
    public void displayBottomToUp() throws Exception {
        if (this.isEmpty()) {
            throw new JDSException("栈空异常");
        }
        // ①初始化一个指向栈底的伪指针
        int pointer = 0;
        while (pointer <= this.top) {
            System.out.print(this.valueArray[pointer] + "-");
            pointer++;
        }
    }
}
