CREATE OR REPLACE PROCEDURE sp_add_lecture (f_studiengang_id IN NUMBER, f_titel IN VARCHAR2, f_lektor_id In NUMBER)
AS
BEGIN
	INSERT INTO lva (fk_studiengang_id, titel)
	VALUES (f_studiengang_id, f_titel);
	
	INSERT INTO leitet (fk_lva_id, fk_personal_id)
	SELECT lva.lva_id, f_lektor_id FROM LVA
	WHERE f_titel = lva.lva_id;
END;
/