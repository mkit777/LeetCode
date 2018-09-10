package Problem303;

/**
 * 303. 区域和检索 - 数组不可变
 * https://leetcode-cn.com/problems/range-sum-query-immutable/description/
 */
class NumArray {

    private int[] tree;
    private int[] data;

    public NumArray(int[] nums) {
        tree = new int[nums.length * 4];
        data = nums;
        if (nums.length>0){
            buildSegmentTree(0, 0, data.length-1);
        }

    }

    private void buildSegmentTree(int index, int l, int r) {
        if ( l == r ){
            tree[index] = data[l];
            return;
        }


        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        int midIndex = l + (r-l)/2; // 0,3 l=0, r=3  mid 1

        buildSegmentTree(leftChildIndex, l, midIndex);
        buildSegmentTree(rightChildIndex, midIndex+1, r);

        tree[index] = tree[leftChildIndex] + tree[rightChildIndex];
    }

    private int leftChildIndex(int index){
        return 2*index+1;
    }


    private int rightChildIndex(int index){
        return 2*index+2;
    }

    public int sumRange(int i, int j) {
        return sumRange(0, 0,data.length-1, i, j);
    }


    private int sumRange(int index, int l ,int r, int ql, int qr){
        if (ql==l && qr==r){
            return tree[index];
        }

        int midIndex = l+(r-l)/2;
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        if (qr<=midIndex){
            return sumRange(leftChildIndex, l, midIndex, ql, qr);
        }

        if (ql>midIndex){
            return sumRange(rightChildIndex,midIndex+1, r, ql, qr);
        }

        return sumRange(leftChildIndex, l, midIndex, ql, midIndex) + sumRange(rightChildIndex, midIndex+1, r, midIndex+1, qr);

    }

}
