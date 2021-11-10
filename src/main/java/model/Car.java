package main.java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

/**
 * Класс Car - продаваемая машина.
 *
 * @author Nikolay Polegaev
 * @version 1.0 10.11.2021
 */
@Entity
@Table(name = "car", schema = "sale", catalog = "job4j_cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private String color;

    private Time productionDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id.equals(car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", brandId=" + brand
                + ", modelId=" + model
                + ", color='" + color + '\''
                + ", productionDate=" + productionDate
                + '}';
    }
}
