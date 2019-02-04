package lind001.jds.hashTable;

import lind001.lintcode.linklist.LinkNode;

/**
 * ��ϣ������������������
 * @author ld
 *
 */
public class hashChainApp {
	public static void main(String[] args) {
		int keys[] = {232,343,3782,3214789,5845,23490,4378,5491,32,2368,584,37291,237893,189,3479,49389,123,920,3217,347891,372984,465,56578,36283};
		HashChain hashChain = new HashChain(13);
		for (int i=0;i <keys.length;i++) {
			hashChain.insert(new LinkNode(keys[i]));
		}
		hashChain.display();
	}
}	
