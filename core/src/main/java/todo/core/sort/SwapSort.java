package todo.core.sort;

import java.util.Arrays;

/**
 * 交换排序
 */
public class SwapSort {

    public static void main(String[] args) {

        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };

        // 冒泡排序
        bubbleSort(a);

        // 快速排序
        quickSort(a);
    }

    /**
     * 1、基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     * @param a
     */
    public static void bubbleSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                // 这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        System.out.println("冒泡排序后：");
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    /**
     * 1、基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
     * @param a
     */
    public static void quickSort(int[] a) {
        if (a.length > 0) {
            quickSort_(a, 0, a.length - 1);

            System.out.println("快速排序后：");
            Arrays.stream(a).forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }

    private static void quickSort_(int[] a, int low, int high) {
        if (low < high) { // 如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = quickSort_GetMiddle(a, low, high);
            quickSort_(a, 0, middle - 1);
            quickSort_(a, middle + 1, high);
        }
    }

    private static int quickSort_GetMiddle(int[] a, int low, int high) {
        int temp = a[low];// 基准元素
        while (low < high) {
            // 找到比基准元素小的元素位置
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }

}
