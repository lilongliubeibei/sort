package org.example;

import java.util.Arrays;

/**
 * @author lilong
 * @create 2020-03-03
 */
public class SortTest {


    /**
     * 冒泡排序
     */
    void bubleSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j + 1] < a[j]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     */
    void chooseSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[index]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(a, i, index);
            }
        }
    }

    /**
     * 插入排序
     */
    void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            int t = a[i];
            while (j >= 0 && t < a[j]) {
                a[j + 1] = a[j];
                j -= 1;
            }
            a[j + 1] = t;
        }
    }


    /**
     * 希尔排序
     * 缩小增量排序
     */
    void shellSort(int[] a) {
        int r;//增量
        for (r = a.length / 2; r >= 1; r /= 2) {
            for (int i = r; i < a.length; i += r) {
                int j = i - r;
                int t = a[i];
                while (j >= 0 && t < a[j]) {
                    a[j + r] = a[j];
                    j -= r;
                }
                a[j + r] = t;
            }
        }
    }

    /**
     * 快速排序
     * 首先设定一个分解值
     * 分解值取第一个起始值
     */
    void quickSort(int[] a, int leaft, int right) {
//        int a[] = {6, 1, 7, 3, 9, 4, 5, 8, 10, 2};
        int ltemp = leaft;
        int rtemp = right;
        if (right > leaft) {
            while (ltemp != rtemp) {
                while (a[leaft] <= a[rtemp] && rtemp > ltemp) {
                    rtemp--;
                }
                while (a[leaft] >= a[ltemp] && ltemp < rtemp) {
                    ltemp++;
                }
                if (rtemp > ltemp) {
                    swap(a, ltemp, rtemp);
                }
//                if (rtemp == ltemp) {
//                    swap(a, ltemp, rtemp);
//                }
            }

            swap(a, ltemp, leaft);
            quickSort(a, leaft, ltemp - 1);
            quickSort(a, ltemp + 1, right);
        }
    }


    /**
     * 堆排序
     * 基于选择排序
     * 步骤：构造堆结构和堆排序输出
     */
    void heapSort(int[] a, int n) {

//       数组0~n-1,从最后一个非叶节点(n-1)/2开始
        for (int i = (n - 1) / 2; i >= 0; i--) {

            while (2 * i + 1 < n) {
                int j = 2 * i + 1;
                if (2 * i + 2 < n) {
                    if (a[j] < a[j + 1]) {
                        j++;
                    }
                }
                if (a[i] < a[j]) {
                    swap(a, i, j);
                    i = j;
                } else break;
            }
        }

        for (int i = n - 1; i >= 0; i--) {

            swap(a, 0, i);
            int k = 0;
            while (2 * k + 1 <= i - 1) {
                int j = 2 * k + 1;
                if (2 * k + 2 <= i - 1) {
                    if (a[j] < a[j + 1]) {
                        j++;
                    }
                }
                if (a[k] < a[j]) {
                    swap(a, k, j);
                    k = j;
                } else break;
            }
            System.out.print("第" + (n - i) + "步排序后的结果：");
            print(a);
        }

    }

    /**
     * 堆排序
     * 递归法实现
     * 步骤：构造堆结构和堆排序输出
     */
    public void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }


    /**
     * 归并排序
     */
    int[] mergeSort(int[] a) {
        if (a.length < 2) {
            return a;
        }
        int middle = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, middle);
        int[] right = Arrays.copyOfRange(a, middle, a.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int t = 0;
        while (t < a.length + b.length) {
            if (i >= a.length) {
                result[t++] = b[j++];
            } else if (j >= b.length) {
                result[t++] = a[i++];
            } else if (a[i] <= b[j]) {
                result[t++] = a[i++];
            } else {
                result[t++] = b[j++];
            }
        }
        return result;
    }

    private void swap(int[] a, int i, int j) {
        int tem = a[i];
        a[i] = a[j];
        a[j] = tem;
    }

    private static void print(int[] a) {
        System.out.print("排序后的数组为：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 顺序查找
     */
    static int searchByrange(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分法查找
     */
    static int searchBymiddle(int[] a, int x) {
        int start = 0;
        int end = a.length - 1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (a[middle] == x) {
                return middle;
            } else if (a[middle] < x) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        int a[] = {6, 1, 7, 3, 9, 4, 5, 8, 10, 2};
        /*测试冒泡排序*/
//        sortTest.bubleSort(a);
//        print(a);
        /*测试选择排序*/
//        sortTest.chooseSort(a);
//        print(a);
//        System.out.println(" /*测试插入排序*/");
//        /*测试插入排序*/
//        sortTest.insertSort(a);
//        print(a);
//        /*测试希尔排序*/
//        sortTest.shellSort(a);
//        print(a);
//        System.out.println("/*测试快速排序*/");
//        /*测试快速排序*/
        sortTest.quickSort(a, 0, 9);
//        print(a);
//        sortTest.heapSort(a, 10);
//        print(a);
//          print(sortTest.mergeSort(a));
//        System.out.println("元素索引为：" + searchByrange(a, 3));
        System.out.println("元素索引为：" + searchBymiddle(a, 3));
    }
}
