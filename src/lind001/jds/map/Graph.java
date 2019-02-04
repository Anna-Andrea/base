package lind001.jds.map;

import java.util.ArrayList;
import java.util.List;

import lind001.jds.exception.JDSException;
import lind001.jds.queue.SquenceQueue;
import lind001.jds.stack.SquenceStack;

/**
 * @author lind001
 * @date 2019/01/02
 */
public class Graph {
    public int MaxSize;

    public Vertex[] vertexArray;

    public List<Vertex> vertexList;

    public int[][] edgeAdjMatrixArray;

    public int currentVertexIndex;

    /**
     * 最短路径数组（会随当前顶点的移动不断更新） shortest Paths
     */
    public DistPar[] sPath;

    /**
     * （最小生成）树顶点数量；当所有顶点都加入树时，最短路径算法结束
     */
    public int treeNum;

    /**
     * 定义无法到达距离为无限大
     */
    public int INF = 1000000;

    /**
     * @param MaxSize
     */
    public Graph(int MaxSize) {
        this.MaxSize = MaxSize;
        vertexArray = new Vertex[MaxSize];
        edgeAdjMatrixArray = new int[MaxSize][MaxSize];
        this.currentVertexIndex = -1;
        for (int i = 0; i < edgeAdjMatrixArray.length; i++) {
            for (int j = 0; j < edgeAdjMatrixArray.length; j++) {
                edgeAdjMatrixArray[i][j] = this.INF;
            }
        }
        // part for Dijkstra
        this.sPath = new DistPar[MaxSize];
        this.treeNum = 0;
    }

    /**
     * @param key
     */
    public void addVertex(String key) {
        Vertex vertex = new Vertex(key);
        this.vertexArray[++this.currentVertexIndex] = vertex;
        vertex.setIndex(currentVertexIndex);
    }

    /**
     * 批量添加顶点
     * 
     * @param keys
     */
    public void batchAddVertex(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            Vertex vertex = new Vertex(keys[i]);
            this.vertexArray[++this.currentVertexIndex] = vertex;
            vertex.setIndex(currentVertexIndex);
        }
    }


    /**
     * 添加带权边（无权边）
     * 
     * @param startKey
     * @param endKey
     * @param weigh
     * @throws Exception
     */
    public void addEdge(String startKey, String endKey, int weight) throws JDSException {
        int startIndex = -1, endIndex = -1;
        for (int i = 0; i < this.vertexArray.length; i++) {
            if (startKey == this.vertexArray[i].key) {
                startIndex = this.vertexArray[i].getIndex();
            }
            if (endKey == this.vertexArray[i].key) {
                endIndex = this.vertexArray[i].getIndex();
            }
        }
        if (startIndex == -1 || endIndex == -1) {
            throw new JDSException("请输入正确的key值");
        }
        this.edgeAdjMatrixArray[startIndex][endIndex] = 1;
        this.edgeAdjMatrixArray[endIndex][startIndex] = 1;
        // graph with weigh
        if (weight != -1) {
            this.edgeAdjMatrixArray[startIndex][endIndex] = weight;
            this.edgeAdjMatrixArray[endIndex][startIndex] = weight;
        }
    }

    /**
     * 深度优先搜索（栈） Depth-First-Search
     * 
     * @throws JDSException
     */
    public void dfs() throws JDSException {
        SquenceStack traceStack = new SquenceStack(this.MaxSize);
        // use first vertex you add to the graph as default start vertex of DST
        System.out.println(this.vertexArray[0].key);
        this.vertexArray[0].hasVisited = true;
        traceStack.push(this.vertexArray[0].key);
        // do these things until the stack is empty(DST complete)
        while (!traceStack.isEmpty()) {
            // get the top element of stack, and try to find its unvisited adjVertexs
            List<Vertex> adjVertexs = this.getAdjVertexs(traceStack.getTopValue());
            boolean canFind = false;
            for (int i = 0; i < adjVertexs.size(); i++) {
                if (!adjVertexs.get(i).hasVisited) {
                    // find its unvisited adjVertexs, display it and push one of its unvisited adjVertexs into the stack
                    canFind = true;
                    System.out.println(adjVertexs.get(i).key);
                    adjVertexs.get(i).hasVisited = true;
                    traceStack.push(adjVertexs.get(i).key);
                    break;
                }
            }
            // can not find because it does not has any adjVertexs or all of its adjVertexs have been visited 
            if (!canFind) {
                // back to last vertex and try to find its unvisited adjVertexs again (by pop top element of the
                // stack)
                traceStack.pop();
            }
        }
        // finally initialize hasVisited, for using this or other likely functions next instruction
        for (int i = 0; i < this.vertexArray.length; i++) {
            this.vertexArray[i].hasVisited = false;
        }
    }
    
    /**
     * 从邻接矩阵中获取与目标顶点相关联的顶点集合
     * 
     * @param index
     * @return
     * @throws JDSException
     */
    public List<Vertex> getAdjVertexs(String key) throws JDSException {
        // find its index of the graph by its key
        int index = -1;
        for (int j = 0; j < this.vertexArray.length; j++) {
            if (this.vertexArray[j].key == key) {
                index = j;
                break;
            }
        }
        if (index == -1) {
            throw new JDSException("请输入正确的关键字！");
        }
        // find its adjVertexs by its index from edgeAdjMatrixArray of the graph
        List<Vertex> adjVertexs = new ArrayList<Vertex>();
        for (int i = 0; i < this.edgeAdjMatrixArray[index].length; i++) {
            if (this.edgeAdjMatrixArray[index][i] == 1) {
                adjVertexs.add(this.vertexArray[i]);
            }
        }
        return adjVertexs;
    }
    
    /**
     * 广度优先搜索（队列）Breadth-First-Search
     * 
     * @throws JDSException
     */
    public void bfs() throws JDSException {
        SquenceQueue traceQueue = new SquenceQueue(this.MaxSize);
        // like DST, use first vertex as default start vertex
        traceQueue.insert(this.vertexArray[0].key);
        this.vertexArray[0].hasVisited = true;
        while (!traceQueue.isEmpty()) {
            List<Vertex> adjVertexs = this.getAdjVertexs(traceQueue.getFrontKey());
            for (Vertex vertex : adjVertexs) {
                // insert its unvisited adjVertexs to the queue
                if (!vertex.hasVisited) {
                    traceQueue.insert(vertex.key);
                    vertex.hasVisited = true;
                }
            }
            // display and get it off the queue
            System.out.println(traceQueue.getFrontKey());
            traceQueue.getOut();
        }
        // finally initialize hasVisited
        for (int i = 0; i < this.vertexArray.length; i++) {
            this.vertexArray[i].hasVisited = false;
        }
    }

    /**
     * 获取距离最小的顶点，使当前顶点指针（伪）指向该顶点
     * 
     * @param sPath
     * @return
     */
    private int getMinIndex(DistPar[] sPath) {
        // set -1 as initial value of minIndex, for easily judging the situation that all vertexes can not reach
        int minIndex = -1;
        // use [INF] as the scale of minimum distance
        int minDistance = this.INF;
        // traversal sPath, find the minimum index (as next currentVertex)
        int i = 0;
        while (i < sPath.length) {
            // apart from those vertexes already added into the prime tree
            if (!this.vertexArray[i].isInTree && sPath[i].distance < minDistance) {
                // update minDistance
                minDistance = sPath[i].distance;
                minIndex = i;
            }
            i++;
        }
        return minIndex;
    }
    
    /**
     * 更新sPath最短路径数组
     * 
     * @param sPath
     * @param currentVertex
     */
    private void adjust_sPath(DistPar[] sPath, Vertex currentVertex) {
        // get startToCurrent distance from sPath
        int startToCurrent = sPath[currentVertex.getIndex()].distance;
        int currentToTarget = this.INF;
        int startToTarget = this.INF;
        // update minimum distance to every destination
        for (int column = 0; column < this.MaxSize; column++) {
            currentToTarget = this.edgeAdjMatrixArray[currentVertex.getIndex()][column];
            startToTarget = startToCurrent + currentToTarget;
            // compare startToTarget with old distance in sPath
            if (startToTarget < sPath[column].distance) {
                // update parent and minimum distance
                sPath[column].parent = currentVertex;
                sPath[column].distance = startToTarget;
            }
        }
    }
    
    /**
     * 最短路径算法（Dijkstra）
     * 
     * @return
     */
    public void dijkstra() {
        // first initialize sPath
        for (int i = 0; i < this.MaxSize; i++) {
            // use first vertex as default start vertex as usual
            DistPar distPar = new DistPar(this.edgeAdjMatrixArray[0][i], this.vertexArray[0]);
            this.sPath[i] = distPar;
        }
        // add startVertex (index:0) into the prime tree
        treeNum++;
        this.vertexArray[0].isInTree = true;
        // do until all vertexes have been added into the prime tree
        while (treeNum < this.MaxSize) {
            // get next vertex to add into the prime tree
            int nextIndex = this.getMinIndex(this.sPath);
            treeNum++;
            this.vertexArray[nextIndex].isInTree = true;
            // update sPath
            this.adjust_sPath(this.sPath, this.vertexArray[nextIndex]);
        }
        // print total weight and trace, apart from start vertex
        for (int i = 1; i < this.sPath.length; i++) {
            System.out
                .print(this.vertexArray[i].key + ": " + this.sPath[i].distance + "(" + this.sPath[i].parent.key + ")");
            System.out.println("");
        }
    }


}
