package datastructure;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
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

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

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

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index di luar batas: " + index);
        }
        Node<T> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedNode = current.next;
            current.next = removedNode.next;
        }
        size--;
        return removedNode.data;
    }

    /**
     * Skenario 2: Pencarian berdasarkan caseId dengan penghitungan langkah (traversal steps)
     * Menggunakan casting langsung ke model Putusan agar aman dari ClassCircularityError JDK.
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
                // Fallback umum jika tipe data bukan Putusan
                if (current.data.toString().contains(targetCaseId)) {
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean validateInvariant() {
        Node<T> current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count == size;
    }
}