

DROP TABLE if exists obj_namespace_tbl;
DROP TABLE if exists obj_type_tbl;
DROP TABLE if exists obj_type_fields_tbl;
DROP TABLE if exists validator_tbl;
DROP TABLE if exists obj_type_validators_tbl;
DROP TABLE if exists obj_context_tbl;
DROP TABLE if exists obj_context_validators_tbl;


CREATE TABLE obj_namespace_tbl (
	namespace_id serial NOT NULL,
    obj_namespace varchar NOT NULL,
	code_prefix varchar NOT NULL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now(),
	CONSTRAINT obj_namespace_tbl_pkey PRIMARY KEY (namespace_id)
);

CREATE TABLE obj_type_tbl (
	type_id uuid NOT NULL DEFAULT gen_random_uuid(),
	type_name varchar NOT NULL,
	is_simple_type bool NULL DEFAULT false,
	namespace_id int4 NULL,
	extends_type_id uuid NULL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now(),
	CONSTRAINT obj_type_tbl_pkey PRIMARY KEY (type_id)
);

CREATE TABLE obj_type_fields_tbl (
	obj_type_id uuid NULL,
	field_name varchar NULL,
	field_type_id uuid NULL,
	is_basic_type bool NULL,
	is_list bool NULL
);

CREATE TABLE validator_tbl (
	validator_id uuid NULL DEFAULT gen_random_uuid(),
	validator_name varchar NOT NULL,
	java_classname varchar NOT NULL,
	config json NULL
);

CREATE TABLE obj_type_validators_tbl (
	obj_type_id uuid NULL,
	field_name varchar NULL,
	validator_id uuid NULL,
	validator_config json NULL
);

CREATE TABLE obj_context_tbl (
	context_key varchar NOT NULL,
	context_desc varchar NOT NULL,
	obj_type_id uuid NULL
);

CREATE TABLE obj_context_validators_tbl (
	context_key varchar NULL,
	field_name varchar NULL,
	validator_id uuid NULL,
	validator_config json NULL
);




