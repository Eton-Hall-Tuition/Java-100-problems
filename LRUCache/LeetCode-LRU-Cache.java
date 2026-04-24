class LRUCache {
    class Node{
        int key, value;
        Node next, prev;
        Node(int key, int value){
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
        if(!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }

        Node newNode = new Node(key, value);
        if(cache.size() == capacity){
            Node lruNode = tail.prev;
            removeNode(lruNode);
        }
        addToHead(newNode);
    }

    private void removeNode(Node node){
        cache.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        cache.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;

        head.next = node;
        node.prev = head;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
