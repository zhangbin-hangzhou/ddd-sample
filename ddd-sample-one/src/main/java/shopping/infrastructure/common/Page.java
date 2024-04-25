package shopping.infrastructure.common;

import java.io.Serializable;

public class Page implements Serializable {
    private Integer number;
    private Integer size;

    public static Page create(Integer number, Integer size) {
        Page page = new Page();
        page.setNumber(number);
        page.setSize(size);
        return page;
    }

    private Page() {
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return this.number <= 1 ? 0 : (this.number - 1) * this.size;
    }

    public String toString() {
        return "Page [number=" + this.number + ", size=" + this.size + "]";
    }
}
