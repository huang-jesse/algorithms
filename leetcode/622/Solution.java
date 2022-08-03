class MyCircularQueue {
    private int[] circularArr;
    private int head = -1;
    private int tail = -1;
    private int cap;
    public MyCircularQueue(int k) {
        this.circularArr = new int[k];
        this.cap = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
            tail = 0;
        } else {
            tail = (tail + 1) % cap;
        }
        circularArr[tail] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % cap;
        }
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return circularArr[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return circularArr[tail];
    }

    public boolean isEmpty() {
        return this.head == -1;
    }

    public boolean isFull() {
        return ((tail + 1) % cap) == head;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1));// 返回 true
        System.out.println(circularQueue.enQueue(2));// 返回 true
        System.out.println(circularQueue.enQueue(3));// 返回 true
        System.out.println(circularQueue.enQueue(4));/// 返回 false，队列已满
        System.out.println(circularQueue.Rear());// 返回 3
        System.out.println(circularQueue.isFull());// 返回 true
        System.out.println(circularQueue.deQueue());// 返回 true
        System.out.println(circularQueue.enQueue(4));// 返回 true
        System.out.println(circularQueue.Rear());// 返回 4
    }
}