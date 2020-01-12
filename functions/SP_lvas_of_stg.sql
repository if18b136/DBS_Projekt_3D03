CREATE OR REPLACE PROCEDURE SP_lvas_of_stg(f_stg in studiengang.bezeichnung%TYPE, c_lva OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_lva FOR
		SELECT Lva.titel, person.nachname, person.vorname FROM LVA 
		INNER JOIN studiengang ON studiengang.Studiengang_ID = LVA.fk_studiengang_Id
		INNER JOIN leitet ON leitet.fk_lva_id = lva.lva_id
		INNER JOIN personal ON personal.personal_id = leitet.fk_personal_id
		INNER JOIN person ON person.person_id = personal.fk_person_id
		WHERE studiengang.bezeichnung = f_stg;
END;
/
