DROP TABLE IF EXISTS comment;

CREATE TABLE comment (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  author VARCHAR(250) NOT NULL,
  comment VARCHAR(250) NOT NULL,
  fecha DATE DEFAULT NULL,
  status BOOLEAN DEFAULT FALSE
);

INSERT INTO comment (author, comment) VALUES
  ('Aliko', 'Dangote'),
  ('Bill', 'Gates'),
  ('Folrunsho', 'Alakija');