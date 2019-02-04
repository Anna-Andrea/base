package lind001.lintcode.linklist;

/**
 * ����ڵ�
 * @author ld
 *
 */
public class LinkNode {
	/**
	 * �ؼ���
	 */
	public int key;

	/**
	 * ���ݳ�Ա
	 */
	public String data;

	/**
	 * ָ�루ָ����һ���ڵ㣩
	 */
	public LinkNode next;
	
	/**
	 * ָ��ǰ���ڵ� 
	 */
	public LinkNode previous;
	

	/** ���췽��
	 * @param key
	 */
	public LinkNode(int key) {
		this.key = key;
		this.data = null;
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
