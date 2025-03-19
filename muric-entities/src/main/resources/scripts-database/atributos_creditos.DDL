-- muric_schema.atributos_creditos definition

-- Drop table

-- DROP TABLE muric_schema.atributos_creditos;

CREATE TABLE muric_schema.atributos_creditos
(
    id                             int8 DEFAULT nextval('muric_schema.atributos_credito_id_seq'::regclass) NOT NULL,
    identificacion_credito_entidad int8 NULL,
    tipo_identificacion            int4 NULL,
    numero_identificacion          int8 NULL,
    clave_atributo                 int4 NULL,
    valor_atributo                 text NULL
);