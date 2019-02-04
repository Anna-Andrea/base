package lind001.jds.heap;

/**
 * 堆节点 （节点中可能存放关键字、数据等多种类型的信息，需要自定义一个类）
 * 
 * @author lind001
 * @date 2019/01/23
 */
public class HeapNode {

    /**
     * 关键字，用于对节点排序的成员
     */
    private int key;

    /**
     * 数据成员
     */
    public String data;

    public int getKey() {
        return this.key;
    }

    /**
     * 构造方法一（只设置关键字）
     * 
     * @param key
     */
    public HeapNode(int key) {
        this.key = key;
    }
}
