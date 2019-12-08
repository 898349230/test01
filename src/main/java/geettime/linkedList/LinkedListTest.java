package geettime.linkedList;

import java.util.Random;

/**
 * @classname: LinkedListTest
 * @description:
 * @author: sunxinbo
 * @time: 2019/12/7、14:34
 */
public class LinkedListTest {
    public static void main(String[] args) {
//        addOrRemove();
        reverse();
    }

    public static void reverse(){
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        list.print();
        System.out.println();
        list.reverse();
        list.print();
    }

    public static void addOrRemove(){
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        Random random = new Random();
        list.add(1000);
        list.add(2000);
        for(int i = 0; i < 10 ; i++){
            int ii = random.nextInt(100);
            list.add(ii);
        }
        list.print();
        System.out.println();
        list.add(3000);
        list.print();

        boolean remove = list.remove(2000);
        System.out.println("\nremove other：" + remove);
        list.print();

        boolean removeHead = list.remove(1000);
        System.out.println("\nremove head：" + removeHead);
        list.print();

        boolean removeTail = list.remove(3000);
        System.out.println("\nremove tail：" + removeTail);
        list.print();
    }
}
