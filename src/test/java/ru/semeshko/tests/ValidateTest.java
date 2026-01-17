package ru.semeshko.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.semeshko.annotations.Validate;
import ru.semeshko.handlers.ValidateHandler;

/**
 * Тест №4: проверка @Validate.
 */
public class ValidateTest {

    @Validate({String.class, Integer.class})
    static class Ok { }

    @Validate({})
    static class Empty { }

    /**
     * Проверяем, что массив классов извлекается корректно.
     */
    @Test
    public void validateReturnsClasses() {
        ValidateHandler h = new ValidateHandler("test");
        Class<?>[] arr = h.getOrThrow(Ok.class);

        Assertions.assertEquals(2, arr.length);
        Assertions.assertEquals(String.class, arr[0]);
        Assertions.assertEquals(Integer.class, arr[1]);
    }

    /**
     * Проверяем, что при пустом массиве выбрасывается IllegalArgumentException.
     */
    @Test
    public void validateEmptyThrows() {
        ValidateHandler h = new ValidateHandler("test");
        Assertions.assertThrows(IllegalArgumentException.class, () -> h.getOrThrow(Empty.class));
    }
}