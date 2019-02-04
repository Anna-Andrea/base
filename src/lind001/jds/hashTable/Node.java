 package lind001.jds.hashTable;

 /**
 * 哈希表元素（数据项）
 * 
 * @author lind001
 * @date 2019/02/02
 */
public class Node {
    /**
     * 关键字
     */
    public int key;

    /**
     * 数据成员
     */
    public String data;

    /**
     * 查找到该节点所需次数（用于比较LDM、SDM、AHM的时间效率）
     */
    public int count;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, String data) {
        this.key = key;
        this.data = data;
    }
}
