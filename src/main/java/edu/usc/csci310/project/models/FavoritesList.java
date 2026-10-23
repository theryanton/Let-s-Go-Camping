package edu.usc.csci310.project.models;

import java.util.List;

public class FavoritesList {
    private ArrayListHeap<Favorite> maxHeapList;

    public FavoritesList() {
        this.maxHeapList = new ArrayListHeap<>();
    }

    public boolean isEmpty() {
        return this.maxHeapList.size() == 0;
    }

    public int size() {
        return this.maxHeapList.size();
    }

    public Favorite getFavoriteAt(int idx) {
        return this.maxHeapList.get(idx);
    }

    public void insert(Favorite f) {
        maxHeapList.insert(f);
    }

    public void remove(int idx) {
        maxHeapList.remove(idx);
    }

    public int find(Favorite fav) {
        for (int i = 0; i < maxHeapList.size(); i++) {
            if (this.getFavoriteAt(i).equals(fav)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < maxHeapList.size(); i++) {
            res += this.getFavoriteAt(i).park_id;
            res += ",";
        }
        return res;
    }

    public List<Favorite> toList() {
        return maxHeapList.toList();
    }
}
