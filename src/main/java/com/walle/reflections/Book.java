package com.walle.reflections;

import java.lang.reflect.Field;
import java.util.Arrays;
import static java.lang.System.out;

/**
 * @author: bbpatience
 * @date: 2019/12/20
 * @description: Book
 **/

enum Tweedle { DEE, DUM }

public class Book {
    private long chapters = 0;
    public String[] characters = { "Alice", "White Rabbit" };
    private final Tweedle twin = Tweedle.DEE;

    public static void main(String... args) {
        Book book = new Book();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();

            Field chap = c.getDeclaredField("chapters");
            out.format(fmt, "before", "chapters", book.chapters);
            chap.setLong(book, 12);
            out.format(fmt, "after", "chapters", chap.getLong(book));

            Field chars = c.getDeclaredField("characters");
            out.format(fmt, "before", "characters", Arrays.asList(book.characters));
            String[] newChars = {"Queen", "King"};
            chars.set(book, newChars);
            out.format(fmt, "after", "characters", Arrays.asList(book.characters));

            Field t = c.getDeclaredField("twin");
            out.format(fmt, "before", "twin", book.twin);
            t.setAccessible(true);
            t.set(book, Tweedle.DUM);
            out.format(fmt, "after", "twin", t.get(book));

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
