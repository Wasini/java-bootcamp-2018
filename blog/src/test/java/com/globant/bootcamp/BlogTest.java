package com.globant.bootcamp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BlogTest {
    
    private Blog blog;
    
    @Mock
    Post entryMock;
    
    @Before
    public void setUp() {
        blog = new BlogImpl();
    }
    
    @Test
    public void whenNewEntryIsPostedThenBlogContainsTheNewEntry() {  
        String postContent = "Hello there";
        String postAuthor = "General Kenobi";
        when(entryMock.getContent()).thenReturn(postContent);
        when(entryMock.getAuthor()).thenReturn(postAuthor);
        
        blog.post(entryMock);
        
        Post lastEntry = blog.getLastEntry();
        assertThat(lastEntry.getContent(), is(equalTo(postContent)));
        assertThat(lastEntry.getAuthor(), is(equalTo(postAuthor)));
    }
    
    @Test
    public void whenEntryIsPostedThenBlogPostCountIncreases() {
        int previousBlogPostCount = blog.getPostCount();
        blog.post(entryMock);
        assertThat(blog.getPostCount(), is(greaterThan(previousBlogPostCount)));
    }
    
    @Test
    public void whenDeletingEntryThenBlogPostCountDecreases() {
        int previousBlogPostCount = blog.getPostCount();
        blog.post(entryMock);
        assertThat(blog.getPostCount(), is(lessThan(previousBlogPostCount)));
    }
}
