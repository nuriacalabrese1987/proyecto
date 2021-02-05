-- Table: public.departamentos

-- DROP TABLE public.departamentos;

CREATE TABLE public.departamentos
(
    n_departamento integer NOT NULL,
    nombre character varying COLLATE pg_catalog."default",
    CONSTRAINT departamentos_pkey PRIMARY KEY (n_departamento)
)

TABLESPACE pg_default;

ALTER TABLE public.departamentos
    OWNER to postgres;