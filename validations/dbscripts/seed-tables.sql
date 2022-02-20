
INSERT INTO client_tbl (client_name) values ('Valpack');

INSERT INTO user_tbl (email, password, salt, client_id) values 
('kalyan.chillara@gmail.com', 'test123', 'abcdef', SELECT sha256('test123abcdef'),1);


INSERT INTO obj_namespace_tbl
(obj_namespace, code_prefix, created_at, modified_at, created_by, modified_by)
VALUES('DEFAULT', 'java.lang', now(), now()1,1,true);

INSERT INTO obj_namespace_tbl
(obj_namespace, code_prefix, created_at, modified_at)
VALUES('TransfersAPI', 'com.valpack.transfers.bl', now(), now());


delete obj_type_tbl;
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'Boolean', true, 1, now(), now(), 1, 1, 1);
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'Integer', true, 1, now(), now(), 1, 1, 1);
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'Long', true, 1, now(), now(), 1, 1, 1);
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'Double', true, 1, now(), now(), 1, 1, 1);
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'String', true, 1, now(), now(), 1, 1, 1);
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at, client_id, created_by, modified_by)
VALUES(gen_random_uuid(), 'Date', true, 1, now(), now(), 1, 1, 1);



