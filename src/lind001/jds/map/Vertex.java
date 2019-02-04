package lind001.jds.map;

/**
 * @author lind001
 * @date 2019/01/02
 */
public class Vertex {

    /**
     * 标志位，是否被访问过；用于DFS/BFS算法遍历/搜索图时避免重复选择顶点
     */
    public boolean hasVisited;

    public String key;

    public Object data;

    private int index;

    /**
     * 标志位，该顶点是否已加入最小生成树
     */
    public boolean isInTree;

    /**
     * constructor
     * 
     * @param key
     */
    public Vertex(String key) {
        this.key = key;
        this.hasVisited = false;
    }

    /**
     * @return
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
