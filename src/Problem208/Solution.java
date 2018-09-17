package Problem208;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. 实现 Trie (前缀树)
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/description/
 */

class Trie {
    /** Initialize your data structure here. */
    private class Node{
        public Map<Character, Node> next;
        public boolean isWord;

        public Node(){
            next = new HashMap<>();
            isWord=false;
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)){
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        boolean p2 = trie.search("hello");
        System.out.println(p2);
        System.out.println(trie.startsWith("h0"));
        trie.insert(null);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
