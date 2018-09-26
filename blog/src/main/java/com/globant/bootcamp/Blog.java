package com.globant.bootcamp;

public interface Blog {
    void post(Post entry);
    
    Post getLastEntry();
    
    int getPostCount();
}
