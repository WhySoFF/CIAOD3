package sample;

class CombinedQueue {

    private PriorityQueue PQ;

    private WeightedQueue[] WQ;


    private final long dataFlow = 100;


    private final long pqCapacity = 40;

    private final long wqCapacity = 100 - pqCapacity;

    private final long wqSize = 4;

    //Конструктор
    public CombinedQueue(){
        WQ = new WeightedQueue[(int)wqSize];
        WQ[0] = new WeightedQueue(10);
        WQ[1] = new WeightedQueue(20);
        WQ[2] = new WeightedQueue(30);
        WQ[3] = new WeightedQueue(40);

        PQ = new PriorityQueue();
    }

    public void insert(Data data){
        int type = (int) data.getType();

        switch (type) {
            case 0:
                WQ[0].pushBack(data);
                break;
            case 1:
                WQ[1].pushBack(data);
                break;
            case 2:
                WQ[2].pushBack(data);
                break;
            case 3:
                WQ[3].pushBack(data);
                break;
            case 4:
                PQ.insert(data);
                break;
            default:
                System.out.println("Wrong data type! Package " + data.getInfo() + " ignored!");
        }
    }

    public void process(){
        long fullCapacity = Math.round(dataFlow * wqCapacity / 100.0);
        Data[] fromWQ0 = WQ[0].remove(fullCapacity);
        Data[] fromWQ1 = WQ[1].remove(fullCapacity);
        Data[] fromWQ2 = WQ[2].remove(fullCapacity);
        Data[] fromWQ3 = WQ[3].remove(fullCapacity);


        Data[] fromPQ  = PQ.remove(Math.round(dataFlow * pqCapacity / 100.0));

        printData("WQ0", fromWQ0);
        printData("WQ1", fromWQ1);
        printData("WQ2", fromWQ2);
        printData("WQ3", fromWQ3);
        printData("PQ", fromPQ);
    }

    private void printData(String arrType, Data[] arr){
        if (arr.length == 0){
            System.out.println("Nothing was extracted from " + arrType + "!");
        } else {
            System.out.print("Extracted from " + arrType + ": ");
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i].getInfo() + " ");
            System.out.println();
        }
    }

    public Data find(long info){
        Data res;
        for (int i = 0; i < 4; i++) {
            res = WQ[i].find(info);
            if (res != null) return res;
        }
        res = PQ.find(info);
        return res;
    }

    public boolean isEmpty(){
        return WQ[0].getSize() == 0 &&
               WQ[1].getSize() == 0 &&
               WQ[2].getSize() == 0 &&
               WQ[3].getSize() == 0 &&
               PQ.getSize() == 0;
    }
}
