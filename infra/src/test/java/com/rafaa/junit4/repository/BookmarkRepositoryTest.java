package com.rafaa.junit4.repository;

import com.rafaa.bookmark.AlreadyBookmarkedException;
import com.rafaa.bookmark.Bookmark;
import com.rafaa.repository.BookmarkRepository;
import com.rafaa.repository.JpaBookmarkRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookmarkRepositoryTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private JpaBookmarkRepository repository;

    private static final String URL = "http://www.test.com";

    private BookmarkRepository bookmarkRepository;
    private Bookmark bookmark;

    @Before
    public void set_up(){
        bookmarkRepository = new BookmarkRepository(repository);
        bookmark = Bookmark.create(URL,"test", singleton("tag"));
    }

    @Test
    public void should_save_bookmark(){
        Bookmark saved = bookmarkRepository.save(bookmark);
        assertThat(saved).isEqualTo(bookmark);
    }

    @Test
    public void should_not_save_an_already_existent_bookmark(){
        bookmarkRepository.save(bookmark);

        exception.expect(AlreadyBookmarkedException.class);
        bookmarkRepository.save(bookmark);
    }

    @Test
    public void should_retrieve_bookmark_by_url(){
        bookmarkRepository.save(bookmark);

        Optional<Bookmark> retrieved = bookmarkRepository.getBy(URL);
        assertThat(retrieved).hasValue(bookmark);
    }

    @Test
    public void should_retrieve_all_bookmarks(){
        bookmarkRepository.save(bookmark);

        Collection<Bookmark> retrieved = bookmarkRepository.getAll();
        assertThat(retrieved).contains(bookmark);
    }

}
