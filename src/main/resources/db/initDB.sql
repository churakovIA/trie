DROP TABLE IF EXISTS al_tree;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE al_tree
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    end_word    BOOL NOT NULL,
    parent_id   INTEGER,
    FOREIGN KEY (parent_id) REFERENCES al_tree (id)
);
CREATE UNIQUE INDEX al_name_parent_idx ON al_tree (name, parent_id);
CREATE INDEX al_parent_idx ON al_tree (parent_id);
