CREATE TABLE sale.advertisement
(
    id bigint NOT NULL,
    description text COLLATE pg_catalog."default",
    status boolean,
    car_id bigint,
    author_id bigint,
    created timestamp without time zone,
    CONSTRAINT advertisement_pkey PRIMARY KEY (id),
    CONSTRAINT author_id FOREIGN KEY (author_id)
        REFERENCES sale.author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT car_id FOREIGN KEY (car_id)
        REFERENCES sale.car (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE sale.author
(
    id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    phone text COLLATE pg_catalog."default",
    CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE sale.brand
(
    id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT brand_pkey PRIMARY KEY (id)
);

CREATE TABLE sale.car
(
    brand_id bigint,
    color text COLLATE pg_catalog."default",
    id bigint NOT NULL,
    model_id bigint,
    production_date time without time zone,
    CONSTRAINT car_pkey PRIMARY KEY (id),
    CONSTRAINT brand_fkey FOREIGN KEY (brand_id)
        REFERENCES sale.brand (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT model_fkey FOREIGN KEY (model_id)
        REFERENCES sale.model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE sale.model
(
    id bigint NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT model_pkey PRIMARY KEY (id)
);

