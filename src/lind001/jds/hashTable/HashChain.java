package lind001.jds.hashTable;

import lind001.lintcode.linklist.LinkList;
import lind001.lintcode.linklist.LinkNode;

/**
 * ��ϣ����������
 * @author ld
 *
 */
public class HashChain {
	/**
	 * װ������
	 */
	public double loadFactor;
	
	/**
	 * ��ϣ���С
	 */
	public int size;
	
	/**
	 * ��ϣ��������
	 */
	public LinkList[] datas;
	
	/**
	 * @param size
	 */
	public HashChain(int size) {
		this.size = size;
		this.datas = new LinkList[this.size];
	}
	
	/** 
	 * ��ϣ����
	 * @param key
	 * @return
	 */
	private int hashFun(int key) {
		return key % this.size;
	}
	
	/**
	 * ����
	 * @param node
	 */
	public void insert(LinkNode node) {
		int hashIndex = this.hashFun(node.key);
		// check whether it has conflict
		if (this.datas[hashIndex] == null) {
			// no conflict
			LinkList linkList = new LinkList(node);
			this.datas[hashIndex] = linkList;
		} else {
			// has conflict, insert to the link list 
			this.datas[hashIndex].insert(node);
		}
	}
	
	/**
	 * ���ң���������loadFactor = 1.0ʱ��ʱ��Ч��O(1)��
	 * @param key
	 * @return
	 */
	public LinkNode find(int key) {
		int hashIndex = this.hashFun(key);
		if (this.datas[hashIndex] == null) {
			// find failed
			return null;
		}
		return this.datas[hashIndex].find(key);
	}
	
	/**
	 * ������ϣ��
	 */
	public void display() {
		for (int i=0; i< this.datas.length; i++) {
			System.out.print("L["+i+"]: ");
			if (this.datas[i] != null) {
				this.datas[i].display();
			}
			System.out.println("");
		}
	}
}
