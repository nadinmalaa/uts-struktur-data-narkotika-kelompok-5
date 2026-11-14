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
        int countedSize = 0;
        Node<T> current = head;
        while (current != null) {
            countedSize++;
            current = current.next;
        }
        return countedSize == size;
    }

    // Inner class untuk hasil pencarian
    public static class SearchResult<T> {
        private boolean found;
        private T data;
        private int steps;

        public SearchResult(boolean found, T data, int steps) {
            this.found = found;
            this.data = data;
            this.steps = steps;
        }

        public boolean isFound() { return found; }
        public T getData() { return data; }
        public int steps() { return steps; }
        public int getSteps() { return steps; }
    }

    // 7. Pencarian berdasarkan Case ID (opsional penunjang test)
    public SearchResult<T> searchByCaseId(String targetCaseId) {
        int steps = 0;
        Node<T> current = head;
        while (current != null) {
            steps++;
            // Menggunakan toString atau refleksi sederhana jika objek memiliki getCaseId
            try {
                java.lang.reflect.Method method = current.data.getClass().getMethod("getCaseId");
                String id = (String) method.invoke(current.data);
                if (id != null && id.equals(targetCaseId)) {
                    return new SearchResult<>(true, current.data, steps);
                }
            } catch (Exception e) {
                // Ignore jika method tidak ditemukan
            }
            current = current.next;
        }
        return new SearchResult<>(false, null, steps);
    }

    // 8. Filter dummy penunjang test
    public SinglyLinkedList<T> filterByKriteria(String keyword, int val) {
        SinglyLinkedList<T> filtered = new SinglyLinkedList<>();
        Node<T> current = head;
        while (current != null) {
            filtered.add(current.data);
            current = current.next;
        }
        return filtered;
    }
}