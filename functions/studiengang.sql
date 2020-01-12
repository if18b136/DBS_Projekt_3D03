CREATE OR REPLACE PROCEDURE SP_add_major (f_uid IN NUMBER, f_bezeichnung IN VARCHAR2, f_jahrgang IN VARCHAR2)
AS
f_leiter_id NUMBER := '0';
BEGIN
	SELECT personal_id INTO f_leiter_id FROM personal WHERE username = f_uid;

	INSERT INTO studiengang (fk_personal_id, bezeichnung, jahrgang)
	VALUES (f_leiter_id, f_bezeichnung, f_jahrgang);
END;
/


CREATE OR REPLACE PROCEDURE sp_delete_major (f_studiengang_id IN NUMBER)
AS
BEGIN
	DELETE FROM studiengang
	WHERE studiengang_id = f_studiengang_id;
END;
/
