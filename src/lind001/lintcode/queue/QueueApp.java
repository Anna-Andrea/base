package lind001.lintcode.queue;

import lind001.jds.exception.JDSException;

/**
 * KY数据结构题测试用例（队列）
 * @author ld
 *
 */
public class QueueApp {
	public static void main(String[] args) throws JDSException {
		// 渡口调度管理 
		String[] shipsTest1 = {"C1","B1","B2","B3","C2","B4","B5","B6","C3"};//用B代表客船（boat），用C代表货船（cargo）
		// expect : [1]B1B2B3B4C1B5B6C2C3
		String result = Queue.ferryManage(shipsTest1);
		System.out.println(result);
		String[] shipsTest2 = {"B1","C1","B2","B3","B4","B5","B6","B7","B8","B9","C2","B10"};
		// expect : [1]B1B2B3B4C1B5B6B7B8C2[2]B9B10
		result = Queue.ferryManage(shipsTest2);
		System.out.println(result);
		String[] shipsTest3 = {"B1","B2","B3","B4","B5","B6"};
		result = Queue.ferryManage(shipsTest3);
		System.out.println(result);
	}
}
