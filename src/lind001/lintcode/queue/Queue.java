package lind001.lintcode.queue;

import lind001.jds.exception.JDSException;
import lind001.jds.queue.LinkQueue;
import lind001.jds.tree.TreeNode;

/**
 * 队列（KY数据结构题）
 * @author ld
 *
 */
public class Queue {
	/**
	 * 渡口管理
	 * p87-4
	 * @param ships  渡口客船货船序列
	 * @return	调度序列
	 * @throws JDSException 
	 */
	public static String ferryManage(String[] ships) throws JDSException {
		String result = "";
		// 客船队列、货船队列与总队列
		LinkQueue boatQueue = new LinkQueue();
		LinkQueue cargoQueue = new LinkQueue();
		LinkQueue queue = new LinkQueue();
		int i=0;
		// 货船可否入队标记，为4时表示可以将货船入队，入队后reset客船标记
		int boatFlag = 0;
		while (i< ships.length) {
			// 满足条件（每放上4辆客船，才允许放上一辆货船）
			if (boatFlag == 4 && !cargoQueue.isEmpty()) {
				TreeNode node = cargoQueue.getHead();
				cargoQueue.remove();
				queue.insert(node);
				// reset boatFlag for next cargo
				boatFlag = 0;
			}
			// 如果是客船，可以直接加入客船队列和总队列中
			if (ships[i].contains("B")) {
				boatQueue.insert(new TreeNode(ships[i]));
				// 超过4辆客船，等待货船
				if (boatFlag < 4) {
					TreeNode node = boatQueue.getHead();
					boatQueue.remove();
					queue.insert(node);
					boatFlag ++;
				}
			} 
			// 如果是货船，加入货船队列中
			else if (ships[i].contains("C")) {
				cargoQueue.insert(new TreeNode(ships[i]));
			}
			i++;
		}
		// 若等待的客船不足4辆，则以货船代替；将剩余的货船全部入队
		while (!cargoQueue.isEmpty()) {
			TreeNode node = cargoQueue.getHead();
			cargoQueue.remove();
			queue.insert(node);
		}
		// 若无客车等待，则允许货车都上船
		while (!boatQueue.isEmpty()) {
			TreeNode node = boatQueue.getHead();
			boatQueue.remove();
			queue.insert(node);
		}
		// 输出调度序列（每次只能载10艘船）
		int count = 0;
		while (!queue.isEmpty()) {
			if (count % 10 == 0) {
				result+= "["+ (count/10+1) + "]";
			}
			result += queue.getHead().name;
			queue.remove();
			count ++;
		}
		return result;
	}	
}
