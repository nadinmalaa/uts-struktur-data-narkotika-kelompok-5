package datastructure;

public class SinglyLinkedList<T> {

    // Node untuk menyimpan data dan pointer ke node berikutnya
    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Menambahkan elemen di akhir list
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    // Mengambil jumlah elemen
    public int size() {
        return size;
    }

    // Memeriksa apakah list kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Mengambil data berdasarkan indeks
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    // Menghapus elemen berdasarkan indeks
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index di luar batas: " + index
            );
        }

        Node<T> removedNode;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> prev = null;
            Node<T> current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removedNode = current.next;
            current.next = removedNode.next;
        }

        size--;
        return removedData;
    }

    // 6. Validasi invariant struktur data (memastikan size sesuai dengan jumlah node fisik)
    public boolean validateInvariant() {
        Node<T> current = head;
        int countedSize = 0;
        while (current != null) {
            countedSize++;
            current = current.next;
        }
        return countedSize == size;
    }

    // Memeriksa kesesuaian size dengan jumlah node
    public boolean validateInvariant() {
        int count = 0;
        Node<T> current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count == size;
    }

    // Menampung hasil pencarian dan jumlah langkah
    public static class SearchResult<T> {
        private final boolean found;
        private final T data;
        private final int steps;

        public SearchResult(boolean found, T data, int steps) {
            this.found = found;
            this.data = data;
            this.steps = steps;
        }

        // Constructor alternatif untuk kompatibilitas
        public SearchResult(T data, int steps, boolean found) {
            this(found, data, steps);
        }

        public boolean isFound() {
            return found;
        }

        public T getData() {
            return data;
        }

        public int steps() {
            return steps;
        }

        public int getSteps() {
            return steps;
        }
    }

    // Pencarian berdasarkan caseId
    public SearchResult<T> searchByCaseId(String targetCaseId) {
        int steps = 0;
        Node<T> current = head;

        while (current != null) {
            steps++;

            if (current.data instanceof model.Putusan) {
                model.Putusan putusan = (model.Putusan) current.data;
                String id = putusan.getCaseId();

                if (id != null &&
                        targetCaseId != null &&
                        id.equalsIgnoreCase(targetCaseId)) {
                    return new SearchResult<>(true, current.data, steps);
                }
            } else if (current.data != null) {
                // Pencarian alternatif untuk tipe data lainnya
                try {
                    java.lang.reflect.Method method =
                            current.data.getClass().getMethod("getCaseId");

                    Object result = method.invoke(current.data);

                    if (result != null &&
                            targetCaseId != null &&
                            result.toString().equalsIgnoreCase(targetCaseId)) {
                        return new SearchResult<>(
                                true, current.data, steps
                        );
                    }
                } catch (Exception e) {
                    // Method getCaseId tidak tersedia
                }
            }

            current = current.next;
        }

        return new SearchResult<>(false, null, steps);
    }

    // Filter berdasarkan jenis narkotika dan minimum vonis
    public SinglyLinkedList<T> filterByKriteria(
            String targetJenisNarkotika,
            int minVonisBulan) {

        SinglyLinkedList<T> filteredList =
                new SinglyLinkedList<>();

        Node<T> current = head;

        while (current != null) {
            if (current.data instanceof model.Putusan) {
                model.Putusan putusan = (model.Putusan) current.data;

                boolean matchJenis =
                        targetJenisNarkotika == null ||
                                targetJenisNarkotika.isEmpty() ||
                                (putusan.getJenisNarkotika() != null &&
                                        putusan.getJenisNarkotika()
                                                .equalsIgnoreCase(targetJenisNarkotika));

                boolean matchVonis =
                        putusan.getVonisBulan() >= minVonisBulan;

                if (matchJenis && matchVonis) {
                    filteredList.add(current.data);
                }
            }

            current = current.next;
        }

        return filteredList;
    }
}
