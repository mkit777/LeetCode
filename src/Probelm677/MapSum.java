package Probelm677;

import java.util.Map;
import java.util.TreeMap;
/**
 * 677. 键值映射
 * https://leetcode-cn.com/problems/map-sum-pairs/description/
 */
public class MapSum {

    private class Node{
        public Map<Character, Node> next;
        public int value;
        public boolean isWord;
        public Node(){
            next = new TreeMap<>();
            isWord = false;
            value=0;
        }
    }

    /** Initialize your data structure here. */

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value=val;
        cur.isWord=true;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c  = prefix.charAt(i);

            if (!cur.next.containsKey(c)){
                return 0;
            }

            cur = cur.next.get(c);
        }

        return sumLink(cur);
    }

    private int sumLink(Node cur){
        int sum=cur.value;

        for ( Character nextChar : cur.next.keySet() ) {
            sum+=sumLink(cur.next.get(nextChar));
        }

        return sum;
    }

    public static void main(String[] args) {
        MapSum mp = new MapSum();
        mp.insert("apple", 3);
        System.out.println(mp.sum("ap"));
        mp.sum("a");
    }
}


/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */