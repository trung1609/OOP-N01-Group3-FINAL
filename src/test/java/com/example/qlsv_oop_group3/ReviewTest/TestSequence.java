package com.example.qlsv_oop_group3.ReviewTest;
import com.example.qlsv_oop_group3.Review.Selector;
import com.example.qlsv_oop_group3.Review.Sequence;

public class TestSequence {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(i);

        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
