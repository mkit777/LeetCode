package Problem547;

import java.util.Set;
import java.util.TreeSet;

/**
 * 547. 朋友圈
 * https://leetcode-cn.com/problems/friend-circles/description/
 */
public class Solution {

    private class UnionFind{
        private int[] id;

        public UnionFind(int size){
            id = new int[size];
            for (int i = 0; i < size; i++) {
                id[i]=i;
            }
        }

        public void union(int p, int q){
            int pID = id[p];
            int qID = id[q];

            if (pID==qID){
                return;
            }

            for (int i = 0; i < id.length; i++) {
                if (id[i]==qID){
                    id[i]=pID;
                }
            }
        }

        public int cluster(){
            Set<Integer> set = new TreeSet<>();
            for (int i : id) {
                set.add(i);
            }
            return set.size();
        }

    }


    public int findCircleNum(int[][] M) {
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i; j < M.length; j++) {
                if (M[i][j]==1){
                    uf.union(i, j);
                }
            }
        }
        return uf.cluster();
    }
}
