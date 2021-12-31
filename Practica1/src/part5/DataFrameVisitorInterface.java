package part5;

import part5.DataFrameVisitor;

public interface DataFrameVisitorInterface<T> {
    double maximum (DataFrameVisitor<T> visitor);
    double minimum (DataFrameVisitor<T> visitor);
    double average (DataFrameVisitor<T> visitor);
    double sum (DataFrameVisitor<T> visitor);
}
