package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Model - модель машины.
 *
 * @author Nikolay Polegaev
 * @version 1.0 10.11.2021
 */
@Entity
@Table(name = "model", schema = "sale", catalog = "job4j_cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Model {
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
        Model model = (Model) o;
        return id.equals(model.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
