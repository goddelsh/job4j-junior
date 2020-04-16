package prices.models;

import java.util.Date;

public class Price {

    private Long id;
    private String productCode;
    private int number;
    private int depart;
    private Date begin;
    private Date end;
    private Long value;


    public Price(Long id, String productCode, int number, int depart, Date begin, Date end, Long value) {
        this.id = id;
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (number != price.number) return false;
        if (depart != price.depart) return false;
        return productCode.equals(price.productCode);
    }

    @Override
    public int hashCode() {
        int result = productCode.hashCode();
        result = 31 * result + number;
        result = 31 * result + depart;
        return result;
    }
}
