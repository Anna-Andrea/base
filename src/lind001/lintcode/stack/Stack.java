package lind001.lintcode.stack;

import lind001.jds.exception.JDSException;
import lind001.jds.stack.LinkStack;
import lind001.jds.tree.TreeNode;

/**栈（KY数据结构题）
 * @author ld
 *
 */
public class Stack {

	/** 括号匹配
	 * p87-1
	 * @param exp
	 * @return
	 */
	public static boolean bracketsMatch(String exp) {
		// 思路：从字符串首开始扫描，如果遇到左括号，入栈；遇到右括号，出栈
		LinkStack stack = new LinkStack();
		for (int i=0; i<exp.length(); i++) {
			String subString = exp.substring(i, i+1);
			// case1: {、[、(
			if (subString.equals("{") || subString.equals("[") || subString.equals("(")) {
				TreeNode expNode = new TreeNode(subString);
				stack.push(expNode);
			}
			// case2: }、]、)
			else if (subString.equals("}") ||subString.equals("]") || subString.equals(")")) {
				try {
					TreeNode topEle = stack.getTop();
					stack.pop();
					if (!Stack.isPair(topEle.name, subString)) {
						return false;
					}
				} catch (JDSException e) {
					// catch到栈空下溢异常，括号匹配失败
					return false;
				}
			}
			// case3：字符串中含有括号之外的非法字符，匹配失败
			else {
				return false;
			}
		}
		return true;
	}
	
	/**字符串（括号）是否配对
	 * （目前括号匹配的输入种类限制为三种括号，故可以穷举）
	 * @param a
	 * @param b
	 * @return
	 */
	private static boolean isPair(String a,String b) {
		switch (a) {
			case "{":{
				if (b.equals("}")) {
					return true;
				}
				return false;
			}
			case "[":{
				if (b.equals("]")) {
					return true;
				}
				return false;
			}
			case "(":{
				if (b.equals(")")) {
					return true;
				}
				return false;
			}
			default:{
				return false;
			}
		}
	}
	
	
	
	/** 
	 * 后缀表达式转中缀表达式（需要根据运算符优先级添加括号）
	 * p83
	 * @param postFix
	 * @return
	 * @throws JDSException 栈空下溢异常，一般来说一个合法的后缀表达式不会抛出该异常
	 */
	public static String postToIn(String postFix) throws JDSException {
		String inFix = "";
		LinkStack stack = new LinkStack();
		int i=0;
		while (i<postFix.length()) {
			String subString = postFix.substring(i, i+1);
			if (!Stack.isOp(subString)) {
				stack.push(new TreeNode(subString));
			} else {
				int rank = Stack.getRank(subString);
				// 添加括号的思路：遇到高优先级的运算符时，检查操作数中是否含有低优先级的运算符，若有则添加括号，避免破坏表达式顺序
				String num1 = stack.getTop().name;
				stack.pop();
				String num2 = stack.getTop().name;
				stack.pop();
				if (Stack.containLowerOp(num1,rank)) {
					num1 = "(" + num1 + ")";
				}
				if (Stack.containLowerOp(num2, rank)) {
					num2 = "(" + num2 + ")";
				}
				String result = num2 + subString + num1;
				stack.push(new TreeNode(result));
			}
			i++;
		}
		inFix = stack.getTop().name;
		return inFix;
	}
	
	/** 
	 * 判断a是否为操作符（目前支持的是+、-、*、/，可穷举）
	 * @param a
	 * @return
	 */
	private static boolean isOp(String a) {
		switch (a) {
			case "+": return true;
			case "-": return true;
			case "*": return true;
			case "/": return true;
			default: return false;
		}
	}
	
	/**
	 * 操作符优先级
	 * @param op
	 * @return
	 */
	private static int getRank(String op) {
		switch (op) {
			case "+": return 1;
			case "-": return 1;
			case "*": return 2;
			case "/": return 2;
			default: return -1;
		}
	} 
	
	/** 
	 * 判断操作数中是否含有低优先级运算符
	 * @param num
	 * @param rank
	 * @return
	 */
	private static boolean containLowerOp(String num, int rank) {
		int i=0; 
		while (i< num.length()) {
			String subString = num.substring(i, i+1);
			// 含有优先级比rank低的运算符
			if (Stack.getRank(subString) > -1 && Stack.getRank(subString) < rank) {
				return true;
			}
			i++;
		}  
		return false;
	}
	
	/**
	 * 函数的非递归计算
	 * p87-3
	 * @param n
	 * @param x
	 * @return
	 * @throws JDSException 
	 */
	public static int nonRecursiveCal(int n,int x) throws JDSException {
		// 若使用非递归求f(5)，则f(0),f(1),f(2),f(3),f(4)只需要各计算一次（因为使用栈保存了中间函数计算结果）
		// 若使用递归求f(5)，则需要计算f(5),f(4)一次，f(3)两次，f(2)三次，f(1)五次，f(0)三次，效率较低
		// 某种程度上非递归是自底向上计算，而递归是自顶向下计算
		int result = 0;
		int i=0;
		LinkStack stack = new LinkStack(); 
		while (i<=n) {   
			if (i==0) {
				result = 1;
				stack.push(new TreeNode(result));// 计算f(0),并将f(0)入栈
			} else if (i==1){
				result = 2*x;
				stack.push(new TreeNode(result));// 计算f(1)，并将f(1)入栈
			}
			// 求f(2),f(3),f(r4),f(5)....
			else {
				// 为计算f(n)，出栈，取出f(n-1)与f(n-2)
				int a = stack.getTop().key;
				stack.pop();
				TreeNode node = stack.getTop();
				int b = node.key;
				stack.pop();
				// 计算f(n)，并将f(n)与f(n-1)入栈，为之后如果需要计算f(n+1)做准备
				result = 2*x*a - 2*(n-1)*b;
				stack.push(node);
				stack.push(new TreeNode(result));
			}
			i++;
		}
		return result;
	}
	
	/**
	 * 车厢调度 
	 * p87-2
	 * @param cabins
	 * @return 
	 * @return 
	 * @throws JDSException 
	 */
	public static String cabinDispatch(String[] cabins) throws JDSException {
		String result = "";
		int i=0;
		LinkStack stack = new LinkStack();
		while (i<cabins.length) {
			// 软座车厢，入栈并出栈
			if (cabins[i] == "S") {
				stack.push(new TreeNode("S"));
				result += "push(S) ";
				stack.pop();
				result += "pop(S) ";
			} 
			// 硬座车厢，入栈，直至车厢序列扫描完成后，如果栈不为空，再将所有硬座车厢出栈
			else if (cabins[i] == "H") {
				stack.push(new TreeNode("H"));
				result += "push(H) ";
			}
			else {
				throw new JDSException("系统只能对硬座车厢和软件车厢调度");
			}
			i++;
		}
		// 输出剩余的硬座车厢
		while (!stack.isEmpty()) {
			stack.pop();
			result += "pop(H) ";
		}
		return result;
	}
	


	
	
}
