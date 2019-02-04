package lind001.lintcode.linklist;

import lind001.jds.heap.Heap;
import lind001.jds.heap.HeapNode;

/**
 * 无序链表（双端链表）
 * @author ld
 *
 */
public class LinkList {
	/**
	 * 头指针，指向链表头节点 
	 */
	public LinkNode head;
	
	/**
	 * 尾指针
	 */
	public LinkNode last;
	
	/**
	 * 链表长度（之所以多维护这个属性，是因为这个链表是前向单向链表，遍历的顺序是逆序，所以需要有一个大小为链表长度的数组来正序）  
	 */
	private int length;
	
	/**
	 * 构造含头节点的链表（头节点中不存储实质信息）
	 * @param head
	 */
	public LinkList(LinkNode head) {
		this.head = head;
		this.last = head;
		this.length = 1;
	}
	
	/**
	 * 构造空链表
	 */
	public LinkList() {
		this.head = null;
		this.last = null;
		this.length = 0;
	}

	/**插入节点 （头插法）
	 * 时间效率O(1)
	 * @param node
	 */
	public void insert(LinkNode node) {
		if (this.isEmpty()) {
			this.head = node;
			this.last = node;
			this.length ++;
		}
		else {
			this.head.previous = node;
			this.head = node;
			this.length ++;
		}
	}
	
	/**插入节点（含头节点型链表） 尾插法
	 * @param node
	 */
	public void H_insert(LinkNode node) {
		this.last.next = node;
		this.last = node;
	}
	
	/**
	 * 查找（时间效率O（n），平均需要遍历一半的链表元素；若采取有序链表，可以减少查找失败的元素比较次数）
	 * 
	 * @param key
	 * @return
	 */
	public LinkNode find(int key) {
		if (this.isEmpty()) {
			return null;
		} else {
			LinkNode current = this.last;
			while (current != null) {
				if (current.key == key) {
					return current;
				} 
				current = current.previous;
			}
			// can not find this element in the link list
			return null;
		}
	}
	
	/** 
	 * 链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if (this.head == null || this.last == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 遍历
	 */
	public void display() {
		if (this.isEmpty()) {
			return;
		}
		LinkNode start = this.last;
		int[] tmp = new int[this.length];
		int end = this.length -1 ;
		while (start!= null) {
			//System.out.print(start.key + "->");
			tmp[end] = start.key;
			end --;
			start = start.previous;
		}
		for (int i=0;i <tmp.length; i++) {
			if (i == tmp.length -1) {
				System.out.print(tmp[i]);
			} else {
				System.out.print(tmp[i] + "->");
			}
		}
	}
	
	/**
	 * 遍历含头节点的链表
	 * Display-Head-LinkList
	 */
	public void H_display() {
		LinkNode current = this.head;
		while (current != null) {
			if (current.next == null) {
				// last node
				System.out.print(current.key);
			} else {
				System.out.print(current.key + "->");}
			current = current.next;
		}
	}
	
	/**带头节点的链表删除（删除所有关键字为x的节点）   
	 * p37-2
	 * @param x
	 * @return
	 */
	public boolean H_delete(int x) {
		// 单向链表，无法向前回溯（除非再遍历一次链表寻找前驱），所以额外需要维护一个指向待删除节点前驱节点的parent指针
		LinkNode current = this.head;
		LinkNode parent = this.head;
		boolean hasDelete = false;
		while (current != null) {
			parent = current;
			current = current.next;
			if (current!= null && current.key == x) {
				parent.next = current.next;
				current.next = null;
				hasDelete = true;
				current = parent.next;
			}
		}
		return hasDelete;
	}
	
	/**
	 * 逆置
	 * 【将带头结点的单链表L就地逆置，就地是指辅助空间复杂度为O(1)】
	 * P37-5  时间效率o(n) =2n
	 */
	public void H_reverse() {
		// end指针指向单链表尾节点 
		LinkNode end = this.head;
		while (end.next != null) {
			end = end.next;
		}
		// nFirst(new First)指针始终指向原链表的尾节点，即逆置后的一个数据节点
		if (head.next != null) {
			LinkNode nFirst = end;
			LinkNode current = head.next;
			while (head.next != nFirst) {
				head.next = current.next;
				current.next = null;
				if (nFirst.next == null) {
					nFirst.next = current;
					end = current;
				} else {
					current.next = end;
					nFirst.next = current;
					end = current;
				}
				current = head.next;
			}
		}
	}
	
	/**
	 * 反向遍历
	 * 【从尾到头，反向输出带头节点的单链表L每个节点的值】
	 * P37-3
	 */
	public void H_reverse_display() {
		// 方法一：先就地逆置链表，然后再遍历；时间复杂度O(n) = 3n,空间复杂度o(n) =1
		// 方法二：辅助数组（但由于数组特性，需要维护表长属性），正向存储到数组后再反向遍历。时间复杂度O(n)= 2n,空间复杂度O(n)= n
		this.H_reverse();
		LinkNode current = this.head;
		while (current != null) {
			if (current.next == null) {
				System.out.print(current.key);
			} else {
				System.out.print(current.key + "->");}
			current = current.next;
		}
	}
	

	/**获取含头节点的链表的长度（不包括头节点）
	 * 
	 * @return
	 */
	private int H_getLen() {
		LinkNode head = this.head;
		int len = 0;
		while (head.next != null) {
			head = head.next;
			len ++;
		}
		return len;
	} 
	
	/**
	 * 有序化
	 * 【有一个带头节点的单链表L，设计一个算法使其元素递增有序】 
	 * p37-6
	 * 时间复杂度：O(n) = log1+log2+...+logN + 2n = O(n*log2(n))
	 * 空间复杂度：O(n)
	 */
	public void H_sort() {
		// 辅助数据结构：堆 
		Heap heap = new Heap(this.H_getLen());
		// at least has one data node
		// 链表n个元素插入堆中，o(n*log2(n))
		if (this.head.next != null) {
			LinkNode current = this.head.next;
			for (int i=0;i<this.H_getLen();i++) {
				HeapNode node = new HeapNode(current.key);
				heap.insert(node);
				current = current.next;
			}
			// o(n) = 2n
			for (int i=0;i<heap.nodeArray.length;i++) {
				HeapNode topNode = heap.getTopNode();
				heap.remove();
				heap.nodeArray[heap.heapNum] = topNode;
			}
			current = this.head.next;
			for (int i=0;i<this.H_getLen();i++) {
				current.key = heap.nodeArray[i].getKey();
				current = current.next;
			}
		}
	}


	
}
