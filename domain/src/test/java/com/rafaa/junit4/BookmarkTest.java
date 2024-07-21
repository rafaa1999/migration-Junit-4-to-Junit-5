package com.rafaa.junit4;

import com.rafaa.bookmark.Bookmark;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import static java.util.Collections.singleton;

public class BookmarkTest {

   @Rule
   public final ExpectedException exception = ExpectedException.none();

   @Test
   public void should_create_the_bookmark(){
       //given
       String url = "http://www.test.com";
       String name = "Some name";
       Set<String> tags = singleton("tag");
       //when
       Bookmark createdBookmark = Bookmark.create(url, name, tags);
       //then
       assertThat(createdBookmark.getUrl()).isEqualTo(url);
       assertThat(createdBookmark.getName()).isEqualTo(name);
       assertThat(createdBookmark.getTags()).isEqualTo(tags);
   }

   @Test
   public void should_not_create_invalid_url(){
       //given
       exception.expect(IllegalArgumentException.class);
       //when
       Bookmark.create("invalid_url","name",emptySet());
   }

   @Test
   public void should_not_accept_an_empty_name(){
       //given
       exception.expect(IllegalArgumentException.class);
       //when
       Bookmark.create("invalid_url","",emptySet());
   }

   @Test
   public void should_not_accept_an_blank_name(){
       //given
       exception.expect(IllegalArgumentException.class);
       //when
       Bookmark.create("invalid_url","  ",emptySet());
   }

}
