 package lind001.jds.map;

import lind001.jds.exception.JDSException;

/**
 * 最短路径算法测试用例
 * 
 * @author lind001
 * @date 2019/01/22
 */
public class PathApp {
    public static void main(String[] args) throws JDSException {
        Graph graph = new Graph(7);
        String[] keys = {"A", "B", "C", "D", "E", "F", "G"};
        graph.batchAddVertex(keys);
        graph.addEdge("A", "B", 4);
        graph.addEdge("B", "C", 3);
        graph.addEdge("B", "D", 6);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "G", 5);
        graph.addEdge("E", "G", 4);
        graph.addEdge("C", "F", 3);
        graph.dijkstra();
    }
}
