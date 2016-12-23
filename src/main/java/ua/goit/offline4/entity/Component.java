package ua.goit.offline4.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;


import static javax.persistence.GenerationType.IDENTITY;

/**
 * Component.
 *
 * @author Andrey Minov
 * @since 2016.12
 */
@Entity
@Table(name = "components", schema = "pizzeria")
public class Component {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private BigDecimal prize;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PizzaComponents> componentAmounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public List<PizzaComponents> getComponentAmounts() {
        return componentAmounts;
    }

    public void setComponentAmounts(List<PizzaComponents> componentAmounts) {
        this.componentAmounts = componentAmounts;
    }

    @Override
    public String toString() {
        return "Component{" + "id=" + id + ", name='" + name + '\'' + ", prize=" + prize + '}';
    }
}
