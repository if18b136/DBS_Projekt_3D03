CREATE OR REPLACE PROCEDURE SP_units_of_lva(f_lva in lva.lva_id%TYPE, c_lva_unit OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_lva_unit FOR
		SELECT Lva_einheit.bezeichnung, lva_einheit.anfang, lva_einheit.ende, person.vorname, person.nachname FROM lva
		INNER JOIN leitet ON leitet.fk_lva_id = lva.lva_id
		INNER JOIN lva_einheit on lva_einheit.fk_lektor_lva_id = leitet.lektor_lva_id
		INNER JOIN personal ON personal.personal_id = leitet.fk_personal_id
		INNER JOIN person ON person.person_id = personal.fk_person_id
		WHERE lva.lva_id = f_lva;
END;
/