package lind001.lintcode.sort;

/**排序工具类（插入排序、冒泡排序、快速排序等）
 * @author ld
 *
 */
public class SortUtils {
	
	/** 快速排序（暂时不考虑关键字相等的情形）
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] quickSort(int[] a,int low,int high) {
		if (low < high) {
			int index = partition(a, low, high);
			quickSort(a, low, index-1);
			quickSort(a, index+1, high);
		}	
		return a;
	} 
	
	

	/** 划分（一趟快速排序，将待排序数组划分为两个子数组）
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(int[] a,int low,int high) {
		int base = a[low]; 
		while (low != high) {
			// 暂时不考虑关键字相等的情形 
			if (a[low] < base) {
				low++;
			} else if (a[low] >base) {
				// exchange 
				int tmp = a[low];
				a[low] = a[high];
				a[high] = tmp;
			}
			if (a[high] >base) {
				high--;
			} else if (a[high] < base) {
				// exchange
				int tmp = a[low];
				a[low] = a[high];
				a[high] = tmp;
			}
		}
		return low;
	}
	

}
