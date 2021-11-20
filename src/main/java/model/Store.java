package model;

import java.util.List;

/**
 * Интерфейс Store содержит методы для выборки из БД.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public interface Store {

    List<Advertisement> showLastDay();

    List<Advertisement> showWithPhoto();

    List<Advertisement> showWithBrand(Brand brand);

}
