delete obj_namespace_tbl;

INSERT INTO obj_namespace_tbl
(obj_namespace, code_prefix, created_at, modified_at)
VALUES('JAVA', 'java.lang', now(), now());

INSERT INTO obj_namespace_tbl
(obj_namespace, code_prefix, created_at, modified_at)
VALUES('RetailTransfers', 'com.alacriti.rmt.model', now(), now());


delete obj_type_tbl;
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Boolean', true, 1, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Integer', true, 1, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Long', true, 1, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Double', true, 1, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'String', true, 1, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Date', true, 1, now(), now());


INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Boolean', true, 2, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Integer', true, 2, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Long', true, 2, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Double', true, 2, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'String', true, 2, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Date', true, 2, now(), now());


/*
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Boolean', true, ?, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Integer', true, ?, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Long', true, ?, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Double', true, ?, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'String', true, ?, now(), now());
INSERT INTO obj_type_tbl(type_id, type_name, is_simple_type, namespace_id, created_at, modified_at)
VALUES(gen_random_uuid(), 'Date', true, ?, now(), now());
*/
