create table student(
	studentId BIGINT NOT NULL AUTO_INCREMENT,
	schoolId BIGINT NOT NULL,
	name NVARCHAR2(100) NOT NULL,
	PRIMARY KEY (studentId)
);
CREATE INDEX student_IDX1 on student (name);
CREATE UNIQUE INDEX student_IDX2 on student (studentId, schoolId);
ALTER TABLE student ADD FOREIGN KEY (schoolId) REFERENCES school(schoolId);