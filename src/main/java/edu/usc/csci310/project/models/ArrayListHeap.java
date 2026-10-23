package edu.usc.csci310.project.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListHeap<T extends Comparable<T>> { //sourced from GeeksForGeeks with modifications made as necessary

    private ArrayList<T> vec;

    public ArrayListHeap() {
        this.vec = new ArrayList<>();
    }

    public T get(int idx) {
        return vec.get(idx);
    }

    public int size() {
        return vec.size();
    }

    private int parent(int pos) {
        return (pos-1)/2;
    }
    private int leftChild(int pos) {
        return ( (pos * 2) + 1);
    }
    private int rightChild(int pos) {
        return ((pos * 2) + 2);
    }

    private boolean checkLeaf(int pos) {
        return ( (pos > vec.size()/2) && (pos <= vec.size()) );
    }

    private void swap(int pos1, int pos2) {
        Collections.swap(vec,pos1,pos2);
    }

    private void maxHeapify(int pos) {
        if (checkLeaf(pos)) {
            return;
        }

        //check if leftChild exists and is bigger than value at position
        //check the same for rightChild
        if ( (leftChild(pos) < vec.size() && vec.get(pos).compareTo(vec.get(leftChild(pos))) < 0)
                || (rightChild(pos) < vec.size() && vec.get(pos).compareTo(vec.get(rightChild(pos))) < 0)
        )

        {
            //check if leftChild is greater than rightChild. swap leftChild with pos if so, otherwise swap right with pos
            if (rightChild(pos) < vec.size() && vec.get(leftChild(pos)).compareTo(vec.get(rightChild(pos))) > 0) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    public void insert(T obj)
    {
        vec.add(obj);

        // Traverse up and restore heap property
        int current = vec.size() - 1;

        while (vec.get(current).compareTo(vec.get(parent(current))) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public T remove(int idx) { //swaps target with end of vec and restores maxheap property

        if (idx >= vec.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds for length " + vec.size());
        }

        if (vec.size() == 1) {
            return vec.remove(0);
        }

        T removed = vec.get(idx);
        swap(idx, vec.size()-1);
        vec.remove(vec.size() - 1);

        int compareVal = vec.get(idx).compareTo( vec.get(parent(idx)) );

        if (compareVal < 0) {
            maxHeapify(idx);
        }
        else if (compareVal > 0) {
            int current = idx;
            while (vec.get(current).compareTo(vec.get(parent(current))) > 0) {
                swap(current, parent(current));
                current = parent(current);
            }
        }
        return removed;
    }

    public int find(T obj) {

        for (int i = 0; i < vec.size(); i++) {
            if (get(i).equals(obj)) {
                return i;
            }
        }
        return -1;

    }

    public List<T> toList() {
        return vec;
    }
}
