================================================================================================

                                          Java
                                       
                                          
================================================================================================
                                         
                                         C++
                                          
================================================================================================
# Constraints
* What is the maximum displacement that an element can have from its correct position? Clearly it is **n-1**. For example, the minimum element is present at the last index of the array, or the maximum element is present at the first index.
* Thus, we can clearly see that _k_ can be reduced to a number between **0** to **n-1**. How? If **k** is bigger than **n-1**, we simply set it to **n-1**.
* This reduces the constraints to **k lies between [0,n-1]**.

# Intuition
* Consider the minimum element of the array. Where can it actually be? 
* If _k_ was 0, it would be amongst the (1st) element, if _k_ was 1, it would be amongst the group of first two elements.
* Hence, the minimum element would be present in the group of first _(k+1)_ elements.
* We form a minHeap of the first _(k+1)_ elements, extract the minimum element and put it in the correct place.
* Now, where can the second minimum element be? Since, it is displaced by a max distance of _k_, it must be present in the group of (k+1) elements (starting from it). However, the first _k_ elements are already in the heap, so we need to insert just one element to the minHeap to find the second minimum element. 
* Inductively, at the end of the i-th loop, we can extract the i-th minimum and hence the array would be sorted.
* Note that the elements to the left (the previous minimums) are already in their correct place, and hence the current minimum element has to be present in the heap.

# Pseudocode
```
1) Normalize k to lie between [0,n-1]
2) Create a minHeap and push the first (k+1) elements in it
3) For each index of the array
        * Extract the current minimum from the heap
        * Place it at the current index
        * Push one (right) element to the heap
        * If all elements have been processed, do not push anything to the heap
```        

# Code Walkthrough

* `if(k>=n) k = n-1`
   * It is used to normalize _k_ to lie between [0,n-1].
* `priority_queue<int, vector<int>, greater<int> > minHeap` 
   * The first argument _int_ refers to the data type
   * The second argument _vector<int>_ represents the container used to create the heap
   * The third argument represents the comparator used to compare the elements
* A word about comparators
   * The default comparator in C++ is `lesser<int>`.
   * Any comparator `comp(int a, int b)` should return **True** if `a` is smaller than `b`
   * For example, `greater(4,3)` would return **True** (since 4 is bigger than 3). However, C++ will interpret 4 to be      smaller since the comparator returns **True**. Hence, the ordering of the elements is reversed and the heap would become a minHeap. 
       
* `if(rightIndex < n)`
   * After the last element has entered the heap, **rightIndex** would point to an element outside the array.
   * Clearly, we do not need to insert any new element in the heap now and the heap size will decrease by 1 in each iteration.

# Time Complexity
* Building a heap takes O(n) time if the heap is built in place using any container like array or vector
* However, in this case, we are building the heap by inserting each element one by one. Hence, **O(k*log(k))**
* The `for` loop runs **n** times
   * Viewing the minimum element is **O(1)**, extracting it is **O(log(k))**.
   * Pushing an element is again **O(k)**.
* The net contribution of the `for` loop is **O(n*log(k))**.
* Overall Time Complexity ====> **O(n*log(k))** + **O(k*log(k))** which is dominated by **O(n*log(k))**

# Space Complexity
* We only build one extra heap. At any time, the maximum number of elements in the heap is _(k+1)_
* Hence, the space complexity is **O(k)**.

# Example
Let the array be [10 9 8 7 4 70 60 50] and take k=4

Step 1) The heap contains (10 9 8 7 4)  
Step 2) Extract min and update a[0] = 4. Push 70 to the heap  
Step 3) Extract min and update a[1] = 7. Push 60 to the heap  
Step 4) Extract min and update a[2] = 8. Push 50 to the heap  
Step 5) Extract min and update a[3] = 9. Nothing to push  
Step 6) Extract min and update a[4] = 10. Nothing to push  
Step 7) Extract min and update a[5] = 50. Nothing to push  
Step 8) Extract min and update a[6] = 60. Nothing to push  
Step 9) Extract min and update a[7] = 70. Nothing to push  

The array is sorted now.

# Credits
[Stack Overflow](https://stackoverflow.com/questions/2726785/sorting-an-almost-sorted-array-elements-misplaced-by-no-more-than-k)
[GeeksForGeeks](https://www.geeksforgeeks.org/nearly-sorted-algorithm/)

================================================================================================
