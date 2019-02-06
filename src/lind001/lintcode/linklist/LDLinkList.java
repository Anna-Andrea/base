package lind001.lintcode.linklist;

/**
 * 带头结点的循环双链表类（循环单链表也可以复用此类，只要不使用previous指针域）
 * Head-Loop-Double-LinkList
 * @author ld
 *
 */
public class LDLinkList {
	/**
	 * 指向头节点
	 */
	public LinkNode head;
	
	/**
	 * 指向尾节点 
	 */
	public LinkNode last;
	
	/**
	 * 带头节点（不含数据域） 
	 */
	public LDLinkList() {
		this.head = new LinkNode();
		this.last = this.head;
	}
	
	/**
	 * 循环双链表插入
	 * @param node
	 */
	public void insert_double(LinkNode node) {
		//与单链表不同，双链表需要维护两个指针域
		this.last.next = node;
		node.previous = this.last;
		//与非循环链表不同，循环链表尾节点的next指针域需指向头节点，头节点的previous指针域需指向尾节点
		node.next = this.head;
		this.head.previous = node;
		this.last = node;
	}
	
	/** 循环单链表插入
	 * @param node
	 */
	public void insert_simple(LinkNode node) {
		this.last.next = node;
		node.next = this.head;
		this.last = node;
	}
	
}
