package algorithm.recursion.fenzhi;

/**
 * a 中的第 k 小元素
 * @author Administrator
 *
 */
public class SelectK {

	public int selectK(int[] a, int n, int k) {
		if (n < 38) {
			// mergeSort (a, 0, a.length-1); //使用归并排序1直接对数组a排序
			return a[k - 1];
		}
		int[] m = new int[n / 5];
		for (int i = 0; i < n / 5; i++) {
			// m[i] = mid(a,5*i,5*i+4);
		}
		int mm = selectK(m, m.length, (m.length + 1) / 2);
		int[] a1 = new int[3 * n / 4];
		int[] a3 = new int[3 * n / 4];
		int r = 0, s = 0, t = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] < mm) {
				a1[r++] = a[i];
				continue;
			}
			if (a[i] == mm) {
				s++;
				continue;
			}
			if (a[i] > mm) {
				a3[t++] = a[i];
				continue;
			}
		}
		if (k <= r)
			return selectK(a1, r, k);
		else if (k <= r + s)
			return mm;
		else
			return selectK(a3, t, k - r - s);
	}
}
