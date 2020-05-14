-- Default data to be loaded into database.
-- Place file in classpath.
-- For data-${platform}.sql: set spring.datasource.platform
-- Check spring.jpa.hibernate.ddl-auto for conflict. Set to 'none' or 'validate'
-- To use hibernate initialization, use import.sql
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');
INSERT INTO unit_of_measure (description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');