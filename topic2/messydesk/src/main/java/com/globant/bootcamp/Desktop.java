package com.globant.bootcamp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import lombok.NonNull;


public class Desktop {

    private static final int CAPACITY = 15;

    private Deque<File> fileHistory;
    private Set<File> fileCache;

    public Desktop(){
        fileHistory = new LinkedList<File>();
        fileCache = new HashSet<File>();
    }

    public boolean hasRecentFiles() {
        return !fileHistory.isEmpty();
    }

    public void openFile(@NonNull File file) {
        file.read();
        updateHistory(file);
    }

    public boolean hasRecentlyOpen(File file) {
        return fileCache.contains(file);
    }

    public File getLastOpenedFile() {
        return fileHistory.getFirst();
    }

    public File getOldestOpenedFile() {
        return fileHistory.getLast();
    }

    public Collection<Object> getFileHistory() {
        return Arrays.asList(fileHistory.toArray());
    }
    
    public boolean reachedCapacity() {
        return fileHistory.size() == CAPACITY;
    }
    
    private void updateHistory(File file) {
        if (fileCache.contains(file))
            fileHistory.remove(file);
        if (reachedCapacity()) 
            fileCache.remove(fileHistory.removeLast());
        fileCache.add(file);
        fileHistory.addFirst(file);
    }
}
