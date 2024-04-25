package shopping.infrastructure.common;

import cn.hutool.core.collection.CollUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageData<T> implements Serializable {
    private long pageNum;
    private long pageSize;
    private long total;
    private long totalPage;
    private List<T> data;

    public PageData() {
    }

    public static <T> PageData<T> newPageData(long totalCounts, Page page, List<T> data) {
        PageData<T> pageData = new PageData();
        pageData.setTotal(totalCounts);
        pageData.setPageNum(page.getNumber());
        pageData.setPageSize(page.getSize());
        pageData.setData(data);
        return pageData;
    }

    public static <T> PageData<T> newEmptyPageData(Page page) {
        return newPageData(0, page, (List)null);
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalPage() {
        if (this.total == 0) {
            return 0;
        } else {
            return this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
        }
    }

    public boolean isEmpty() {
        return CollUtil.isEmpty(this.getData());
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }


    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data == null ? Collections.emptyList() : data;
    }

    public void setData(List<T> data) {this.data = data;}
}
