INSERT INTO SYS_USER (id,username,password) VALUES (1,'www','www');
INSERT INTO SYS_USER (id,username,password) VALUES (2,'yyy','yyy');

INSERT INTO SYS_ROLE (id,username) VALUES (1,'ROLE_ADMIN');
INSERT INTO SYS_USER (id,username) VALUES (2,'ROLE_USER');

INSERT INTO SYS_USER_ROLES(ID,SYS_USER_ID,ROLES_ID) VALUES (1,1,1);
INSERT INTO SYS_USER_ROLES(ID,SYS_USER_ID,ROLES_ID) VALUES (2,2,2);

