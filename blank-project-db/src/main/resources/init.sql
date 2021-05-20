DROP SCHEMA if EXISTS chuprynin;
CREATE SCHEMA if NOT EXISTS chuprynin;
DROP USER if EXISTS usr;
CREATE USER usr WITH password 'pass';
GRANT ALL ON SCHEMA chuprynin TO usr;