package com.rafaa.junit5;

import com.rafaa.api.FindBookmarks;
import com.rafaa.bookmark.Bookmark;
import com.rafaa.bookmark.Bookmarks;
import com.rafaa.bookmark.BookmarksFinder;
import com.rafaa.stubs.InMemoryBookmarks;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BookmarksFinderTest {

    private static Bookmarks bookmarks;
    private static FindBookmarks findBookmarks;

    @BeforeAll
    static void setUp() {
        bookmarks = new InMemoryBookmarks();
        findBookmarks = new BookmarksFinder(bookmarks);
    }

    @MethodSource("testCases")
    @DisplayName("Should find bookmarks by tag")
    @ParameterizedTest(name = "\"{0}\" tag")
    void should_find_bookmarks_by_tag(String tag, Bookmark[] expected) {
        //given
        FindBookmarks findBookmarks = new BookmarksFinder(bookmarks);
        //when
        Collection<Bookmark> bookmarks = findBookmarks.by(tag);
        //then
        assertThat(bookmarks).containsExactlyInAnyOrder(expected);
    }

    static Stream<Arguments> testCases() {
        bookmarks = new InMemoryBookmarks();

        Bookmark junit = saveBookmark("https://junit.org", "JUnit", "tests");
        Bookmark cucumber = saveBookmark("https://cucumber.io", "Cucumber", "tests", "bdd");
        Bookmark bdd = saveBookmark("https://en.wikipedia.org/wiki/BDD", "BDD", "bdd", "method");

        return Stream.of(
                arguments("tests", new Bookmark[]{junit, cucumber}),
                arguments("bdd", new Bookmark[]{cucumber, bdd}),
                arguments("method", new Bookmark[]{bdd})
        );
    }

    private static Bookmark saveBookmark(String url, String name, String... tags) {
        Bookmark bookmark = Bookmark.create(url, name, asList(tags));
        return bookmarks.save(bookmark);
    }
}
