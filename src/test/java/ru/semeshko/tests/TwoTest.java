package ru.semeshko.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.semeshko.annotations.Two;
import ru.semeshko.handlers.TwoHandler;

/**
 * Тест №6: некорректная @Two должна выбрасывать IllegalArgumentException.
 */
public class TwoTest {

    @Two(first = "", second = -1)
    static class Bad { }

    @Two(first = "ok", second = 1)
    static class Ok { }

    /**
     * Если first пустой или second отрицательный — исключение.
     */
    @Test
    public void twoBadThrows() {
        TwoHandler h = new TwoHandler("test");
        Assertions.assertThrows(IllegalArgumentException.class, () -> h.validateOrThrow(Bad.class));
    }

    /**
     * Корректные значения — исключения быть не должно.
     */
    @Test
    public void twoOkDoesNotThrow() {
        TwoHandler h = new TwoHandler("test");
        Assertions.assertDoesNotThrow(() -> h.validateOrThrow(Ok.class));
    }
}