package Problem307;

class NumArray {

    private int[] tree;
    private int[] data;

    public NumArray(int[] nums) {
        tree = new int[nums.length*4];
        data = nums;

        if (nums.length>0){
            buildSegmentTree(0, 0, data.length-1);
        }
    }

    private void buildSegmentTree(int index, int l, int r) {
        if (l==r){
            tree[index] = data[l];
            return;
        }

        int mid = l+(r-l)/2;
        int leftChild =leftChild(index);
        int rightChild = rightChild(index);

        buildSegmentTree(leftChild,l, mid);
        buildSegmentTree(rightChild, mid+1, r);

        tree[index] = tree[leftChild]+tree[rightChild];
    }

    private int leftChild(int treeIndex){
        return 2*treeIndex+1;
    }

    private int rightChild(int treeIndex){
        return 2*treeIndex+2;
    }

    public void update(int i, int val) {
        data[i] = val;
        update(0, 0, data.length-1, i);
    }


    private void update(int treeIndex, int l, int r, int index){
        if (l==r){
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if (index<=mid){
            update(leftChild, l, mid, index);
        }
        else {
            update(rightChild, mid+1, r, index);
        }

        tree[treeIndex] = tree[leftChild] + tree[rightChild];
    }

    public int sumRange(int i, int j) {
        return sumRange(0,0, data.length-1, i, j);
    }

    private int sumRange(int treeIndex, int l, int r, int ql, int qr){
        if (ql==l && qr==r){
            return tree[treeIndex];
        }

        int mid = l+(r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if (qr<=mid){
            return sumRange(leftIndex, l, mid, ql, qr);
        }

        if(ql>mid){
            return sumRange(rightIndex, mid+1,r, ql, qr);
        }

        return sumRange(leftIndex, l, mid, ql, mid)+sumRange(rightIndex, mid+1,r, mid+1, qr);
    }

    public static void main(String[] args) {
        int[] nums = {};
        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0,2));
        obj.update(1, 2);
        System.out.println(obj.sumRange(0,2));
    }
}
