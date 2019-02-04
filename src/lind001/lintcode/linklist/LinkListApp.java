package lind001.lintcode.linklist;

/**
 * 链表题测试用例
 * @author ld
 *
 */
public class LinkListApp {
	public static void main(String[] args) {
		LinkNode head = new LinkNode();
		LinkList linkList = new LinkList(head);
		int keys[] = {40,23,56,78,98,60,12,23,109,77};
		for (int i=0;i<keys.length;i++) {
			linkList.H_insert(new LinkNode(keys[i]));
		}
		linkList.H_display();
		// H_delete
		linkList.H_delete(23);
		System.out.println("");
		linkList.H_display();
		// H_sort 时间复杂度O(n*log2(n))，空间复杂度O(n)
		linkList.H_sort();
		System.out.println("");
		linkList.H_display();
		// H_reverse
		linkList.H_reverse();
		System.out.println("");
		linkList.H_display();
	
	}
}
