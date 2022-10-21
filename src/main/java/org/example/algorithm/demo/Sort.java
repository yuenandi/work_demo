package org.example.algorithm.demo;

public class Sort {
    /**
     * 冒泡排序
     * 1、从第一个元素开始，比较相邻的两个元素。如果第一个比第二个大，则进行交换。
     * 2、轮到下一组相邻元素，执行同样的比较操作，再找下一组，直到没有相邻元素可比较为止，此时最后的元素应是最大的数。
     * 3、除了每次排序得到的最后一个元素，对剩余元素重复以上步骤，直到没有任何一对元素需要比较为止。
     */
    public void bubbleSort(int[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (arr.length < 2) {
            return;
        }
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     * 把待排序的数组拆成左右两个区间，左边都比中间的基准数小，右边都比基准数大。接着左右两边各自再做同样的操作，完成后再拆分再继续，一直到各区间只有一个数为止。
     * */
    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // 数组中的首位数字作为基准数
            int stard = arr[start];
            // 记录需要排序的下标
            int low = start;
            int high = end;
            while (low < high) {
                // 右边的数字比基准数大
                while (low < high && arr[high] >= stard) {
                    high--;
                }
                arr[low] = arr[high];
                // 左边的数字比基准数小
                while (low < high && arr[low] <= stard) {
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = stard;
            // 处理小的数字部分
            quickSort(arr, start, low);
            // 处理大的数字部分
            quickSort(arr, low + 1, end);
        }
    }

    /**
     * 直接插入排序
     * 基本思想是将一个记录插入到已经排好序的有序表中，使得被插入数的序列同样是有序的。按照此法对所有元素进行插入，直到整个序列排为有序的过程*/
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int j;
                int temp = arr[i];
                for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    /**
     * 希尔排序
    * 某些情况下直接插入排序的效率极低。比如一个已经有序的升序数组，这时再插入一个比最小值还要小的数，也就意味着被插入的数要和数组所有元素比较一次。我们需要对直接插入排序进行改进。

    怎么改进呢？前面提过，插入排序对已经排好序的数组操作时，效率很高。因此我们可以试着先将数组变为一个相对有序的数组，然后再做插入排序。

    希尔排序能实现这个目的。希尔排序把序列按下标的一定增量（步长）分组，对每组分别使用插入排序。随着增量（步长）减少，一直到一，算法结束，整个序列变为有序。因此希尔排序又称缩小增量排序。

    一般来说，初次取序列的一半为增量，以后每次减半，直到增量为一*/
    public void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < arr.length; j += gap) {
                    if (arr[j] < arr[j - gap]) {
                        int k;
                        int temp = arr[j];
                        for (k = j - gap; k >= 0 && arr[k] > temp; k -= gap) {
                            arr[k + gap] = arr[k];
                        }
                        arr[k + gap] = temp;
                    }
                }
            }
        }
    }
    /**
     * 选择排序
     * */
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 堆排序
     * @param arr 待排序的整型数组
     */
    public  void heapSort(int[] arr) {
        // 开始位置是最后一个非叶子结点，即最后一个结点的父结点
        int start = (arr.length - 1) / 2;
        // 调整为大顶堆
        for (int i = start; i >= 0; i--) {
            maxHeap(arr, arr.length, i);
        }
        // 先把数组中第 0 个位置的数和堆中最后一个数交换位置，再把前面的处理为大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr, i, 0);
        }
    }

    /**
     * 转化为大顶堆
     * @param arr 待转化的数组
     * @param size 待调整的区间长度
     * @param index 结点下标
     */
    public void maxHeap(int[] arr, int size, int index) {
        // 左子结点
        int leftNode = 2 * index + 1;
        // 右子结点
        int rightNode = 2 * index + 2;
        int max = index;
        // 和两个子结点分别对比，找出最大的结点
        if (leftNode < size && arr[leftNode] > arr[max]) {
            max = leftNode;
        }
        if (rightNode < size && arr[rightNode] > arr[max]) {
            max = rightNode;
        }
        // 交换位置
        if (max != index) {
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            // 因为交换位置后有可能使子树不满足大顶堆条件，所以要对子树进行调整
            maxHeap(arr, size, max);
        }
    }
}
