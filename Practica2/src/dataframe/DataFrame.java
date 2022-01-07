package dataframe;

import part2.Visitor;

import java.util.*;
import java.util.function.Predicate;

public interface DataFrame {
    List<Object> at(int row, String column);
    List<Integer> columns();
    List<Integer> size();
    void accept(Visitor v);
}
