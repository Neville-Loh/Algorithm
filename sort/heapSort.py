# -*- coding: utf-8 -*-
"""
Heap sort
Created on Thu May 28 09:31:57 2020

@author: Neville

Index method

                  root at 0       root at 1
Left child        index*2 + 1     index*2
Right child       index*2 + 2     index*2 + 1
Parent            (index-1)/2     index/2


"""
import math
from io import StringIO

# Printing tree
###########################################################
def childNodes(i):
     return (2*i)+1, (2*i)+2

def traversed(a, i=0, d = 0):
    if i >= len(a):
        return 
    l, r =  childNodes(i)
    traversed(a, r, d = d+1)
    print("   "*d + str(a[i]))
    traversed(a, l, d = d+1)

def printTree(L):
    traversed(L)
    print("----------------------")
#######################################
    
    
    


class Heap:

    def __init__(self, L = []):
        self.heap = L;


    #########################        
    # Helper function 
    def swap(self, i,j):
        temp = self.heap[i]
        self.heap[i] = self.heap[j]
        self.heap[j] = temp
    
    
    def left(self, i):
        return 2*i + 1
    
    def right(self, i):
        return 2*i + 2
    
    def parent(self,i):
        return (i-1)//2
    
    def hasLeft(self, i):
        return self.left(i) <= len(self.heap) 
    
    def index(self, i):
        return self.heap.index(i)
    
    def get(self,i):
        return self.heap[i]
    
    def __str__(self):
        return str(self.heap)
    
    
    ####################################    

    
    
    def heapifyUp(self,i):
        
        #####
        print("HeapifyUp ", end = '')
        print(self.heap)
        #printTree(self.heap)
        #####
        
        if i > 0:
            j = self.parent(i)
            if self.heap[i] < self.heap[j]:
                self.swap(i,j)
                self.heapifyUp(j)
                
    
    """
    if right >= current node stop
    if left < n  ,   swap n and left, s 
    """
    def heapifyDown(self,i):
        
        ######
        print("HeapifyDown " , end = '')
        print(self.heap)
        #printTree(self.heap)
        #####
        
        
        leftIndex = self.left(i)
        rightIndex = self.right(i)
        
        if leftIndex >= len(self.heap):
            return
        elif rightIndex >= len(self.heap):
            left = self.get(leftIndex)
            if (self.get(i) > left):
                self.swap(i,leftIndex)
            return
        
        parent = self.get(i)
        left = self.get(leftIndex)
        right = self.get(rightIndex)
        
        if (parent > left or parent > right):
            if left < right:
                self.swap(i,leftIndex)
                self.heapifyDown(leftIndex)
            else:
                self.swap(i,rightIndex)
                self.heapifyDown(rightIndex)
                
        
        
        
    
    
    """
    Add element to the heap while maintaining the heap structure.
    """
    def append(self, ele):
        print("\nAppending number ", ele)
        self.heap.append(ele)
        self.heapifyUp(len(self.heap)- 1)
        
    """
    Get and removed the least element of heap
    """
    def poll(self):
        self.swap(0,-1)
        val = self.heap.pop(-1)
        self.heapifyDown(0)
        return val
        
        

    
def heapSort(L):
    
    result = []
    
    h = Heap()
    for ele in L:
        h.append(ele) 
    
    print("Final heap tree = ", h.heap ,"\n\n")
    while (len(h.heap) != 0):
        
        print("\nPolling number ", h.heap[0])
        val = h.poll()
        result.append(val)
    
    print("Final Result = ", result)
    return result
    

    
    
L1 = [3,8,4,10,9,5,7,13,17,18,12]
L2 = [8, 9, 11, 13, 12, 13, 18, 15, 10, 20]
L3 = [2, 5, 6, 3, 4, 7, 1, 0, 9, 8]

heapSort(L3)
# =============================================================================
# h = Heap(L2)
# h.append(7)
# 
# printTree(L2)
# 
# 
# print(h);
# =============================================================================
    
    
# =============================================================================
# L = [2, 5, 6, 3, 4, 7, 1, 0, 9, 8]
# HeapSort(L)
# =============================================================================
