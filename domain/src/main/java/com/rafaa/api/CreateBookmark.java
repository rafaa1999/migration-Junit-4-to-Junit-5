package com.rafaa.api;

import com.rafaa.bookmark.Bookmark;

import java.util.Collection;

@FunctionalInterface
public interface CreateBookmark {
    Bookmark forResource(String url, String name, Collection<String> tags);
}
