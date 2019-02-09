package lind001.lintcode.stack;

import lind001.jds.exception.JDSException;

/**
 * KY数据结构题测试用例（栈）
 * @author ld
 */
public class StackApp {
	public static void main(String[] args) throws JDSException {
		// 括号匹配
		String expTest1  = "{[([{[]}])]}";
		boolean result = Stack.bracketsMatch(expTest1);
		System.out.print(result+" ");
		String expTest2 = "{[([))]}";
		result = Stack.bracketsMatch(expTest2);
		System.out.print(result);
		// 后缀表达式转中缀表达式
		System.out.println("");
		expTest1 = "ABCD-*+EF/-";
		String inFix = Stack.postToIn(expTest1);
		System.out.print(inFix);
		// 函数的非递归计算
		System.out.println("");
		// n=3,x=1, expect f(3,1) = -4
		int value = Stack.nonRecursiveCal(3, 1);
		System.out.print(value);
		// 车厢调度
		System.out.println("");
		String[] cabins1 = {"H","S","H","S","H","H","S","S"};
		String operation = Stack.cabinDispatch(cabins1);
		System.out.println(operation);
	}
}
