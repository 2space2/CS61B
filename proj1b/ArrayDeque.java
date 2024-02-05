public class  ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int size;
    private int first;
    private int last;
    private int sizeCheckRange = 16;
    private  double rate = 0.3;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    //copy items to a.
    private void copy(Item[] a) {
        if (first < last) {
            System.arraycopy(items, first, a, 0, size);
        } else {
            System.arraycopy(items, first, a, 0, items.length - first);
            System.arraycopy(items, 0, a, items.length - first, last + 1);
        }
        first = 0;
        last = first + size - 1;
    }
    private void resize(int cap) {
        Item[] a = (Item[])  new Object[cap];
        copy(a);
        items = a;
    }


    private boolean needReduce() {
        boolean a = (double) size / items.length < rate;
        boolean b = size != 0;
        boolean c = (items.length >= sizeCheckRange);
        return a && b && c;
    }

    @Override
    public void addFirst(Item item) {
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

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (last == items.length - 1) {
            last = -1;
        }
        last++;
        if (size == 0) {
            last = first;
        }
        items[last] = item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int pionter = first;
        for (int i = 0; i < size; i++) {
            System.out.print(items[pionter]);
            System.out.print(' ');
            if (pionter == items.length - 1) {
                pionter = 0;
            }
            pionter++;
        }
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item tmp = items[first];
        items[first] = null;
        first = first == items.length - 1 ? 0 : first + 1;
        size--;
        if (needReduce()) {
            resize(items.length / 3);
        }
        return tmp;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item tmp = items[last];
        items[last] = null;
        last = last == 0 ? items.length - 1 : last - 1;
        size--;
        if (needReduce()) {
            resize(items.length / 3);
        }
        return tmp;
    }

    @Override
    public Item get(int index) {
        if (index >= size  || index < 0) {
            return null;
        }
        if (last < first) {
            return items[(first + index) % items.length];
        } else {
            return items[first + index];
        }
    }


}
