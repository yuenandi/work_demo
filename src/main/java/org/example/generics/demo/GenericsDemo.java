package org.example.generics.demo;

public class  GenericsDemo<U, V> {
    U first;
    V second;

    public GenericsDemo(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public static <T> int indexOf(T[] arr, T elm){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elm){
                return i;
            }
        }
        return -1;
    }

    public static <U, V> GenericsDemo<U, V> makeGenerics(U first, V second){
        GenericsDemo<U, V> g = new GenericsDemo<>(first, second);
        return g;
    }

    public static void main(String[] args) {
        System.out.println(indexOf(new Integer[]{1,2,3,4,5}, 10));

        System.out.println(indexOf(new String[]{"ynd", "ynd1", "ynd2"}, "ynd"));

        GenericsDemo<String, String> g = makeGenerics("Yue", "Nandy");
        System.out.println(g.getFirst() + g.getSecond());
    }
}
