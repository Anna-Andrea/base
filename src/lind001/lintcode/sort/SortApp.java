package lind001.lintcode.sort;

/**
 * 排序测试用例
 * @author ld
 *
 */
public class SortApp {
	public static void main(String[] args) {
		// quickSort 
		int a[] = {60,50,41,78,23,91,62,321,17,76,30};
		SortUtils.quickSort(a, 0, a.length-1);
		for (int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
	}
}
