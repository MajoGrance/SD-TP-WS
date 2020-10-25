CREATE TABLE public.movil (
    identificador character varying(20) NOT NULL,
    tipo character varying(20)
);

CREATE TABLE public.ubicacion (
    id integer NOT NULL,
    fecha_hora timestamp with time zone,
    latitud double precision,
    longitud double precision,
    movil character varying(20) NOT NULL
);
ALTER TABLE ONLY public.movil
    ADD CONSTRAINT movil_pkey PRIMARY KEY (identificador);
ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT movil FOREIGN KEY (movil) REFERENCES public.movil(identificador) NOT VALID;
CREATE SEQUENCE ubicacion_sequence;
ALTER TABLE ubicacion ALTER COLUMN id SET DEFAULT nextval('ubicacion_sequence');
