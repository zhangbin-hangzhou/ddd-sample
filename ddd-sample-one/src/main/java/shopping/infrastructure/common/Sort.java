package shopping.infrastructure.common;

import java.io.Serializable;

public class Sort implements Serializable {
    private String columnName;
    private String sortType;

    public static Sort create() {
        return new Sort();
    }

    public static Sort create(String columnName, SortType sortType) {
        return new Sort(columnName, sortType);
    }

    /** @deprecated */
    @Deprecated
    public static Sort byCreateTimeDesc() {
        return create("create_time", SortType.DESC);
    }

    /** @deprecated */
    @Deprecated
    public static Sort byCreateTimeAsc() {
        return create("create_time", SortType.ASC);
    }

    public static Sort byIdDesc() {
        return create("id", SortType.DESC);
    }

    public static Sort byIdAsc() {
        return create("id", SortType.ASC);
    }

    public Sort() {
        this("create_time", SortType.DESC);
    }

    public Sort(String columnName, SortType sortType) {
        this.columnName = columnName;
        this.sortType = sortType.name();
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortType() {
        return this.sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType.name();
    }

    public String toString() {
        return "Sort [columnName=" + this.columnName + ", sortType=" + this.sortType + "]";
    }

    public static enum SortType {
        DESC,
        ASC;

        private SortType() {
        }
    }
}
