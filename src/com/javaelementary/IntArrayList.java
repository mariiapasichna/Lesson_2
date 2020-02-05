package com.javaelementary;

import java.util.Arrays;

public class IntArrayList implements IntList {
    private int size = 0;
    private int[] elementData = new int[10];
    public static int count = 0;

    /**
     * Appends the specified element to the end of this list.
     * @param element - element to be appended to this list
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean add(int element) {
        ensureCapacity(size);
        elementData[size] = element;
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index   - index at which the specified element is to be inserted
     * @param element - element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, int element) {
        checkIndex(index);
        ensureCapacity(size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        size = 0;
        elementData = new int[10];
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index - the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public int remove(int index) {
        checkIndex(index);
        int removeElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removeElement;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param value - element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean removeByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index   - index of the element to replace
     * @param element - element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    @Override
    public int set(int index, int element) {
        checkIndex(index);
        int oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex - low endpoint (inclusive) of the subList
     * @param toIndex   - high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException
     */
    @Override
    public IntList subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, fromIndex, toIndex);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * @return an array containing all of the elements in this list in proper sequence
     */
    @Override
    public int[] toArray() {
        int[] result = new int[size];
        System.arraycopy(elementData, 0, result, 0, size);
        return result;
    }

    /**
     * Returns a string representation of this collection.
     * @return a string representation of this collection
     */
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private void ensureCapacity(int size) {
        if (elementData.length <= size + 1) {
            grow();
        }
    }

    private int[] grow() {
        int[] tmpArray = new int[elementData.length * 3 / 2 + 1];
        System.arraycopy(elementData, 0, tmpArray, 0, size);
        elementData = tmpArray;
        count++;
        return elementData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of range");
        }
    }

    private static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    private static class SubList implements IntList {
        private final IntArrayList intArrayList;
        private final SubList subList;
        private final int offset;
        private int fromIndex;
        private int size;

        public SubList(IntArrayList intArrayList, int fromIndex, int toIndex) {
            this.intArrayList = intArrayList;
            this.subList = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        public SubList(SubList subList, int fromIndex, int toIndex) {
            this.intArrayList = subList.intArrayList;
            this.subList = subList;
            this.offset = subList.offset + fromIndex;
            this.size = toIndex - fromIndex;
        }

        @Override
        public boolean add(int element) {
            intArrayList.add(offset + size, element);
            return true;
        }

        @Override
        public void add(int index, int element) {
            checkSubListIndex(index);
            intArrayList.add(offset + index, element);
        }

        @Override
        public void clear() {
            int newSize = intArrayList.size - this.size;
            System.arraycopy(intArrayList.elementData, offset + this.size, intArrayList.elementData, offset, intArrayList.size - (offset + this.size));
            intArrayList.size = newSize;
            this.size = 0;
        }

        @Override
        public int get(int index) {
            checkSubListIndex(index);
            return intArrayList.elementData[offset + index];
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int remove(int index) {
            checkSubListIndex(index);
            return intArrayList.remove(offset + index);
        }

        @Override
        public boolean removeByValue(int value) {
            for (int i = 0; i < this.size; i++) {
                if (intArrayList.elementData[i + offset] == value) {
                    System.arraycopy(intArrayList.elementData, offset + i + 1, intArrayList.elementData, offset + i, intArrayList.size - (offset + i));
                    intArrayList.size--;
                    this.size--;
                    return true;
                }
            }
            return false;
        }

        @Override
        public int set(int index, int element) {
            checkSubListIndex(index);
            int oldValue = intArrayList.elementData[index + offset];
            intArrayList.elementData[index + offset] = element;
            return oldValue;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public IntList subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, fromIndex, toIndex);
        }

        @Override
        public int[] toArray() {
            int[] result = new int[size];
            System.arraycopy(intArrayList.elementData, offset, result, 0, size);
            return result;
        }

        @Override
        public String toString() {
            return Arrays.toString(toArray());
        }

        private void checkSubListIndex(int index) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
            }
        }
    }
}