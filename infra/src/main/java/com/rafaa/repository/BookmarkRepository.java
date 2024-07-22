package com.rafaa.repository;

import com.rafaa.bookmark.AlreadyBookmarkedException;
import com.rafaa.bookmark.Bookmark;
import com.rafaa.bookmark.Bookmarks;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookmarkRepository implements Bookmarks {

    private JpaBookmarkRepository repository;

    public BookmarkRepository(JpaBookmarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bookmark save(Bookmark bookmark) throws AlreadyBookmarkedException {
        String url = bookmark.getUrl();
        repository.findByUrl(url).ifPresent(b -> {
            throw new AlreadyBookmarkedException(url);
        });

        BookmarkEntity entity = BookmarkEntity.from(bookmark);
        return repository.save(entity).toValueObject();
    }

    @Override
    public Optional<Bookmark> getBy(String url) {
        return repository.findByUrl(url)
                .map(BookmarkEntity::toValueObject);
    }

    @Override
    public Collection<Bookmark> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(BookmarkEntity::toValueObject)
                .collect(Collectors.toList());
    }

}
