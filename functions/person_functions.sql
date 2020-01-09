CREATE OR REPLACE PROCEDURE sp_add_rank (f_person_id IN NUMBER, f_rang IN NUMBER)
AS
BEGIN
	INSERT INTO personenrang (fk_person_id, rang)
	VALUES (f_person_id, f_rang);
END;
/

CREATE OR REPLACE PROCEDURE sp_remove_rank (f_person_id IN NUMBER, f_rang IN NUMBER)
AS
BEGIN
	DELETE FROM personenrang
	WHERE ((f_person_id = fk_person_id) AND (f_rang = rang));
END;
/

CREATE OR REPLACE PROCEDURE sp_add_staff (f_person_id IN NUMBER, f_gehaltsklasse IN VARCHAR2, f_username IN VARCHAR2, f_password IN VARCHAR2)
AS
BEGIN
	INSERT INTO personal (fk_person_id, gehaltsklasse, username, password)
	VALUES (f_person_id, f_gehaltsklasse, f_username, f_password);
END;
/

CREATE OR REPLACE PROCEDURE sp_delete_staff (f_personal_id IN NUMBER)
AS
BEGIN
	DELETE FROM personal
	WHERE personal_id = f_personal_id;
END;
/

CREATE OR REPLACE PROCEDURE sp_add_student (f_person_id IN NUMBER)
AS
BEGIN
	INSERT INTO student (fk_person_id)
	VALUES (f_person_id);
END;
/

CREATE OR REPLACE PROCEDURE sp_delete_student (f_student_id IN NUMBER)
AS
BEGIN
	DELETE FROM student
	WHERE student_id = f_student_id;
END;
/

CREATE OR REPLACE PROCEDURE sp_add_account (f_student_id IN NUMBER, f_studiengang_id IN NUMBER, f_username IN VARCHAR2, f_password VARCHAR2)
AS
BEGIN
	INSERT INTO account (fk_student_id, fk_studiengang_id, username, password)
	VALUES (f_student_id, f_studiengang_id, f_username, f_password);
END;
/

CREATE OR REPLACE PROCEDURE sp_delete_account (f_username IN VARCHAR2)
AS
BEGIN
	DELETE FROM account
	WHERE username = f_username;
END;
/

CREATE OR REPLACE PROCEDURE sp_add_lector_to_lecture (f_personal_id IN NUMBER, f_lva_id IN NUMBER)
AS
BEGIN
	INSERT INTO leitet (fk_personal_id, fk_lva_id)
	VALUES (f_personal_id, f_lva_id);
END;
/

CREATE OR REPLACE PROCEDURE sp_remove_lector_from_lecture (f_personal_id IN NUMBER, f_lva_id IN NUMBER)
AS
BEGIN
	DELETE FROM leitet
	WHERE ((f_personal_id = fk_personal_id) AND (f_lva_id = fk_lva_id));
END;
/

CREATE OR REPLACE PROCEDURE sp_set_head_of_section (f_personal_id IN NUMBER, f_studiengang_id IN NUMBER)
AS
BEGIN
	UPDATE studiengang
	SET fk_personal_id = f_personal_id
	WHERE studiengang_id = f_studiengang_id;
END;
/
