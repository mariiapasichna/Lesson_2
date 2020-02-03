package com.javaelementary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    /*
    1) Реализовать класс IntArrayList и интерфейс IntList по аналогии с List<Integer> (ArrayList)
    Перечень методов:
    boolean add(int element);
    boolean add(int index, int element);
    void clear();
    int get(int index);
    boolean isEmpty();
    boolean remove(int index);
    boolean removeByValue(int value);
    boolean set(int index, int element);
    int size();
    IntList subList(int fromIndex, int toIndex);
    int[] toArray();
    2) Про помощи своего класса IntList заполнить случайными числами и отсортировать.
    */
    public static final int CAPACITY_OF_LIST = 10000;
    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntList list1 = new IntArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
            list1.add(i);
        }
        System.out.println("ArrayList<>()");
        System.out.println(list.toString());
        list.add(0, 20);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list);
        System.out.println(list.isEmpty());
        System.out.println(list.remove(0));
        System.out.println(list);
        Integer value = 8;
        System.out.println(list.remove(value));
        System.out.println(list);
        System.out.println(list.set(0, 33));
        System.out.println(list);
        list.toArray();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.subList(2, 8));
        //list.clear();
        System.out.println(list);
        System.out.println(list.isEmpty());
        System.out.println("ArrayList<>() subList() ===================================");
        System.out.println(list.subList(2, 8));
        Integer x = 22;
        System.out.println(list.subList(2, 8).add(x));
        System.out.println(list);
        list.subList(2, 8).add(2, x);
        System.out.println(list);
        list.subList(2, 8).clear();
        System.out.println(list);
        System.out.println(list.subList(2, 8).get(0));
        System.out.println(list);
        System.out.println(list.subList(0, 0).isEmpty());
        System.out.println(list.subList(2, 8).remove(1));
        System.out.println(list);
        Integer value1 = 9;
        System.out.println(list.subList(2, 8).remove(value1));
        System.out.println(list);
        System.out.println(list.subList(2, 8).set(0, 44));
        System.out.println(list);
        System.out.println(list.subList(2, 8).size());
        System.out.println(list.subList(2, 8).subList(3, 4));
        System.out.println("IntArrayList() ==============================================");
        System.out.println(list1);
        list1.add(0, 20);
        System.out.println(list1);
        System.out.println(list1.get(0));
        System.out.println(list1);
        System.out.println(list1.isEmpty());
        System.out.println(list1.remove(0));
        System.out.println(list1);
        System.out.println(list1.removeByValue(8));
        System.out.println(list1);
        System.out.println(list1.set(0, 33));
        System.out.println(list1);
        list1.toArray();
        System.out.println(list1);
        System.out.println(list1.size());
        System.out.println(list1.subList(2, 8));
        //list1.clear();
        System.out.println(list1);
        System.out.println(list1.isEmpty());
        System.out.println("IntArrayList() subList() =======================================");
        System.out.println(list1.subList(2, 8));
        System.out.println(list1.subList(2, 8).add(22));
        System.out.println(list1);
        list1.subList(2, 8).add(2, 22);
        System.out.println(list1);
        list1.subList(2, 8).clear();
        System.out.println(list1);
        System.out.println(list1.subList(2, 8).get(0));
        System.out.println(list1);
        System.out.println(list1.subList(0, 0).isEmpty());
        System.out.println(list1.subList(2, 8).remove(1));
        System.out.println(list1);
        System.out.println(list1.subList(2, 8).removeByValue(9));
        System.out.println(list1);
        System.out.println(list1.subList(2, 8).set(0, 44));
        System.out.println(list1);
        System.out.println(list1.subList(2, 8).size());
        System.out.println(list1.subList(2, 8).subList(3, 4));

        IntList list2 = new IntArrayList();
        IntList list3 = new IntArrayList();
        initList(list2);
        System.out.println(list2);
        sortList(list2);
        System.out.println(list2);
        countGrowList(list3);
        //System.out.println("The number of buffer grow operations for " + CAPACITY_OF_LIST + " elements: " + IntArrayList.count);
        //count: 17
    }

    private static void sortList(IntList list2) {
        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list2.size() - i - 1; j++) {
                if (list2.get(j) > list2.get(j + 1)) {
                    int tmp = list2.get(j);
                    list2.set(j, list2.get(j + 1));
                    list2.set(j + 1, tmp);
                }
            }
        }
    }

    private static void initList(IntList list2) {
        for (int i = 0; i < 10; i++) {
            list2.add(RANDOM.nextInt(100));
        }
    }

    private static void countGrowList(IntList list3) {
        for (int i = 0; i < CAPACITY_OF_LIST; i++) {
            list3.add(RANDOM.nextInt(100));
        }
    }
}