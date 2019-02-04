 package lind001.jds.hashTable;

/**
 * 哈希表（开放地址法解决哈希冲突）
 * 
 * @author lind001
 * @date 2019/02/02
 */
public class Hash {

    /**
     * 哈希表大小
     */
    private int maxSize;

    /**
     * 装载因子（初始）
     */
    private double loadFactor;

    /**
     * 数据成员
     */
    public Node[] datas;

    /**
     * 哈希函数（封装，对哈希表调用方应不可见）
     * 
     * @param key
     * @return
     */
    private int hashFunc(int key) {
        return key % this.maxSize;
    }

    /**
     * 构造函数
     * 
     * @param initialLen
     * @param loadFactor
     */
    public Hash(int initialLen, double loadFactor) {
        this.loadFactor = loadFactor;
        this.maxSize = (int)(initialLen / this.loadFactor);
        this.datas = new Node[this.maxSize];
    }

    /**
     * 构造函数（开放地址法中，哈希表大小一般为质数）
     * 
     * @param initialLen
     * @param maxSize
     */
    public Hash(int initialLen, int maxSize) {
        this.maxSize = maxSize;
        this.loadFactor = initialLen / this.maxSize;
        this.datas = new Node[this.maxSize];
    }

    /**
     * 获取当前哈希表的装载因子（用于分析装载因子与查找时间效率的关系）
     * 
     * @return
     */
    public double getCurrentLF() {
        int count = 0;
        for (int i = 0; i < this.datas.length; i++) {
            if (this.datas[i] != null) {
                count++;
            }
        }
        this.loadFactor = count / this.maxSize;
        return this.loadFactor;
    }

    /**
     * 插入（线性探测法解决冲突）
     * 
     * @param node
     * @return
     */
    public void insertByLDM(Node node) {
        int hashIndex = this.hashFunc(node.key);
        while (this.datas[hashIndex] != null) {
            // LDM线性探测法：步长固定为1
            hashIndex++;
            hashIndex = hashIndex % this.maxSize;
        }
        this.datas[hashIndex] = node;
    }

    /**
     * 插入（二次探测法）
     * 
     * @param node
     */
    public void insertBySDM(Node node) {
        int hashIndex = this.hashFunc(node.key);
        int count = 0;
        while (this.datas[hashIndex] != null) {
            // SDM二次探测法：步长与查找次数有关
            count++;
            hashIndex = (int)(hashIndex + Math.pow(count, 2));
            hashIndex = hashIndex % this.maxSize;
        }
        this.datas[hashIndex] = node;
    }

    /**
     * 插入（再次哈希法）Again-Hash-Method
     * 
     * @param node
     */
    public void insertByAHM(Node node) {
        int hashIndex = this.hashFunc(node.key);
        // notice:再次哈希法中，哈希表大小必须为质数，避免出现步长被表大小整除而死循环的情形
        int stepLen = 5 - node.key % 5; // notice:用5减余数是为了避免步长为0的死循环情形
        while (this.datas[hashIndex] != null) {
            // ①选取一个新的质数（与哈希化选取的质数不同）再次哈希，确定步长
            // ②这里的步长与关键字相对应。SDM、AHM都是为了减小聚集
            hashIndex += stepLen;
            hashIndex = hashIndex % this.maxSize;
        }
        this.datas[hashIndex] = node;
    }

    /**
     * 查找（线性探测法）Linear-Detection-Method
     * 
     * @param key
     * @return
     */
    public Node findByLDM(int key) {
        int hashIndex = this.hashFunc(key);
        int findCount = 1;
        while (this.datas[hashIndex] != null) {
            if (this.datas[hashIndex].key == key) {
                this.datas[hashIndex].count = findCount;
                return this.datas[hashIndex];
            }
            // go to next element which is not null
            hashIndex++;
            findCount++;
            hashIndex = hashIndex % this.maxSize;
        }
        // failed
        return null;
    }

    /**
     * 查找（二次探测法）
     * 
     * @param key
     * @return
     */
    public Node findBySDM(int key) {
        int hashIndex = this.hashFunc(key);
        int stepLenBase = 0;
        int findCount = 1;
        while (this.datas[hashIndex] != null) {
            if (this.datas[hashIndex].key == key) {
                this.datas[hashIndex].count = findCount;
                return this.datas[hashIndex];
            }
            stepLenBase++;
            hashIndex = (int)(hashIndex + Math.pow(stepLenBase, 2));
            findCount++;
            hashIndex = hashIndex % this.maxSize;
        }
        return null;
    }

    /**
     * 查找（再次哈希法）
     * 
     * @param key
     * @return
     */
    public Node findByAHM(int key) {
        int hashIndex = this.hashFunc(key);
        int findCount = 1;
        int stepLen = 5 - key % 5;
        while (this.datas[hashIndex] != null) {
            if (this.datas[hashIndex].key == key) {
                this.datas[hashIndex].count = findCount;
                return this.datas[hashIndex];
            }
            hashIndex += stepLen;
            findCount++;
            hashIndex = hashIndex % this.maxSize;
        }
        return null;
    }

    /**
     * 输出关键字
     */
    public void display() {
        for (int i = 0; i < this.datas.length; i++) {
            if (this.datas[i] == null) {
                System.out.print("-1 ");
            } else {
                System.out.print(this.datas[i].key + " ");
            }
        }
    }
}
