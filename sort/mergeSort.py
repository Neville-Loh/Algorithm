# -*- coding: utf-8 -*-
"""
Created on Tue May 12 02:18:32 2020

@author: Neville
"""

import queue



# Merger Sort(X) psudo code
# =============================================================================
# If X is the emplty list or contains one item only then output X.
# ----While X contains more than 1 item
# --------Find the midpoint x_k of X
# --------Define two lists A = x0 , x1, ... xk and B = xk+1, xn-1
# --------Set A = mergeSort(A) and B = MergerSort(B)
# ----End While
# End If
# Output Merge(A,B)
# =============================================================================

pQ = queue.PriorityQueue();

pQ.put(3)
pQ.put(4)

L = [1,2,3,4]
print(L.pop(0));
print(L);




"""
The function sort the list with merge sort

Parameter: List X
return: Sorted List of X 

Time Complexity = 
"""
def mergeSort(L):
    #Base case the 
    if (len(L) == 1):
        return L
    
    # merge sort the first half and the second half and merge them together
    else:
        mid = len(L) // 2
        A = mergeSort(L[0:mid])
        B = mergeSort(L[mid:])
        return merge(A,B)

"""
The function merge the two sorted list in to one

Parameters: List A, List B
return: Sorted list of A and B

Time Complexity = 
"""
def merge(A,B):
    result = []
    i = 0
    j = 0
    # while they are not empty compare the list
    while (i < len(A) and j < len(B)):
        a = A[i]
        b = B[j]
        if (a < b):
            result.append(a)
            i += 1
        else:
            result.append(b)
            j += 1
            
        
    # one of the list is fully processed
    if (i == len(A)):
        result += B[j:]
    else:
        result += A[i:]
    return result




L = [48, 36 , 16, 10, 5, 15, 24, 8, 6]
print(mergeSort(L))
