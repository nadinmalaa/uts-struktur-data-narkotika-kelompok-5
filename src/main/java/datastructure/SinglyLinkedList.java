package datastructure;

public class SinglyLinkedList<T> {

    // Node inner class untuk menampung data dan pointer berikutnya
    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Kelas pembantu untuk menampung hasil pencarian beserta jumlah langkah (traversal steps)
     */
    public static class SearchResult<T> {
        private final T data;
        private final int steps;
        private final boolean found;

        public SearchResult(T data, int steps, boolean found) {
            this.data = data;
            this.steps = steps;
            this.found = found;
        }

        public T getData() {
            return data;
        }

        public int getSteps() {
            return steps;
        }

        public boolean isFound() {
            return found;
        }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // 1. Menambahkan elemen baru di akhir list
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

    // 2. Mendapatkan ukuran list
    public int size() {
        return size;
    }

    // 3. Mengecek apakah list kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // 4. Mengambil data berdasarkan indeks
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // 5. Menghapus elemen berdasarkan indeks
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<T> prev = null;
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            removedData = current.data;
            prev.next = current.next;
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

    /**
     * Skenario 2: Pencarian berdasarkan caseId dengan penghitungan langkah (traversal steps)
     */
    public SearchResult<T> searchByCaseId(String targetCaseId) {
        Node<T> current = head;
        int steps = 0;

        while (current != null) {
            steps++;
            if (current.data instanceof model.Putusan) {
                model.Putusan p = (model.Putusan) current.data;
                if (p.getCaseId() != null && p.getCaseId().equalsIgnoreCase(targetCaseId)) {
                    return new SearchResult<>(current.data, steps, true);
                }
            } else {
                if (current.data != null && current.data.toString().contains(targetCaseId)) {
                    return new SearchResult<>(current.data, steps, true);
                }
            }
            current = current.next;
        }
        return new SearchResult<>(null, steps, false);
    }

    /**
     * Skenario 5: Filter Gabungan berdasarkan jenis narkotika dan minimum vonis bulan
     */
    public SinglyLinkedList<T> filterByKriteria(String targetJenisNarkotika, int minVonisBulan) {
        SinglyLinkedList<T> filteredList = new SinglyLinkedList<>();
        Node<T> current = head;

        while (current != null) {
            if (current.data instanceof model.Putusan) {
                model.Putusan p = (model.Putusan) current.data;
                boolean matchJenis = (targetJenisNarkotika == null || targetJenisNarkotika.isEmpty() ||
                        (p.getJenisNarkotika() != null && p.getJenisNarkotika().equalsIgnoreCase(targetJenisNarkotika)));
                boolean matchVonis = (p.getVonisBulan() >= minVonisBulan);

                if (matchJenis && matchVonis) {
                    filteredList.add(current.data);
                }
            }
            current = current.next;
        }
        return filteredList;
    }
}