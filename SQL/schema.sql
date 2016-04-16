CREATE TABLE assignment.member ( 
	email                varchar(50)    ,
	password             binary(20)  NOT NULL  ,
	id                   int  NOT NULL  AUTO_INCREMENT,
	CONSTRAINT idperson_idx UNIQUE ( id ) ,
	CONSTRAINT pk_member PRIMARY KEY ( id ),
	CONSTRAINT idx_member_email UNIQUE ( email ) 
 );

CREATE TABLE assignment.person ( 
	id                   int  NOT NULL AUTO_INCREMENT ,
	idmember             int    ,
	admin                bit    ,
	title                varchar(5)    ,
	firstname            varchar(30)    ,
	lastname             varchar(30)    ,
	dateofbirth          date    ,
	CONSTRAINT pk_person PRIMARY KEY ( id )
 );

CREATE TABLE assignment.policy ( 
	id                   int  NOT NULL  AUTO_INCREMENT,
	idperson             int    ,
	`outsideEuUS`        bit  NOT NULL  ,
	start                date    ,
	finish               date    ,
	health_insurance     bit    ,
	CONSTRAINT pk_policy PRIMARY KEY ( id )
 );

CREATE INDEX idx_policy ON assignment.policy ( idperson );

CREATE TABLE assignment.policyperson ( 
	`idPolicyPerson`     int  NOT NULL AUTO_INCREMENT,
	idpolicy             int  NOT NULL  ,
	title                varchar(5)    ,
	firstname            varchar(30)    ,
	lastname             varchar(30)    ,
	dateofbirth          date    ,
	CONSTRAINT pk_policyperson PRIMARY KEY ( `idPolicyPerson` )
 );

CREATE INDEX idpolicyfk_idx ON assignment.policyperson ( idpolicy );

CREATE VIEW assignment.personview AS SELECT person.*, member.email FROM person, member where person.idmember = member.id;

ALTER TABLE assignment.person ADD CONSTRAINT fk_person_member FOREIGN KEY ( id ) REFERENCES assignment.member( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE assignment.policy ADD CONSTRAINT fk_policy_person FOREIGN KEY ( idperson ) REFERENCES assignment.person( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE assignment.policyperson ADD CONSTRAINT idpolicyfk FOREIGN KEY ( idpolicy ) REFERENCES assignment.policy( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO assignment.member( id, email, password ) VALUES ( 1, 'test@test.com', '[B@6ce27568' ); 

INSERT INTO assignment.person( id, idmember, admin, title, firstname, lastname, dateofbirth ) VALUES ( 1, 1, '\0', 'Mr', 'Joe', 'Bloggs', '1990-05-02');

INSERT INTO assignment.policy( id, idperson, outsideEuUS, start, finish, health_insurance) VALUES ( 1, 1, '\0', '2016-06-26', '2016-07-04', '\0' );

INSERT INTO assignment.policyperson( idPolicyPerson, idpolicy, title, firstname, lastname, dateofbirth ) VALUES ( 1, 1, 'Mr', 'Joe', 'Bloggs', '1990-05-02' ), 
																												( 2, 1, 'Miss', 'Sarah', 'Bloggs', '1988-02-23' ), 
																												( 3, 1, 'Mr', 'Nick', 'Brown', '1970-11-16');