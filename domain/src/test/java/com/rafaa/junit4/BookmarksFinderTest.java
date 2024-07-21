package com.rafaa.junit4;

import com.rafaa.api.FindBookmarks;
import com.rafaa.bookmark.Bookmark;
import com.rafaa.bookmark.Bookmarks;
import com.rafaa.bookmark.BookmarksFinder;
import com.rafaa.stubs.InMemoryBookmarks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BookmarksFinderTest {

    private static Bookmarks bookmarks;

    @Parameter
    public String tag;

    @Parameter(1)
    public Bookmark[] expected;

    @Test
    public void should_find_bookmarks_by_tag() {
        //given
        FindBookmarks findBookmarks = new BookmarksFinder(bookmarks);
        //when
        Collection<Bookmark> bookmarks = findBookmarks.by(tag);
        //then
        assertThat(bookmarks).containsExactlyInAnyOrder(expected);
    }

    @Parameters
    public static Collection<Object[]> testCases() {
        bookmarks = new InMemoryBookmarks();

        Bookmark junit = saveBookmark("https://junit.org", "JUnit", "tests");
        Bookmark cucumber = saveBookmark("https://cucumber.io", "Cucumber", "tests", "bdd");
        Bookmark bdd = saveBookmark("https://en.wikipedia.org/wiki/BDD", "BDD", "bdd", "method");

        return Arrays.asList(new Object[][]{
                {"tests", new Bookmark[]{junit, cucumber}},
                {"bdd", new Bookmark[]{cucumber, bdd}},
                {"method", new Bookmark[]{bdd}}
        });
    }

    private static Bookmark saveBookmark(String url, String name, String... tags) {
        Bookmark bookmark = Bookmark.create(url, name, asList(tags));
        return bookmarks.save(bookmark);
    }

}
