package lind001.lintcode.linklist;

/**
 * 链表节点
 * @author ld
 *
 */
public class LinkNode {
	/**
	 * 关键字
	 */
	public int key;

	/**
	 * 数据成员
	 */
	public String data;

	/**
	 * 后继指针
	 */
	public LinkNode next;
	
	/**
	 * 前驱指针
	 */
	public LinkNode previous;
	

	/** 
	 * @param key
	 */
	public LinkNode(int key) {
		this.key = key;
		this.data = null;
		this.next = null;
	}
	
	/**
	 * @param data
	 */
	public LinkNode(String data) {
		this.data = data;
		this.next = null;
	}
	
	/**
	 * 头节点构造（不含数据成员）
	 */
	public LinkNode() {
		this.data = null;
		this.next = null;
	}
	
}
