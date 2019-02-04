 package lind001.jds.hashTable;

 /**
 * 哈希表（开放地址法、拉链法）测试用例
 * 
 * @author lind001
 * @date 2019/02/02
 */
public class HashApp {
    public static void main(String[] args) {
        int[] keys =
            {8, 14, 23, 45, 165, 74864, 4541, 4864, 154, 3456, 102, 15486, 471, 45487, 16535, 456132, 456,
            1245, 48, 4578};
        Hash hashTable = new Hash(20, 41); // hashTable 引用 0xffae
        // LDM查找效率
        for (int i = 0; i < keys.length; i++) {
            hashTable.insertByLDM(new Node(keys[i]));
        }
        int totalCount = 0;
        for (int i = 0; i < keys.length; i++) {
            totalCount += hashTable.findByLDM(keys[i]).count;
        }
        System.out.print("Total count of LDM : " + totalCount);
        // SDM查找效率
        System.out.println("");
        hashTable = new Hash(20, 41);
        for (int i = 0; i < keys.length; i++) {
            hashTable.insertBySDM(new Node(keys[i]));
        }
        totalCount = 0;
        for (int i = 0; i < keys.length; i++) {
            totalCount += hashTable.findBySDM(keys[i]).count;
        }
        System.out.print("Total count of SDM : " + totalCount);
        // AHM查找效率
        System.out.println("");
        hashTable = new Hash(20, 41);
        for (int i = 0; i < keys.length; i++) {
            hashTable.insertByAHM(new Node(keys[i]));
        }
        totalCount = 0;
        for (int i = 0; i < keys.length; i++) {
            totalCount += hashTable.findByAHM(keys[i]).count;
        }
        System.out.print("Total count of AHM : " + totalCount);
    }
}
