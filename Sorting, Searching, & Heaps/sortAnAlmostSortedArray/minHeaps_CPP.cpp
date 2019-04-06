/*
 * @author Just__a__Visitor
 */

#include<bits/stdc++.h>
using namespace std;

class Solution
{
    public:
    void sort_a_nearly_sorted_array(vector<int> &a, int k);
};

/* Sorts a nearly sorted array */
void Solution :: sort_a_nearly_sorted_array(vector<int> &a, int k)
{
    // Extract the total numbers in the array
    int n = a.size();

    // The minimum and maximum displacement can be 0 and n-1
    if(k>=n) k = n-1;

    // Create the min heap
    priority_queue<int, vector<int>, greater<int> > minHeap;

    // Fill the first (k+1) elements in the min heap
    for(int i=0; i<(k+1); i++)
        minHeap.push(a[i]);

    // Initialize the indexes
    int rightIndex = (k+1);

    /* At any time, leftIndex represents the index to be corrected
     * rightIndex represents the first index (to the right) which
     * is not present in the minHeap
     */


    // Keep correcting each of the indexes
    for(int leftIndex=0; leftIndex<n; leftIndex++)
    {
        // Extract the minimum element
        int minElement = minHeap.top();
        minHeap.pop();

        // Put the minimum in the correct place
        a[leftIndex] = minElement;

        // Push one element to keep the size of the  minHeap as (k+1)
        if(rightIndex < n) 
        {
            minHeap.push(a[rightIndex]);
            rightIndex++;
        }
    }
}

void scanVector(vector<int> &a)
{
    for(auto &ele:a)
        cin >> ele;
}

void printVector(vector<int> &a)
{
    cout << "Printing the contents of the vector" << endl;
  
    for(auto ele:a)
        cout << ele << " ";

    cout << endl << "The vector has been printed" << endl;
}

int main()
{
    int size,displacement;
    cin >> size >> displacement;

    vector<int> a(size);
    scanVector(a);

    Solution myObject;
    myObject.sort_a_nearly_sorted_array(a,displacement);

    printVector(a);
	return 0;
}

/*
8 4
10 9 8 7 4 70 60 50
*/
