package com.globant.bootcamp;

import java.util.List;

public interface Blog {
	void post(Post entry);

	void removePost(int postIndex);

	List<Post> getLastPosts();

	Post getLastEntry();

	int getPostCount();

	boolean isEmpty();
}
