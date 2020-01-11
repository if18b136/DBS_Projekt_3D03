CREATE OR REPLACE PROCEDURE SP_lva_info(f_uid in account.username%TYPE, c_lva OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_lva FOR
		SELECT Lva.titel, person.nachname, person.vorname FROM LVA 
		INNER JOIN studiengang ON studiengang.Studiengang_ID = LVA.fk_studiengang_Id
		INNER JOIN account ON account.fk_studiengang_ID = studiengang.studiengang_id
		INNER JOIN leitet ON leitet.fk_lva_id = lva.lva_id
		INNER JOIN personal ON personal.personal_id = leitet.fk_personal_id
		INNER JOIN person ON person.person_id = personal.fk_person_id
		WHERE account.USERNAME = f_uid;
END;
/
