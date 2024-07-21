package com.rafaa.api;

import com.rafaa.bookmark.Bookmark;

import java.util.Collection;

@FunctionalInterface
public interface FindBookmarks {
    Collection<Bookmark> by(String tags);
}
