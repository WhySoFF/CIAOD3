package sample;

class WeightedQueue {
    private Data head;
    private Data last;
    private long size;
    private final long capacity;

    public long getSize() {
        return size;
    }

    public WeightedQueue(long capacity) {
        head = null;
        last = null;
        size = 0;
        this.capacity = capacity;
    }

    public void pushBack(Data data) {
        if (head == null) {
            head = data;
        } else {
            last.next = data;
        }
        last = data;
        size++;
    }

    public Data[] remove(long fullCapacity){
        Data[] toReturn = null;
        long toRemove = fullCapacity * capacity / 100;

            if (size < toRemove){
                toReturn = new Data[(int)size];
            } else {
                toReturn = new Data[(int)toRemove];
            }
            int ind = 0;
            while (ind != toReturn.length){
                Data temp = head;
                head = head.next;
                temp.next = null;
                toReturn[ind] = temp;
                ind++;
            }
        size -= toReturn.length;
        return toReturn;
    }

    public Data find(long info){
        Data curr = head;
        while (curr != null) {
            if (curr.getInfo() == info) break;
            curr = curr.next;
        }
        return curr;
    }
}
