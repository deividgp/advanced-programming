package data;

public abstract class DataFrame implements Iterable<String>{
    String[] columnLabels;
    int[] rows;
    String[] content;

    public DataFrame(String[] columnLabels, int[] rows, String[] content){
        this.columnLabels = columnLabels;
        this.rows = rows;
        this.content = content;
    }
}
