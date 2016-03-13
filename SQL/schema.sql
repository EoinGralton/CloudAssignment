CREATE SCHEMA assignment;

CREATE TABLE assignment.users ( 
	id                   int(10)  NOT NULL  ,
	email                varchar(50)  NOT NULL  ,
	password             binary(20)  NOT NULL  ,
	isadmin              int(10)  NOT NULL DEFAULT 0 ,
	CONSTRAINT pk_users PRIMARY KEY ( id )
 );

INSERT INTO assignment.users( id, email, password, isadmin ) VALUES ( 2, 'test@test.com', '[B@2da23947', 0 ); 

