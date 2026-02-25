# Java Linked Set

A generic Set collection implemented using a **doubly-linked list** as the underlying data structure. Built as part of a Data Structures & Algorithms course at Auburn University.

---

## Overview

This project implements the `Set` interface using a doubly-linked list that maintains elements in **ascending natural order** at all times. It supports standard set operations as well as optimized overloaded methods that take advantage of the underlying linked structure for better performance.

---

## Classes

### `Set<T>` (Interface)
Defines the full contract for a generic set collection including general collection methods, set operators, and iteration.

### `LinkedSet<T extends Comparable<T>>` (Implementation)
Implements the `Set` interface using a doubly-linked list. The type parameter `T` must implement `Comparable`, ensuring a natural order exists on all stored elements.

---

## Features

### General Collection Methods
| Method | Description | Complexity |
|---|---|---|
| `add(T element)` | Adds element maintaining sorted order | O(N) |
| `remove(T element)` | Removes element maintaining sorted order | O(N) |
| `contains(T element)` | Checks if element exists in set | O(N) |
| `size()` | Returns number of elements | O(1) |
| `isEmpty()` | Returns true if set is empty | O(1) |
| `iterator()` | Returns ascending order iterator | O(1) |

### Set Operators (Generic `Set<T>` parameter)
| Method | Description | Complexity |
|---|---|---|
| `union(Set<T> s)` | Returns A ∪ B | O(N²) |
| `intersection(Set<T> s)` | Returns A ∩ B | O(N²) |
| `complement(Set<T> s)` | Returns A \ B | O(N²) |
| `equals(Set<T> s)` | Returns true if sets are equal | O(N²) |

### Optimized Operators (LinkedSet parameter)
When the parameter is also a `LinkedSet`, the sorted structure of both lists enables more efficient algorithms:

| Method | Complexity |
|---|---|
| `union(LinkedSet<T> s)` | O(N) |
| `intersection(LinkedSet<T> s)` | O(N) |
| `complement(LinkedSet<T> s)` | O(N) |
| `equals(LinkedSet<T> s)` | O(N) |

### Additional Iterators
| Method | Description |
|---|---|
| `descendingIterator()` | Iterates elements in descending order |
| `powerSetIterator()` | Iterates over all 2^N subsets of the set |

---

## Example

```java
LinkedSet<Integer> a = new LinkedSet<>();
a.add(1); a.add(2); a.add(3);

LinkedSet<Integer> b = new LinkedSet<>();
b.add(2); b.add(3); b.add(4);

Set<Integer> union = a.union(b);        // {1, 2, 3, 4}
Set<Integer> intersection = a.intersection(b); // {2, 3}
Set<Integer> complement = a.complement(b);     // {1}
```

---

## Tech Stack
- Java
- Generics & Comparable
- Custom doubly-linked list (no java.util.LinkedList)

---
