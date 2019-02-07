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
		
	}
}
