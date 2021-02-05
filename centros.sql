-- Table: public.centros

-- DROP TABLE public.centros;

CREATE TABLE public.centros
(
    nombre character varying COLLATE pg_catalog."default",
    n_centro integer NOT NULL,
    latitud numeric,
    direccion character varying COLLATE pg_catalog."default",
    longitud numeric,
    CONSTRAINT centros_pkey PRIMARY KEY (n_centro)
)

TABLESPACE pg_default;

ALTER TABLE public.centros
    OWNER to postgres;