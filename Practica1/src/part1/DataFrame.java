package part1;

import java.util.Comparator;

public abstract class DataFrame implements Iterable<String> {
    String[] columnLabels;
    int[] rows;
    String[] content;

    public DataFrame(String[] columnLabels, int[] rows, String[] content){
        this.columnLabels = columnLabels;
        this.rows = rows;
        this.content = content;
    }

    protected DataFrame() {
    }

    public String at(int row, String column){
        return null;
    }

    public String iat(int row, String column){
        return null;
    }

    public int columns(){
        return columnLabels.length-1;
    }

    public int size(){
        return rows.length-1;
    }

    public String sort(String column, Comparator<DataFrame> comparator){
        return null;
    }
}
