package Problem303;

class NumArray2 {
    private int[] sums;
    public NumArray2(int[] nums) {
        sums = new int[nums.length+1];
        for (int i = 1; i < nums.length + 1; i++) {
            sums[i] = sums[i-1]+nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1]-sums[i];
    }
}