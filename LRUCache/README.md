
# 🚀 LRU Cache Implementation (Java)

## 📌 Problem Reference
- **Platform:** LeetCode  
- **Problem Name:** LRU Cache  
- **Problem Number:** 146  
- **Difficulty:** Medium  
- **Link:** https://leetcode.com/problems/lru-cache/

---

## 🧠 Problem Statement

Design a data structure that follows the constraints of a **Least Recently Used (LRU) Cache**.

### Requirements:
- `get(key)` → Return value if key exists, else `-1`
- `put(key, value)` → Insert/update key
- When capacity is exceeded → remove **least recently used** item

### ⚡ Constraint:
Both operations must run in **O(1) time complexity**

---

## 💡 Approach

To achieve **O(1)** operations, we combine:

### 1. HashMap
- Stores key → node reference
- Enables **O(1) lookup**

### 2. Doubly Linked List
- Maintains usage order
- Most recently used → **Head**
- Least recently used → **Tail**

---

## 🏗️ Architecture

```

Head <-> Most Recently Used Nodes <-> Least Recently Used <-> Tail

```

### Key Idea:
- On every `get()` or `put()` → move node to **head**
- On overflow → remove node from **tail**

---

## 🔧 Core Methods Explained

### 🔹 get(int key)
- If key exists:
  - Move node to head (mark as recently used)
  - Return value
- Else return `-1`

---

### 🔹 put(int key, int value)
- If key exists:
  - Update value
  - Move node to head
- Else:
  - Create new node
  - If cache is full:
    - Remove **LRU node (tail.prev)**
  - Insert new node at head

---

### 🔹 removeNode(Node node)
- Removes node from linked list
- Removes from HashMap

---

### 🔹 addToHead(Node node)
- Inserts node right after head
- Adds to HashMap

---

## ⏱️ Time Complexity

| Operation | Complexity |
|----------|-----------|
| get()    | O(1)      |
| put()    | O(1)      |

---

## 🧪 Example Execution

```

Capacity = 2

put(1,1) → [1]
put(2,2) → [2,1]
get(1)   → [1,2]
put(3,3) → removes 2 → [3,1]
get(2)   → -1
put(4,4) → removes 1 → [4,3]

````

---

## 📂 Code Highlights

- Custom `Node` class for DLL
- Dummy `head` and `tail` for easier operations
- Efficient eviction logic using `tail.prev`

---

## 🎯 Why This Solution is Optimal?

- Avoids linear scans ❌
- Maintains order dynamically ✅
- Constant time operations ✅
- Clean separation of concerns (Map + DLL) ✅

---

## 🧑‍💻 How to Run

```bash
javac LRUCache.java
java LRUCache
````

---

## 🔥 Key Takeaways

* LRU Cache is a **classic system design + DSA problem**
* Used in:

  * OS memory management
  * Database caching
  * Web browsers

---

## 📌 Author

**Hubert Spencer**

* Embedded + Software Developer
* Focus: Scalable Systems & Problem Solving

