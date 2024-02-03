public class LinkedListDeque<T> {
    private class Linknode<T> {
        public T item;
        public Linknode first;
        public Linknode next;

        public Linknode(T x, Linknode f, Linknode r) {
            item = x;
            first = f;
            next = r;
        }

        private T getRe(int index, Linknode tmp) {
            if(index == 0) return (T) tmp.item;
            else return (T) getRe(index-1, tmp.next);
        }
    }

    private Linknode<T> front;
    private int size;

    public LinkedListDeque() {
        front = new Linknode<T>(null, null, null);
        front.first = front;
        front.next = front;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Linknode<T> tmp = new Linknode<T>(item, front, front.next);
        front.next.first = tmp;
        front.next = tmp;
        size += 1;
    }
    public void addLast(T item) {
        front.first.next = new Linknode<T>(item, front.first, front);
        front.first = front.first.next;
        size += 1;
    }

    public int size() {
        return size;
    }

    public  void printDeque() {
        Linknode<T> p = front.next;
        while (p!=front) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
    }

    public T removeFirst(){
        if(isEmpty()) return null;
        Linknode<T> tmp = front.next;
        front.next.next.first = front;
        front.next = front.next.next;
        size--;
        return tmp.item;

    }

    public T removeLast(){
        if(isEmpty()) return null;
        T tmp = (T) front.first.item;
        front.first.first.next = front;
        front.first = front.first.first;
        size--;

        return tmp;
        
    }

    public T get(int index) {
        if(index < 0 || index >= size) return null;
        Linknode<T> tmp = front;
        for(int i = 0;i <= index;i++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }
    
    

    public T getRecursive(int index) {
        return front.getRe(index, front.next);
    }

}