package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Brand - марка машины.
 *
 * @author Nikolay Polegaev
 * @version 1.0 10.11.2021
 */
@Entity
@Table(name = "brand", schema = "sale", catalog = "job4j_cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brand brand = (Brand) o;
        return id.equals(brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Brand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
