import java.util.*;

class LRUCache {
    class Node {
        int key, value;
        Node next, prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> cache;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key))
            return -1;

        Node node = cache.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }

        Node newNode = new Node(key, value);
        if (cache.size() == capacity) {
            Node lruNode = tail.prev;
            removeNode(lruNode);
        }
        addToHead(newNode);
    }

    private void removeNode(Node node) {
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        cache.put(node.key, node);

        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void displayCache() {
        System.out.print("Cache (Most Recent -> Least Recent): ");
        Node current = head.next;
        while (current != tail) {
            System.out.print(current.key + ":" + current.value + " -> ");
            current = current.next;
        }
        System.out.println("END");
    }

    // ==================== MAIN METHOD FOR TESTING ====================

    public static void main(String[] args) {
        System.out.println("=== LRU Cache Demo ===\n");

        // Create cache with capacity 2
        LRUCache cache = new LRUCache(2);
        System.out.println("Created LRU Cache with capacity 2\n");

        // Test Case 1: put(1, 1)
        System.out.println("Operation: put(1, 1)");
        cache.put(1, 1);
        cache.displayCache();
        System.out.println();

        // Test Case 2: put(2, 2)
        System.out.println("Operation: put(2, 2)");
        cache.put(2, 2);
        cache.displayCache();
        System.out.println();

        // Test Case 3: get(1)
        System.out.println("Operation: get(1)");
        int result = cache.get(1);
        System.out.println("Result: " + result);
        cache.displayCache();
        System.out.println();

        // Test Case 4: put(3, 3) - will evict key 2
        System.out.println("Operation: put(3, 3) - Cache is full!");
        cache.put(3, 3);
        System.out.println("Evicted key 2 (least recently used)");
        cache.displayCache();
        System.out.println();

        // Test Case 5: get(2) - should return -1
        System.out.println("Operation: get(2)");
        result = cache.get(2);
        System.out.println("Result: " + result + " (not found)");
        cache.displayCache();
        System.out.println();

        // Test Case 6: put(4, 4) - will evict key 1
        System.out.println("Operation: put(4, 4) - Cache is full!");
        cache.put(4, 4);
        System.out.println("Evicted key 1 (least recently used)");
        cache.displayCache();
        System.out.println();

        // Test Case 7: get(1) - should return -1
        System.out.println("Operation: get(1)");
        result = cache.get(1);
        System.out.println("Result: " + result + " (not found)");
        cache.displayCache();
        System.out.println();

        // Test Case 8: get(3)
        System.out.println("Operation: get(3)");
        result = cache.get(3);
        System.out.println("Result: " + result);
        cache.displayCache();
        System.out.println();

        // Test Case 9: get(4)
        System.out.println("Operation: get(4)");
        result = cache.get(4);
        System.out.println("Result: " + result);
        cache.displayCache();
    }
}