package com.rafaa.junit5;

import com.rafaa.bookmark.Tags;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

class TagsTest implements EqualityTest<Tags> {

    @Override
    public Tags createValue() {
        return Tags.of(emptySet());
    }

    @Override
    public Tags createOtherValue() {
        return Tags.of(singleton("tag"));
    }

}
