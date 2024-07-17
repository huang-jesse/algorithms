class MyCircularDeque {
    private int[] arr;
    private int maxSize;
    private int front = -1;
    private int last = -1;
    public MyCircularDeque(int k) {
        this.arr = new int[k];
        this.maxSize = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = 0;
            last = 0;
        } else {
            front = (front + 1) % maxSize;
        }
        arr[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = 0;
            last = 0;
        } else {
            last = (last - 1 + maxSize) % maxSize;
        }
        arr[last] = value;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (front == last) {
            // last one
            front = -1;
            last = -1;
        } else {
            front = (front - 1 + maxSize) % maxSize;
        }
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (front == last) {
            // last one
            front = -1;
            last = -1;
        } else {
            last = (last + 1) % maxSize;
        }
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : arr[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : arr[last];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (front + 1) % maxSize == last;
    }

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
        System.out.println(circularDeque.insertLast(1));			        // 返回 true
        System.out.println(circularDeque.insertLast(2));			        // 返回 true
        System.out.println(circularDeque.insertFront(3));			    // 返回 true
        System.out.println(circularDeque.insertFront(4));			    // 已经满了，返回 false
        System.out.println(circularDeque.getRear());  				    // 返回 2
        System.out.println(circularDeque.isFull());				        // 返回 true
        System.out.println(circularDeque.deleteLast());			        // 返回 true
        System.out.println(circularDeque.insertFront(4));		// 返回 true
        System.out.println(circularDeque.getFront());				    // 返回 4
    }
}