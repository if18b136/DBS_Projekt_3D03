CREATE OR REPLACE PROCEDURE SP_lva_students(f_titel in lva.titel%TYPE, c_lva_students OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_lva_students FOR
		SELECT person.vorname, person.nachname, account.username FROM LVA
		INNER JOIN studiengang ON studiengang.Studiengang_ID = LVA.fk_studiengang_Id
		INNER JOIN account ON account.fk_studiengang_id = studiengang.studiengang_id
		INNER JOIN student ON student.student_id = account.fk_student_id
		INNER JOIN person ON person.person_id = student.fk_person_id
		WHERE lva.titel = f_titel;
END;
/