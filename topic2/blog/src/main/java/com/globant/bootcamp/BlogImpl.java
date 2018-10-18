package com.globant.bootcamp;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BlogImpl implements Blog {

	private static final int POSTS_PER_HISTORY = 10;

	private static final int LAST_POST_INDEX = 0;

	private List<Post> PostHistory;

	private BlogImpl() {
		PostHistory = new ArrayList<Post>();
	}

	public static BlogImpl createBlogImpl() {
		return new BlogImpl();
	}

	public Post getLastEntry() {
		if (isEmpty())
			throw new IllegalStateException("Can't retrieve posts, the blog is empty");
		return PostHistory.get(LAST_POST_INDEX);
	}

	public int getPostCount() {
		return PostHistory.size();
	}

	public boolean isEmpty() {
		return PostHistory.isEmpty();
	}

	public void post(@NonNull Post entry) {
		PostHistory.add(LAST_POST_INDEX, entry);
	}

	public void removePost(int postIndex) {
		validateIndex(postIndex);
		PostHistory.remove(postIndex);
	}

	public List<Post> getLastPosts() {
		return PostHistory.subList(LAST_POST_INDEX,
				PostHistory.size() < POSTS_PER_HISTORY ? PostHistory.size() : POSTS_PER_HISTORY);
	}

	private void validateIndex(int index) {
		if (index < LAST_POST_INDEX || index >= PostHistory.size())
			throw new IllegalArgumentException("Index is invalid");
	}
}
