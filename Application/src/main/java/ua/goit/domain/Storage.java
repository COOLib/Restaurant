package ua.goit.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "storage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Storage {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private int quantity;

    public Storage() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (quantity != storage.quantity) return false;
        return ingredient != null ? ingredient.equals(storage.ingredient) : storage.ingredient == null;

    }

    @Override
    public int hashCode() {
        int result = ingredient != null ? ingredient.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}