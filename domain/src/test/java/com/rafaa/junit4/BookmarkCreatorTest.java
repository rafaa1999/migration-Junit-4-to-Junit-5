package com.rafaa.junit4;

import com.rafaa.api.CreateBookmark;
import com.rafaa.bookmark.BookmarkCreator;
import com.rafaa.bookmark.Bookmarks;
import com.rafaa.stubs.InMemoryBookmarks;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Set;

import static java.util.Collections.singleton;

public class BookmarkCreatorTest {

    private static String url;
    private static String name;
    private static Set<String> tags;

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;

    @BeforeClass
    public static void global_set_up(){
        url = "http://www.test.com";
        name = "Some name";
        tags = singleton("tag");
    }

    @Before
    public void set_upt(){
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
    }

    @After
    public void tear_down(){
        bookmarks = null;
        createBookmark = null;
    }

}
