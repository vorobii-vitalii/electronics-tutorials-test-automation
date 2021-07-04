package utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    private TestUtils() {
    }

    public static <T extends Comparable<T>> void verifySorted(List<T> categoriesNames) {
        final int N = categoriesNames.size();

        for (int i = 1; i < N; i++) {
            Comparable<T> previousCategory = categoriesNames.get(i - 1);

            assertNegative(previousCategory.compareTo(categoriesNames.get(i)) );
        }
    }

    private static void assertNegative(int num) {
        assertTrue(num < 0);
    }

}
