CREATE OR REPLACE PROCEDURE SP_res_lva(f_uid in personal.personal_ID%TYPE, c_res_lva OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_res_lva FOR
		SELECT Lva.titel, studiengang.bezeichnung FROM LVA
		INNER JOIN studiengang ON studiengang.Studiengang_ID = LVA.fk_studiengang_Id
		INNER JOIN leitet ON leitet.fk_lva_id = lva.lva_id
		INNER JOIN personal ON personal.personal_id = leitet.fk_personal_id
		WHERE personal.USERNAME = f_uid;
END;
/