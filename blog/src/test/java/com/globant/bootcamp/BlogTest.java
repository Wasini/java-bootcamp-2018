package com.globant.bootcamp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

public class BlogTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	private Blog blog;

	@Mock
	Post entryMock1;

	@Mock
	Post entryMock2;

	@Before
	public void setUp() {
		blog = BlogImpl.createBlogImpl();
	}

	@Test
	public void whenBlogInitializesItHasNoPosts() {
		Blog freshBlog = BlogImpl.createBlogImpl();
	}

	@Test
	public void whenNewEntryIsPostedThenBlogContainsTheNewEntry() {
		String postContent = "Hello there";
		String postAuthor = "General Kenobi";
		when(entryMock1.getContent()).thenReturn(postContent);
		when(entryMock1.getAuthor()).thenReturn(postAuthor);

		blog.post(entryMock1);

		Post lastEntry = blog.getLastEntry();
		assertThat(lastEntry.getContent(), is(equalTo(postContent)));
		assertThat(lastEntry.getAuthor(), is(equalTo(postAuthor)));
	}

	@Test
	public void whenEntryIsPostedThenBlogPostCountIncreases() {
		int previousBlogPostCount = blog.getPostCount();
		blog.post(entryMock1);
		assertThat(blog.getPostCount(), is(greaterThan(previousBlogPostCount)));
	}

	@Test
	public void whenDeletingEntryThenBlogPostCountDecreases() {
		blog.post(entryMock1);
		int previousBlogPostCount = blog.getPostCount();
		blog.removePost(0);
		assertThat(blog.getPostCount(), is(lessThan(previousBlogPostCount)));
	}

	@Test
	public void ifThereAreLessThan10PostsThenGetPostHistoryReturnsTheCurrentBlogPosts() {
		final int postedAmount = 6;
		String postContent = "Spam!";
		when(entryMock1.getContent()).thenReturn(postContent);

		addPostsToBlog(entryMock1, postedAmount);
		List<Post> lastPosts = blog.getLastPosts();
		assertThat(lastPosts.size(), equalTo(postedAmount));
		for (Post post : lastPosts) {
			assertThat(post, hasProperty("content", is(postContent)));
		}
	}

	@Test
	public void ifThereAreMoreThan10PostThenGetPostHistoryReturnsTheLast10Posts() {
		final int postsLimit = 10;
		String oldPostContent = "This post is old";
		String moreSpamContent = "Spam spam spam";
		when(entryMock2.getContent()).thenReturn(oldPostContent);
		when(entryMock1.getContent()).thenReturn(moreSpamContent);

		addPostsToBlog(entryMock2, 3);
		addPostsToBlog(entryMock1, 10);
		List<Post> lastPosts = blog.getLastPosts();
		assertThat(lastPosts.size(), equalTo(postsLimit));
		for (Post post : lastPosts) {
			assertThat(post, hasProperty("content", not(equalTo(oldPostContent))));
		}
	}

	private void addPostsToBlog(Post post, int amount) {
		for (int i = 0; i < amount; i++) {
			blog.post(post);
		}
	}
}
