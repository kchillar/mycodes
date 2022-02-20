DROP TABLE if exists client_tbl;
DROP TABLE if exists user_tbl;
DROP TABLE if exists obj_namespace_tbl;
DROP TABLE if exists obj_type_tbl;
DROP TABLE if exists obj_type_fields_tbl;
DROP TABLE if exists validator_tbl;
DROP TABLE if exists obj_type_validators_tbl;
DROP TABLE if exists obj_context_tbl;
DROP TABLE if exists obj_context_validators_tbl;

CREATE TABLE client_tbl (
	client_id serial NOT NULL,
    client_name varchar NOT NULL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now(),
	CONSTRAINT client_tbl_pk PRIMARY KEY (client_id)
);


CREATE TABLE user_tbl (
 user_id BIGSERIAL NOT NULL,
 email varchar NOT NULL,
 password varchar NOT NULL,
 salt varchar NOT NULL,
 client_id integer NOT NULL,
 status varchar,
 CONSTRAINT user_tbl_pk PRIMARY KEY (user_id),
 UNIQUE (email)
 );


CREATE TABLE obj_namespace_tbl (
    namespace_id serial NOT NULL,	
    obj_namespace varchar NOT NULL,
    client_id integer not NULL, 
	code_prefix varchar NOT NULL,
	is_default BOOLEAN DEFAULT FALSE,
	created_by BIGSERIAL,
	modified_by BIGSERIAL,	
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now(),
	CONSTRAINT obj_namespace_tbl_pk PRIMARY KEY (namespace_id),
	CONSTRAINT obj_namespace_tbl_fk FOREIGN KEY(client_id) REFERENCES client_tbl(client_id),
	UNIQUE (client_id, obj_namespace)	
);

CREATE TABLE obj_type_tbl (    
    type_name varchar NOT NULL,    
	is_simple_type bool NULL DEFAULT false,	
	extends_type_id uuid NULL,	
	type_id uuid NOT NULL DEFAULT gen_random_uuid(),
	namespace_id integer NULL,
	created_by BIGSERIAL,
	modified_by BIGSERIAL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now(),
	CONSTRAINT obj_type_tbl_pkey PRIMARY KEY (type_id),
	UNIQUE(type_name, namespace_id)
);

CREATE TABLE obj_type_fields_tbl (
	obj_type_id uuid NOT NULL,
	obj_fields json NOT NULL,
	created_by BIGSERIAL,
	modified_by BIGSERIAL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now()	
);

CREATE TABLE validator_tbl (
	validator_name varchar NOT NULL,
	java_classname varchar NOT NULL,
	validator_config json NULL,
	validator_id uuid NULL DEFAULT gen_random_uuid(),
	namespace_id integer NOT NULL,
	created_by BIGSERIAL,
	modified_by BIGSERIAL,
	created_at timestamp NULL DEFAULT now(),
	modified_at timestamp NULL DEFAULT now()	
	
);


CREATE TABLE obj_type_validators_tbl (	    
	obj_type_id uuid NOT NULL,
	field_name varchar NOT NULL,	
	validator_id uuid,
	validator_config json,
	namespace_id integer NOT NULL
);

CREATE TABLE obj_context_tbl (
    context_desc varchar NOT NULL,
	context_key varchar NOT NULL,	
	namespace_id1 integer,
	namespace_id2 integer	
);


CREATE TABLE obj_context_validators_tbl (
	context_key varchar NOT NULL,
	obj_type_id uuid  NOT NULL
	field_name varchar NOT NULL,
	validator_id uuid,
	validator_config json
);




