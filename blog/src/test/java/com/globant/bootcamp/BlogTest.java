package com.globant.bootcamp;

import lombok.Getter;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlogTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	/* Used for reflection */
	private static final String POST_CONTENT_PROPERTY = "content";

	private Blog blog;

	@Mock
	Post entryMock;

	@Before
	public void setUp() {
		blog = BlogImpl.createBlogImpl();
	}

	@Test
	public void whenBlogInitializesItHasNoPosts() {
		Blog freshBlog = BlogImpl.createBlogImpl();
		assertTrue(freshBlog.isEmpty() && freshBlog.getPostCount() == 0);
	}

	@Test(expected = Exception.class)
	public void whenGettingLastEntryOfEmptyBlogThenItShouldThrowAnException() {
		Blog freshBlog = BlogImpl.createBlogImpl();
		freshBlog.getLastEntry();
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
		blog.post(entryMock);
		int previousBlogPostCount = blog.getPostCount();
		blog.removePost(0);
		assertThat(blog.getPostCount(), is(lessThan(previousBlogPostCount)));
	}

	@Test
	public void ifThereAreLessThan10PostsThenGetPostHistoryReturnsTheCurrentBlogPosts() {
		final int postedAmount = 6;
		String postContent = "Spam!";
		new BlogSpammer(blog).spam(postContent, postedAmount);

		List<Post> lastPosts = blog.getLastPosts();
		assertThat(lastPosts.size(), equalTo(postedAmount));
		for (Post post : lastPosts) {
			assertThat(post, hasProperty(POST_CONTENT_PROPERTY, is(postContent)));
		}
	}

	@Test
	public void ifThereAreMoreThan10PostThenGetPostHistoryReturnsTheLast10Posts() {
		final int postsLimit = 10;
		String oldPostContent = "This post is old";
		String moreSpamContent = "Spam spam spam";
		new BlogSpammer(blog).spam(oldPostContent, 5).spam(moreSpamContent, postsLimit);

		List<Post> lastPosts = blog.getLastPosts();
		assertThat(lastPosts.size(), equalTo(postsLimit));
		for (Post post : lastPosts) {
			assertThat(post, hasProperty(POST_CONTENT_PROPERTY, not(equalTo(oldPostContent))));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenRemovingNonExisitingPostThenItAnException() {
		blog.removePost(blog.getPostCount() + 1);
	}

	private class BlogSpammer {

		@Getter
		private final Blog blog;

		public BlogSpammer(Blog blog) {
			this.blog = blog;
		}

		public BlogSpammer spam(int times) {
			for (int i = 0; i < times; i++) {
				blog.post(mock(Post.class));
			}
			return this;
		}

		public BlogSpammer spam(String content, int times) {
			Post postWithContent;
			for (int i = 0; i < times; i++) {
				postWithContent = mock(Post.class);
				when(postWithContent.getContent()).thenReturn(content);
				blog.post(postWithContent);
			}
			return this;
		}
	}
}