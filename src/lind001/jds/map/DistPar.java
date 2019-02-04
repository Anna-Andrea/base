 package lind001.jds.map;

 /**
 * 距离-父顶点类（辅助类，可以用来存储距离和父顶点信息；因为最短路径算法不能只输出最小权值和，还应当输出路径）
 * 
 * @author lind001
 * @date 2019/01/22
 */
public class DistPar {
    /**
     * （最短）距离
     */
    public int distance;

    /**
     * 父亲顶点
     */
    public Vertex parent;


    /**
     * constructor
     * 
     * @param distance
     * @param parent
     */
    public DistPar(int distance, Vertex parent) {
        this.distance = distance;
        this.parent = parent;
    }
}
