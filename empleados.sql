-- Table: public.empleados

-- DROP TABLE public.empleados;

CREATE TABLE public.empleados
(
    id_empleado integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nombre character varying COLLATE pg_catalog."default",
    apellidos character varying COLLATE pg_catalog."default",
    direccion character varying COLLATE pg_catalog."default",
    telefono character varying COLLATE pg_catalog."default",
    n_departamento integer,
    n_centro integer,
    url_storage character varying COLLATE pg_catalog."default",
    CONSTRAINT empleados_pkey PRIMARY KEY (id_empleado),
    CONSTRAINT empleados_n_centro_fkey FOREIGN KEY (n_centro)
        REFERENCES public.centros (n_centro) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT empleados_n_departamento_fkey FOREIGN KEY (n_departamento)
        REFERENCES public.departamentos (n_departamento) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.empleados
    OWNER to postgres;