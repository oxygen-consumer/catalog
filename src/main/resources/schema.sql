/*
 * Sequences for PK
 */
CREATE SEQUENCE IF NOT EXISTS course_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS grade_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS student_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS teacher_id_seq START WITH 1 INCREMENT BY 50;

/*
 * Tables
 */
CREATE TABLE IF NOT EXISTS courses (
    id          BIGINT NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    start_date  TIMESTAMP WITHOUT TIME ZONE,
    end_date    TIMESTAMP WITHOUT TIME ZONE,
    teacher_id  BIGINT,
    CONSTRAINT pk_courses PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS grades
(
    id          BIGINT           NOT NULL,
    grade_value DOUBLE PRECISION NOT NULL,
    course_id   BIGINT,
    student_id  BIGINT,
    CONSTRAINT pk_grades PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS students
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255),
    birth_date TIMESTAMP WITHOUT TIME ZONE,
    email      VARCHAR(255),
    CONSTRAINT pk_students PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS teachers
(
    id         BIGINT NOT NULL,
    name       VARCHAR(255),
    birth_date TIMESTAMP WITHOUT TIME ZONE,
    email      VARCHAR(255),
    CONSTRAINT pk_teachers PRIMARY KEY (id)
);

-- TODO: remove student_course, use grades instead
CREATE TABLE IF NOT EXISTS student_course
(
    course_id  BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    CONSTRAINT pk_student_course PRIMARY KEY (course_id, student_id)
);

/*
 FK constraints
 */
ALTER TABLE grades
    ADD CONSTRAINT FK_GRADES_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (id);

ALTER TABLE grades
    ADD CONSTRAINT FK_GRADES_ON_STUDENT FOREIGN KEY (student_id) REFERENCES students (id);

ALTER TABLE courses
    ADD CONSTRAINT FK_COURSES_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teachers (id);

ALTER TABLE student_course
    ADD CONSTRAINT fk_stucou_on_course_entity FOREIGN KEY (course_id) REFERENCES courses (id);

ALTER TABLE student_course
    ADD CONSTRAINT fk_stucou_on_student_entity FOREIGN KEY (student_id) REFERENCES students (id);


COMMIT;