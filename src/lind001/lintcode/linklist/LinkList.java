package lind001.lintcode.linklist;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * 区间删除
	 * 【带有头节点的单链表元素无序，删除表中所有关键字介于给定两个值之前的元素】 p37-7
	 * @param linkList 带头节点的单链表（这一形参体现了面向过程C与面向对象Java之间的区别）
	 * @param floor 
	 * @param ceil
	 * @return
	 */
	public boolean H_delete_section(LinkList linkList,int floor,int ceil) {
		// 思路一：①排序O(nlogn);②删除O(logn)，总时间复杂度O(nlogn)
		// 思路二:遍历线性表 o(n)，比较之下选择思路二
		LinkNode start = linkList.head;
		// 单链表找前驱很麻烦，采取双指针法
		LinkNode sParent = linkList.head;
		boolean hasDelete = false;
		// at least has one data node 
		while (start.next != null) {
			sParent = start;
			start = start.next;
			if (start.key>=floor && start.key<=ceil) {
				// delete 
				sParent.next = start.next;
				start.next = null;
				hasDelete = true;
			}
		}
		return hasDelete;
	}
	

	/**
	 * 公共结点
	 * 【给定两个单链表，编写算法找出两个链表的公共结点】
	 * p37-8
	 * @param L1 
	 * @param L2
	 * @return
	 */
	public static List<LinkNode> H_find_common(LinkList L1,LinkList L2) {
		// 思路一：双层循环遍历 O(n1*n2)
		// 思路二：①堆排序/快排 O(nlogn)；②折半查找O(logn) 总时间复杂度o(nlogn)<O(n^2)
		List<LinkNode> nodeList = new ArrayList<>();
		if (L1.head.next != null && L2.head.next != null) {
			LinkNode p1 = L1.head.next;
			LinkNode p2 = L2.head.next;
			while (p2 != null) {
				while (p1 != null) {
					// common node
					if (p1.key == p2.key) {
						nodeList.add(p2);
						break;
					}
					p1 = p1.next;
				} 
				p2 = p2.next;
			}
		}
		return nodeList;
	}
	
	/**拆分
	 * p37-11
	 * @param hc = {a1,b1,a2,b2,...,an,bn}
	 * @return A={a1,a2,...,an},B={bn,...,b2,b1}
	 */
	public static LinkList[] H_depart(LinkList hc) {
		// 时间复杂度:O(n) = n/2 +　n/2 + 2n(reverse) = 3n = O(n)
		// 观察单链表hc的元素分布可知，采用双指针法，每次跳两步
		LinkList[] lists = new LinkList[2];
		// 拆分后的两个链表 A、B也带头节点
		// LinkNode conHead = new LinkNode();
		// LinkList A = new LinkList(conHead);
		// LinkList B = new LinkList(conHead);
		LinkList A = new LinkList(new LinkNode());
		LinkList B = new LinkList(new LinkNode());
		LinkNode p1 = hc.head.next;
		LinkNode p2 = hc.head.next.next;
		while (p1 != null) {
			A.H_insert(new LinkNode(p1.key));
			if (p1.next == null) {
				p1 = p1.next;
			} else {
				p1 = p1.next.next;
			}
		}
		while (p2 != null) {
			B.H_insert(new LinkNode(p2.key));
			if (p2.next == null) {
				p2 = p2.next;
			} else {
				p2 = p2.next.next;
			}
		}
		lists[0] = A;
		B.H_reverse();
		lists[1] = B;
		return lists;
	}
	
	/**
	 * 去重
	 * p37-12
	 * @param h 递增有序的线性表
	 * @return
	 */
	public static LinkList remove_repeat(LinkList h) {
		// 思路一：（暴力搜索）对于链表的每一个元素，从链表的头节点开始遍历整个链表查找是否有重复元素，O(n^2)
		// 思路二：将链表分为已去重区和尚未去重区，每次从尚未去重区的第一个元素开始遍历链表
		// O(n) = (n-1) + (n-2)+ ...+1 = n*(n-1)/2 = O(n^2)
		if (h.head.next != null) {
			// at least has one data node	
			LinkNode current = h.head.next;
			while (current.next != null) {
				LinkNode start = current.next;
				LinkNode sParent = current;
				while (start != null) {
					if (start.key == current.key) {
						// delete 
						sParent.next = start.next;
						// if start is not the last node 
						if (start.next != null) {
							start.next = null;
						}
						// check next node
						start = sParent.next;
					} else {
						// check next node
						start = start.next;
						sParent = sParent.next;
					}
				} 
				if (current.next != null) {
					current = current.next;
				}
			}
		}
		return h;
	}
	
	/** 归并  First-Method-Merge
	 * 【将两个元素关键字递增的单链表归并成元素关键字递减的单链表，要求空间复杂度O(1)】
	 *  p37-13
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static LinkList FM_Merge(LinkList h1,LinkList h2) {
		// 思路一：①将L2插入L1中；②再将L1逆置。时间复杂度：O(n)= O(n^2) + 2n = O(n^2)
		// 思路二：①找到h1和h2的链尾节点；②比较h1.last和h2.last，插入，前移h1,h2直至遍历完毕
		// O(n) = 2n + 2n = O(n)，这里由于题目要求采取思路一，下一个方法采取思路二
		if (h1.head.next != null && h2.head.next != null) {
			// h1,h2 at least has one data node 
			LinkNode p1 = h1.head.next;
			LinkNode p2 = h2.head.next;
			while (p2 != null) {				
				// get insert location
				while (p1.next!=null && p1.next.key < p2.key) {
					p1 = p1.next;
				}
				// get p2 from h2,then insert p2 between p1 and p1.next
				h2.head.next = p2.next;
				p2.next = p1.next;
				p1.next = p2;
				// reset p1 and p2 
				p1 = h1.head.next;
				p2 = h2.head.next;
			}
		}
		// after merging h1 and h2 ASC,reverse it 
		h1.H_reverse();
		return h1;
	}
	
	/**
	 * 合并 Second-Method-Merge
	 * p37-13
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static LinkList SM_Merge(LinkList h1,LinkList h2) {
		LinkNode head = new LinkNode();
		LinkList hc = new LinkList(head);
		LinkNode hcLast = hc.head;
		if (h1.head.next != null && h2.head.next != null) {
			LinkNode p1 = h1.head.next;
			LinkNode p2 = h2.head.next;
			while (p1 != null || p2 != null) {
				if (p2== null || p1.key <= p2.key) {
					// 若p1和p2关键字相等则将p1插入（视为p1较小），避免p1,p2指针停滞而死循环
					LinkNode node = new LinkNode(p1.key);
					hcLast.next = node;
					hcLast = node;
					p1 = p1.next;
				} 
				if (p1== null || p2.key < p1.key) {
					LinkNode node = new LinkNode(p2.key);
					hcLast.next = node;
					hcLast = node;
					p2 = p2.next;
				}
			}
		}
		return hc;
	}
	
	/**
	 * 产生公共单链表
	 * p37-14
	 * @param A
	 * @param B
	 * @return
	 */
	public LinkList getCommonList(LinkList A,LinkList B) {
		// 构造带头节点的单链表C
		LinkList C = new LinkList(new LinkNode());
		return C;
	}
	
	/**判断B是否为A的连续子序列  p37-16
	 * @param A
	 * @param B
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean isChild(LinkList A,LinkList B) {
		//	思路一：步长为一，逐个扫描
		// 	思路二：KMP算法
		if (A.head.next!= null && B.head.next != null) {
			LinkNode p1 = A.head.next;
			LinkNode p2 = B.head.next;
			// get start node
			while (p1 != null) {
				if (p1.key != p2.key) {
					p1 = p1.next;
				}
			}
			if (p1 == null) {
				return false;
			}
			while (p2!=null && p1!=null) {
				if (p1.key != p2.key) {
					return false;
				}
				p1 = p1.next;
				p2 = p2.next;
			}
			return true;
		}
		return false;
	}
	
	/**判断带头节点的循环双链表是否对称 
	 * p37-17
	 * @return
	 */
	public boolean isSymmetrical(LDLinkList L) {
		// 思路一：双指针法，head.next和last从两端开始遍历，步长为1，时间复杂度O(n) = n/2 = O(n)
		// 只有头节点的循环双链表对称
		if (L.head.next == null) {
			return true;
		}
		LinkNode start = L.head.next;
		LinkNode end = L.last;
		// 对称的两种情况：奇数个数据元素、偶数个数据元素
		while (start!=end && start.next!=end) {
			if (start.key != end.key) {
				return false;
			}
			start = start.next;
			end = end.previous;
		}
		return true;
	}
	

	/** 循环单链表链接（将h2链接到h1末尾，不破坏h1的循环链表结构） 
	 * p37-18
	 * @param h1
	 * @param h2
	 * @return
	 */
	public LDLinkList link(LDLinkList h1,LDLinkList h2) {
		// 若h2只有头节点，没有数据节点，则无需链接
		if (h2.head.next!=null) {
			h1.last.next = h2.head.next;
			h2.head.next = null;
			h2.last.next = h1.head;
		}
		return h1;
	}
	
	/** 反复输出、删除循环单链表L的最小节点，直至表空
	 * p37-19
	 * @param L
	 */
	public void del_Min(LDLinkList L) {
		// 思路一：①快速排序；②逐个删除    o(nlogn)
		// 思路二：（暴力搜索）o(n^3)
	}

	/** 输出倒数第K个节点的数据值
	 * p38-21
	 * @param L
	 * @return
	 */
	public int find_K(LinkList L,int k) {
		// O(n) = n + (n-k) = 2n-k = O(n)
		L.length = L.H_getLen();
		if (k > L.length) {
			return 0;
		} 
		LinkNode start  = L.head.next;
		while (L.length - k > 0) {
			start = start.next;
			k++;
		}
		System.out.print("倒数第K个节点："+start.key);
		return 1;
	}
	
	/** 获取共同后缀的起始位置
	 * 【采用带有头节点的单链表保存单词，找出str1和st2所指向的两个链表的共同后缀的起始位置】
	 * p38-22
	 * @param str1
	 * @param str2
	 * @return
	 */
	public LinkNode common_postFix(String str1,String str2) {
		// 思路一：从两表的尾指针开始向前跳，直至找到相同后缀的起始	 O(n)=n1+n2+k=O(n)
		// 思路二：双指针法，两个字符串的长度已知，移动较长单词的开始指针使其【右对齐】
		LinkList h1 = new LinkList(new LinkNode());
		LinkList h2 = new LinkList(new LinkNode());
		for (int i=0 ; i<str1.length();i++) {
			h1.H_insert(new LinkNode(str1.substring(i, i+1)));
		}
		for (int i=0 ; i<str2.length();i++) {
			h1.H_insert(new LinkNode(str2.substring(i, i+1)));
		}
		// 右对齐开始指针
		LinkNode p1 = h1.head.next;
		LinkNode p2 = h2.head.next;
		int stepLen = Math.max(str1.length(), str2.length()) - Math.min(str1.length(), str2.length());
		// if not equal, adjust p1 or p2
		while (stepLen > 0) {
			// str1.length > str2.length
			if (Math.max(str1.length(), str2.length()) == str1.length()) {
				p1 = p1.next;
				stepLen --;
			} else {
				p2 = p2.next;
				stepLen--;
			}
		}
		while (p1 != null) {
			if (p1.data == p2.data) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		// str1 and str2 do not have common post fix
		return null;
	}
	
	/**
	 * 删除绝对值相同的重复节点   Delete-Absolute-Value-Equal-Node
	 * p39-23
	 * @param L
	 * @return
	 */
	public LinkList delAbsEq(LinkList L) {
		
		return L;
	}
	
	
	
}
