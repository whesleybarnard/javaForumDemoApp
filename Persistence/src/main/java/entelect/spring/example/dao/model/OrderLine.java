package entelect.spring.example.dao.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_LINE")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = -8084296090981325932L;

    @Id
    @SequenceGenerator(name = "SEQ", sequenceName = "ENTELECT.GENERIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ORDER_ID")
    private CustomerOrder customerOrder;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", customerOrder=" + customerOrder +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
