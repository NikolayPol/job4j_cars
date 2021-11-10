package main.java.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Класс Advertisement - объявление о продаже машины.
 *
 * @author Nikolay Polegaev
 * @version 1.0 10.11.2021
 */
@Entity
@Table(name = "advertisement", schema = "sale", catalog = "job4j_cars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Timestamp created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", status=" + status
                + ", carId=" + car
                + ", authorId=" + author
                + ", created=" + created
                + '}';
    }
}
