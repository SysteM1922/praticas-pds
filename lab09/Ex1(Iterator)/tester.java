import java.util.Iterator;
import java.util.ListIterator;

public class tester {
    public static void main(String[] args) {
        // criar um vetor generico
        VectorGeneric<Integer> vec = new VectorGeneric<>();

        // adicionar elementos ao vector
        for (int i = 0; i <= 20; i++) {
            vec.addElem(i);
        }

        // criar todos os iterators
        Iterator<Integer> iter = vec.Iterator();
        ListIterator<Integer> iter1 = vec.listIterator();
        ListIterator<Integer> iter2 = vec.listIterator(5);

        // percorrer vec com o iterator normal
        System.out.println("iterator normal");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // percorrer vec com o List iterator a começar no index 0
        System.out.println("\n List iterator ");
        while (iter1.hasNext()) {
            System.out.printf("Index %d--> %d\n", iter1.nextIndex() , iter1.next());
        }

        System.out.println("backwards");
        while (iter1.hasPrevious()) {
            System.out.printf("Index %d--> %d\n", iter1.previousIndex() + 1, iter1.previous());
        }

        // percorrer vec com o List iterator a começar no index 5
        System.out.println("\n List iterator index 5");
        while (iter2.hasNext()) {
            System.out.printf("Index %d--> %d\n", iter2.nextIndex(), iter2.next());
        }

        System.out.println("backwards");
        while (iter2.hasPrevious()) {
            System.out.printf("Index %d--> %d\n", iter2.previousIndex() + 1, iter2.previous());
        }

    }
}
