package com.rafaa.junit5;

import com.rafaa.bookmark.Bookmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookmarkTest {

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "http://www.test.com, Some name, tag",
            "http://www.test.com/path, Some other name, another-tag"
    })
    @DisplayName("Should create the bookmark")
    void should_create_the_bookmark(String url, String name, String tag){
        //given
        Set<String> tags = singleton(tag);
        //when
        Bookmark createdBookmark = Bookmark.create(url, name, tags);
        //then
        assertAll(
                () -> assertThat(createdBookmark.getUrl()).isEqualTo(url),
                () -> assertThat(createdBookmark.getName()).isEqualTo(name),
                () -> assertThat(createdBookmark.getTags()).isEqualTo(tags)
        );
    }

    @Test
    void should_not_create_invalid_url(){
        assertThrows(
               IllegalArgumentException.class,
                () -> Bookmark.create("invalid_url","name",emptySet())
        );
    }

    @Test
    void should_not_accept_an_empty_name(){
        assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("invalid_url","",emptySet())
        );
    }

    @Test
    void should_not_accept_an_blank_name(){
        assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("invalid_url","  ",emptySet())
        );
    }
}
