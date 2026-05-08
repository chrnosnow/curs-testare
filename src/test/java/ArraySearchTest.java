import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySearchTest {

    ArraySearch arraySearch = new ArraySearch();

    @Nested
    class EquivalencePartitioningTest {
        @Test
        void testKeyPresent() {
            // Test case 1: Key is present in the array
            int[] v = {-10, 20, -30, -40, 50};
            int key = -30;
            int result = arraySearch.findIndex(v, key);
            assertEquals(0, result);
        }

        @Test
        void testKeyNotPresent() {
            // Test case 2: Key is not present in the array
            int[] v = {-10, 20, -30, -40, 50};
            int key = 99;
            int result = arraySearch.findIndex(v, key);
            assertEquals(-1, result);
        }

        @Test
        void testMultipleKeysPresent() {
            // Test case 3: Multiple keys are present in the array
            int[] v = {-10, 20, -30, 20, 50};
            int key = 20;
            int result = arraySearch.findIndex(v, key);
            assertEquals(1, result);
        }

        @Test
        void testArrayIsNull() {
            // Test case 4: Array is null
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(null, key));
            assertEquals("Vectorul nu poate fi null.", exception.getMessage());
        }

        @Test
        void testArrayLengthSmallerThanFive() {
            // Test case 5: Array length is smaller than 5
            int[] v = {-10, 20, -30};
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(v, key));
            assertEquals("Vectorul trebuie sa aiba lungimea 5.", exception.getMessage());
        }

        @Test
        void testArrayLengthLargerThanFive() {
            // Test case 6: Array length is larger than 5
            int[] v = {-10, 20, -30, -40, 50, 60};
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(v, key));
            assertEquals("Vectorul trebuie sa aiba lungimea 5.", exception.getMessage());
        }
    }

    @Nested
    class BoundaryValueAnalysisTest {
        @Test
        void testKeyAtFirstPosition() {
            // Test case 1: Key is at the first position
            int[] v = {-10, 20, -30, -40, 50};
            int key = -10;
            int result = arraySearch.findIndex(v, key);
            assertEquals(0, result);
        }

        @Test
        void testKeyAtLastPosition() {
            // Test case 2: Key is at the last position
            int[] v = {-10, 20, -30, -40, 50};
            int key = 50;
            int result = arraySearch.findIndex(v, key);
            assertEquals(4, result);
        }

        @Test
        void testKeyNotPresent() {
            // Test case 3: Key is not present in the array
            int[] v = {-15, 20, 0, 12, -500};
            int key = 99;
            int result = arraySearch.findIndex(v, key);
            assertEquals(-1, result);
        }

        @Test
        void testMultipleKeysFirstAppearanceAtFirstPosition() {
            // Test case 4: Multiple keys are present in the array; first appearance at first position
            int[] v = {9, 9, 9, 9, 9};
            int key = 9;
            int result = arraySearch.findIndex(v, key);
            assertEquals(0, result);
        }

        @Test
        void testMultipleKeysFirstAppearanceNotAtFirstPosition() {
            // Test case 5: Multiple keys are present in the array; first appearance not at first position
            int[] v = {0, 5, 9, 9, 9};
            int key = 9;
            int result = arraySearch.findIndex(v, key);
            assertEquals(2, result);
        }

        @Test
        void testArrayIsNull() {
            // Test case 6: Array is null
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(null, key));
            assertEquals("Vectorul nu poate fi null.", exception.getMessage());
        }

        @Test
        void testArrayLengthSmallerThanFive() {
            // Test case 7: Array length is smaller than 5
            int[] v = {-10, 20, -30};
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(v, key));
            assertEquals("Vectorul trebuie sa aiba lungimea 5.", exception.getMessage());
        }

        @Test
        void testArrayLengthLargerThanFive() {
            // Test case 8: Array length is larger than 5
            int[] v = {-10, 20, -30, -40, 50, 60};
            int key = -30;
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> arraySearch.findIndex(v, key));
            assertEquals("Vectorul trebuie sa aiba lungimea 5.", exception.getMessage());
        }
    }

    @Nested
    class StatementCoverageTest {
        @Test
        public void statementCoverage_nullVector() {
            assertThrows(IllegalArgumentException.class, () -> arraySearch.findIndex(null, 30));
        }

        @Test
        public void statementCoverage_invalidLength() {
            int[] v = {10, 20, 30};
            assertThrows(IllegalArgumentException.class, () -> arraySearch.findIndex(v, 30));
        }

        @Test
        public void statementCoverage_keyFound() {
            int[] v = {-10, 20, -30, 5, 4};
            assertEquals(2, arraySearch.findIndex(v, -30));
        }

        @Test
        public void statementCoverage_keyNotFound() {
            int[] v = {-10, 20, -30, 5, 4};
            assertEquals(-1, arraySearch.findIndex(v, 9));
        }
    }
}
