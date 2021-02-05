-- Table: public.nominas

-- DROP TABLE public.nominas;

CREATE TABLE public.nominas
(
    n_nomina integer NOT NULL,
    id_empleado integer,
    url character varying COLLATE pg_catalog."default",
    fecha date,
    CONSTRAINT nominas_pkey PRIMARY KEY (n_nomina),
    CONSTRAINT nominas_id_empleado_fkey FOREIGN KEY (id_empleado)
        REFERENCES public.empleados (id_empleado) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.nominas
    OWNER to postgres;