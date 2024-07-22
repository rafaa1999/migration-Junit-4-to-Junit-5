package com.rafaa.rest;

import com.rafaa.bookmark.Bookmark;

import java.util.Collection;

public class BookmarkPayload {
    public String url;
    public String name;
    public Collection<String> tags;

    BookmarkPayload() {
    }

    public BookmarkPayload(String url, String name, Collection<String> tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    public Bookmark toBookmark() {
        return Bookmark.create(url, name, tags);
    }
}
