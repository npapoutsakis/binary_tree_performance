# Binary Tree Performance

A Java implementation comparing the performance of different data structures for search operations: Simple Binary Search Tree, Threaded Binary Search Tree, and Binary Search in a Sorted Array.

## Overview

This project evaluates and compares the efficiency of three different data structures for various search operations using comparison counting as a performance metric:

1. **Simple Binary Search Tree**
2. **Threaded Binary Search Tree**
3. **Sorted Array with Binary Search**

The implementation provides detailed metrics on the average number of comparisons required for different operations including insertion, single key searches, and range searches with different ranges.

## Features

- **Data Structure Implementations**:
  - Simple Binary Search Tree
  - Threaded Binary Search Tree
  - Sorted Array with Binary Search
  
- **Operations Tested**:
  - Key insertion (for tree structures)
  - Single key search
  - Range search with small range (100)
  - Range search with large range (1000)

- **Performance Metrics**:
  - Comparison count for each operation
  - Average comparison counts across multiple operations

## Test Configuration

The tests are configured with the following parameters:
- Number of nodes: 100,000
- Key range: 1 to 1,000,000
- Number of searches: 100
- Small range search: Current key to +100
- Large range search: Current key to +1000


## Features of Each Data Structure

### Simple Binary Search Tree
- Traditional binary search tree implementation with left/right child pointers
- Uses a 2D array structure for node storage
- Simple recursive implementation for operations

### Threaded Binary Search Tree
- Enhanced binary tree with thread pointers to successor/predecessor nodes
- Uses a 2D array with additional columns for thread flags
- Provides more efficient traversal without recursion

### Sorted Array with Binary Search
- Uses binary search algorithm on a sorted array
- No insertion time measured (assumes pre-sorted data)
- Efficient for search operations

## Optional Features

You can optionally load keys from a file by uncommenting and setting the `fileName` variable in the `Main` class.

## Performance Analysis

A detailed performance analysis report is included with the project, comparing the efficiency of each data structure across different operations and providing insights into their strengths and weaknesses.
