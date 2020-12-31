CREATE TABLE phish_domains
(
  id NUMBER NOT NULL PRIMARY KEY,
  phish_domain VARCHAR2(1000) NOT NULL UNIQUE
);

CREATE SEQUENCE seq_phish_domain_id
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;

CREATE TABLE phish_urls
(
  id NUMBER NOT NULL PRIMARY KEY,
  domain_id NUMBER NOT NULL REFERENCES phish_domains(id),
  phish_url VARCHAR2(4000) NOT NULL UNIQUE,
  phish_tank_id NUMBER,
  phish_tank_url VARCHAR2(1000),
  register_at DATE NOT NULL
);

CREATE INDEX index_phish_urls ON phish_urls(domain_id, phish_tank_id, register_at);

CREATE SEQUENCE seq_phish_url_id
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;

CREATE TABLE users
(
  id NUMBER NOT NULL PRIMARY KEY,
  name VARCHAR2(1000) NOT NULL,
  email VARCHAR2(1000) NOT NULL UNIQUE,
  password VARCHAR2(1000) NOT NULL,
  status VARCHAR2(100) NOT NULL CHECK(status = 'active' or status = 'confirming' or status = 'suspend'),
  register_at DATE NOT NULL
);

CREATE SEQUENCE seq_user_id
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;

CREATE TABLE email_auth_temps
(
  user_id NUMBER NOT NULL PRIMARY KEY,
  token VARCHAR2(1000) NOT NULL,
  expired_at DATE NOT NULL
);

CREATE TABLE password_reset_temps
(
  user_id NUMBER NOT NULL PRIMARY KEY,
  token VARCHAR2(1000) NOT NULL,
  expired_at DATE NOT NULL
);

CREATE TABLE user_posts
(
  id NUMBER NOT NULL PRIMARY KEY,
  user_id NUMBER NOT NULL REFERENCES users(id),
  url_id NUMBER NOT NULL REFERENCES phish_urls(id),
  post_contents VARCHAR2(4000) NOT NULL,
  register_at DATE NOT NULL,
  CONSTRAINT unique_key_posts UNIQUE(user_id, url_id)
);

CREATE SEQUENCE seq_post_id
  MINVALUE 1
  MAXVALUE 9999999999
  START WITH 1
  INCREMENT BY 1;
