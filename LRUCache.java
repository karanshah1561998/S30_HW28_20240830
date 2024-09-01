// Problem 146. LRU Cache
// Time Complexity : O(1)
// Space Complexity : O(Capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class LRUCache {
    class Node {
        int key, val;
        Node next, prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        // remove from current position and put it towards MR side
        removeNode(node);
        addAtHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Existing
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addAtHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addAtHead(newNode);
            if (map.size() > capacity) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
        }
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */