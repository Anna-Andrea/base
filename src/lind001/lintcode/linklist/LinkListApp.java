package lind001.lintcode.linklist;

import java.util.List;

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
		// 删除区间内的数
		linkList.H_delete_section(linkList, 90, 100);
		System.out.println("");
		linkList.H_display();
		// 查找公共节点
		LinkList L = new LinkList(new LinkNode());
		int[] a2 = {109,60,2343,4823,19,324,98};
		for (int i=0;i<a2.length;i++) {
			LinkNode node = new LinkNode(a2[i]);
			L.H_insert(node);
		}
		System.out.println("");
		List<LinkNode> nodeList = LinkList.H_find_common(linkList, L);
		for (LinkNode node : nodeList) {
			System.out.print(node.key + " ");
		}
		// 拆分，拆分为奇数序号和偶数序号的两个链表，其中偶数序号倒置
		System.out.println("");
		LinkList[] linkLists = LinkList.H_depart(linkList);
		for (int i=0; i< linkLists.length; i++) {
			System.out.print("L"+i+":");
			linkLists[i].H_display();
			System.out.print(" ");
		}
		// 去重
		System.out.println("");
		L.H_insert(new LinkNode(98));
		L.H_insert(new LinkNode(60));
		L = LinkList.remove_repeat(L);
		L.H_display();
		// 合并
		System.out.println("");
		linkList.H_sort();
		L.H_sort();
		LinkList hc = LinkList.FM_Merge(linkList, L);
		hc.H_display();
		// 合并
		System.out.println("");
		hc = LinkList.SM_Merge(linkList, L);
		hc.H_display();
	}
}
