create table chairman(
	chairmanId BIGINT NOT NULL AUTO_INCREMENT,
	schoolId BIGINT NOT NULL,
	name NVARCHAR2(100) NOT NULL,
	PRIMARY KEY (chairmanId)
);
CREATE INDEX chairman_IDX1 on chairman (name);
CREATE UNIQUE INDEX chairman_IDX2 on chairman (chairmanId, schoolId);
ALTER TABLE chairman ADD FOREIGN KEY (schoolId) REFERENCES school(schoolId);