import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {

    private final int numOfChildren;
    private final List<T> heap;

    public PowerOfTwoMaxHeap(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        heap = new ArrayList<>();
    }

    public void insert(T value) {
        heap.add(value);
        int currentIndex = heap.size() - 1;
        int parentIndex = (currentIndex - 1) / numOfChildren;

        while (currentIndex > 0 && heap.get(currentIndex).compareTo(heap.get(parentIndex)) > 0) {
            T temp = heap.get(currentIndex);
            heap.set(currentIndex, heap.get(parentIndex));
            heap.set(parentIndex, temp);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / numOfChildren;
        }
    }

    public T popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        T max = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIndex = 0;

        while (true) {
            int maxIndex = getMaximumChildIndex(currentIndex);
            if (maxIndex == -1) {
                break;
            }
            if (heap.get(currentIndex).compareTo(heap.get(maxIndex)) < 0) {
                T temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(maxIndex));
                heap.set(maxIndex, temp);
                currentIndex = maxIndex;
            } else {
                break;
            }
        }

        return max;
    }

    private int getMaximumChildIndex(int parentIndex) {
        int maxIndex = -1;
        int startChildIndex = parentIndex * numOfChildren + 1;
        int endChildIndex = startChildIndex + numOfChildren - 1;

        for (int i = startChildIndex; i <= endChildIndex && i < heap.size(); i++) {
            if (maxIndex == -1 || heap.get(i).compareTo(heap.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static void main(String[] args){
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(10);
        heap.popMax();
    }
}