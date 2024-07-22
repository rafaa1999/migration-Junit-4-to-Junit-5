package com.rafaa.config;

import com.rafaa.api.CreateBookmark;
import com.rafaa.api.FindBookmarks;
import com.rafaa.bookmark.BookmarkCreator;
import com.rafaa.bookmark.Bookmarks;
import com.rafaa.bookmark.BookmarksFinder;
import com.rafaa.repository.BookmarkRepository;
import com.rafaa.repository.JpaBookmarkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Bookmarks bookmarks(JpaBookmarkRepository jpaBookmarkRepository) {
        return new BookmarkRepository(jpaBookmarkRepository);
    }

    @Bean
    public CreateBookmark createBookmark(Bookmarks bookmarks) {
        return new BookmarkCreator(bookmarks);
    }

    @Bean
    public FindBookmarks findBookmarks(Bookmarks bookmarks) {
        return new BookmarksFinder(bookmarks);
    }

}
