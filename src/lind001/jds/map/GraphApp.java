 package lind001.jds.map;

 /**
 * 深度优先搜索DFS、广度优先搜索BFS测试用例
 * 
 * @author lind001
 * @date 2019/01/11
 */
public class GraphApp {
    public static void main(String args[]) throws Exception {
        // ① 深度优先搜索dst测试
        Graph graph = new Graph(5);
        // ②初始化图的顶点
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("F");
        // ③初始化邻接矩阵（顶点的关联关系）
        graph.addEdge("A", "C", -1);
        graph.addEdge("A", "D", -1);
        graph.addEdge("B", "D", -1);
        graph.addEdge("D", "C", -1);
        graph.addEdge("A", "F", -1);
        // ④ 深度优先搜索遍历图
        System.out.println("*****DST*****");
        graph.dfs();
        // ⑤ 广度优先搜索遍历图
        System.out.println("*****BST*****");
        graph.bfs();
    }
}
