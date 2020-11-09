package sample;


class PriorityQueue {
    private Data head;
    private long size;

    public long getSize() {
        return size;
    }

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    public void insert(Data data) {

        if (head == null) {

            head = data;
        } else {
            Data curr = head;

            if (data.getInfo() >= head.getInfo()){
                data.next = head;
                head = data;
            } else {
                Data prev = curr;

                while (curr != null && data.getInfo() < curr.getInfo()) {
                    prev = curr;
                    curr = curr.next;
                }

                data.next = curr;
                prev.next = data;
            }
        }

        size++;
    }

    public Data[] remove(long toBeRemoved){

        Data[] toReturn = null;
        if (size < toBeRemoved){
            toReturn = new Data[(int)size];
        } else {
            toReturn = new Data[(int)toBeRemoved];
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
