public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = last = 0;
    }

    //copy items to a.
    private void copy(T[] a) {
        if (first < last) {
            System.arraycopy(items, first, a, 0, size);
        }
        else {
            System.arraycopy(items, first, a, 0, items.length - first);
            System.arraycopy(items, 0, a, 0, last + 1);
        }
        first = 0;
        last = first + size;
    }
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        copy(a);
        items = a;
    }


    private boolean needReduce() {
        boolean a = (double) size / items.length < 0.3;
        boolean b = size == 0;
        boolean c = (size >= 16);
        return a && b && c;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (first == 0) {
            first = items.length;
        }

        first--;
        if (size == 0) {
            last = first;
        }
        items[first] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (last == items.length -1) {
            last = 0;
        }
        last++;
        if (size == 0) {
            last = first;
        }
        items[last] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int pionter = first;
        for (int i = 0; i < size; i++) {
            System.out.print(items[pionter]);
            System.out.print(' ');
            if (pionter == items.length - 1) pionter = 0;
            pionter++;
        }
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        T tmp = items[first];
        items[first] = null;
        first = first == items.length - 1 ? 0 : first + 1;
        size--;
        if (needReduce()) resize(items.length / 3);
        if (size == 0) {
            last = first;
        }
        return tmp;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T tmp = items[last];
        items[last] = null;
        last = last == 0 ? items.length - 1 : last - 1;
        size--;
        if (needReduce()) resize(items.length / 3);
        if (size == 0) {
            first = last;
        }
        return tmp;
    }

    public T get(int index) {
        if (index >= size  || index < 0) return null;
        if (last < first) return items[(first + index) % items.length];
        else return items[first + index];
    }
    public static  void main(String[] args) {
        ArrayDeque<Integer> hi = new ArrayDeque<>();
        hi.addFirst(0);
        hi.removeLast();
        hi.addFirst(2);
        hi.addFirst(3);
        hi.removeLast();
        hi.isEmpty();
        System.out.println(hi.get(1));

    }
}
