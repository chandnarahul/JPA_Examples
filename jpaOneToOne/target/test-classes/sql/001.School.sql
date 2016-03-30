create table school(
	schoolId BIGINT NOT NULL AUTO_INCREMENT,
	name NVARCHAR2(100) NOT NULL,
	PRIMARY KEY (schoolId)
);
CREATE INDEX schoolId_IDX1 on school (name);
