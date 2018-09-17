package Problem211;
import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    private class Node{
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(){
            isWord = false;
            next = new HashMap<>();
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord=true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node cur, String word, int i){

        if (i==word.length()){
            return cur.isWord;
        }

        char c = word.charAt(i);

        if (c == '.'){
            for (Character nextChar : cur.next.keySet()) {
                if (search(cur.next.get(nextChar), word, i+1)){
                    return true;
                }
            }
            return false;
        }

        if (!cur.next.containsKey(c)){
            return false;
        }

        return search(cur.next.get(c), word, i+1);
    }


    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("hello");
        System.out.println(wd.search("he"));
        System.out.println(wd.search("hello"));
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */