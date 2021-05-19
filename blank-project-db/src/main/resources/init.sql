drop schema if exists chuprynin;
create schema if not exists chuprynin;
drop user if exists usr;
create user usr with password 'pass';
grant all on schema chuprynin to usr;