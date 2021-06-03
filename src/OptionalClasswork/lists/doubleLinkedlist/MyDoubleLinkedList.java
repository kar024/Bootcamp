package OptionalClasswork.lists.doubleLinkedlist;

public class MyDoubleLinkedList<T> implements List {

    MyNode<T> first;
    MyNode<T> last;
    int size;

    private class MyNode<T> {
        private MyNode last;
        private MyNode next;
        private T data;

        public MyNode(T data) {
            this.data = data;
        }

        public void connectToNodes(MyNode previous, MyNode next) {
            this.last = previous;
            this.next = next;
        }

        public void connectToNodeForward(MyNode current) {
            this.next = current;
        }

        public void connectToNodePrevious(MyNode current) {
            this.last = current;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public MyNode getLast() {
            return last;
        }

        public void setLast(MyNode last) {
            this.last = last;
        }

        public MyNode getNext() {
            return next;
        }

        public void setNext(MyNode next) {
            this.next = next;
        }
    }

    public MyDoubleLinkedList() {

    }

    @Override
    public T get(int index) {
        if (indexInputCheck(index)) return null;
        if (index >= size) {
            index = index % size;
        }
        if (index <= size / 2) {
            return getFromStart(index);
        } else {
            return getFromEnd(index);
        }
    }

    private boolean indexInputCheck(int index) {
        try {
            if (index < 0) {
                throw new MyIndexOutOfBoundsException();
            }
        } catch (MyIndexOutOfBoundsException e) {
            e.printMessage();
            return true;
        }
        return false;
    }

    private T getFromStart(int index) {
        MyNode<T> current = first;
        for (int i = 0; i <= index; i++) {
            if (i != index) {
                current = current.getNext();
            }
        }
        return current.getData();
    }

    private T getFromEnd(int index) {
        MyNode<T> current = last;
        for (int i = size - 1; i >= index; i--) {
            if (i != index) {
                current = current.getLast();
            }
        }
        return current.getData();
    }

    @Override
    public void add(Object obj) {
        MyNode<T> newNode = new MyNode<T>((T) obj);
        if (ifNoFirstCreate(newNode)) return;
        if (ifNoLastCreate(newNode)) return;
        last.connectToNodeForward(newNode);
        newNode.connectToNodes(last, first);
        first.connectToNodePrevious(newNode);
        last = newNode;
        size++;
    }

    private boolean ifNoLastCreate(MyNode<T> newNode) {
        if (size == 1) {
            first.connectToNodeForward(newNode);
            newNode.connectToNodePrevious(first);
            last = newNode;
            size++;
            return true;
        }
        return false;
    }

    private boolean ifNoFirstCreate(MyNode<T> newNode) {
        if (size == 0) {
            newNode.connectToNodes(null, null);
            first = newNode;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public void remove(int index) {
        if (indexInputCheck(index)) return;
        if (index >= size) {
            index = index % size;
        }
        if (ifStartIndexRemove(index)) return;
        MyNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        if (ifLastRemoveCheck(index, current)) return;

        current.getLast().connectToNodeForward(current.getNext());
        current.getNext().setLast(current.getLast());
        size--;
    }

    private boolean ifLastRemoveCheck(int index, MyNode<T> current) {
        if (index == size - 1) {
            last = current;
            last.setNext(null);
            size--;
            return true;
        }
        return false;
    }

    private boolean ifStartIndexRemove(int index) {
        if (index == 0) {
            first = first.getNext();
            first.setLast(null);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public void remove(Object obj) {
        if (first.getData() == obj) {
            first = first.getNext();
            first.setLast(null);
            size--;
            return;
        }
        MyNode<T> current = first;
        for (int i = 0; i < size - 2; i++) {
            current = current.getNext();
            if (current.getData() == obj) {
                current.getLast().connectToNodeForward(current.getNext());
                current.getNext().setLast(current.getLast());
                size--;
                return;
            }
        }
        if (last.getData() == obj) {
            last = current;
            current.setNext(null);
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object obj) {
        if (first.getData().equals(obj)) {
            return true;
        }
        MyNode<T> current = first;
        for (int i = 0; i < size - 1; i++) {
            current = current.getNext();
            if (current.getData() == obj) {
                return true;
            }
        }
        if (last.getData() == obj) {
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        MyNode<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current == null) {
                break;
            }
            result.append(current.getData());
            if (i < size - 1) {
                result.append(", ");
            }
            current = current.getNext();
        }
        result.append("]");
        return result.toString();
    }
}