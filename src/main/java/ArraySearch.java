public class ArraySearch {

    public int findIndex(int[] v, int key) {
        if (v == null) {
            throw new IllegalArgumentException("Vectorul nu poate fi null.");
        }
        if (v.length != 5) {
            throw new IllegalArgumentException("Vectorul trebuie sa aiba lungimea 5.");
        }

        for (int i = 0; i < v.length; i++) {
            if (v[i] == key) {
                return i + 1;
            }
        }
        return -1;
    }
}
