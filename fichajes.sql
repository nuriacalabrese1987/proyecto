-- Table: public.fichajes

-- DROP TABLE public.fichajes;

CREATE TABLE public.fichajes
(
    id_empleado integer NOT NULL,
    fecha timestamp without time zone,
    estado boolean,
    CONSTRAINT fichajes_id_empleado_fkey FOREIGN KEY (id_empleado)
        REFERENCES public.empleados (id_empleado) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.fichajes
    OWNER to postgres;