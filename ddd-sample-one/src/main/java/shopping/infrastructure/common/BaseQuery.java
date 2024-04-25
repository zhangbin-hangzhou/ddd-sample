package shopping.infrastructure.common;



import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class BaseQuery implements Serializable {
    private Long id;
    private Integer pageNum;
    private Integer pageSize;
    private Page page;
    private List<Sort> sorts = Arrays.asList(Sort.byIdDesc());

    public BaseQuery() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page getPage() {
        if(pageNum != null && pageSize != null){
            return Page.create(pageNum,pageSize);
        }
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Sort> getSorts() {
        return this.sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public void setSort(Sort sort) {
        this.sorts = Arrays.asList(sort);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
