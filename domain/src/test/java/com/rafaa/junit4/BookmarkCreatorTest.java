package com.rafaa.junit4;

import com.rafaa.api.CreateBookmark;
import com.rafaa.bookmark.Bookmark;
import com.rafaa.bookmark.BookmarkCreator;
import com.rafaa.bookmark.Bookmarks;
import com.rafaa.stubs.InMemoryBookmarks;
import org.junit.*;

import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void should_create_bookmark(){
        //given
        createBookmark.forResource(url,name,tags);
        Optional<Bookmark> saved = bookmarks.getBy(url);
        //when
        Bookmark expected = Bookmark.create(url,name,tags);
        //then
        assertThat(saved).hasValue(expected);
    }

    @Test
    public void should_return_the_bookmark_after_creating_it(){
        //given
        Bookmark bookmark = createBookmark.forResource(url, name, tags);
        //when
        //then
        assertThat(bookmark).isNotNull();
    }

    @Test
    @Ignore
    public void should_not_be_run(){
        Assert.fail();
    }

}
