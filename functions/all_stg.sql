CREATE OR REPLACE PROCEDURE SP_all_stg(c_all_stg OUT SYS_REFCURSOR)
IS
BEGIN
	OPEN c_all_stg FOR
		SELECT studiengang.bezeichnung, studiengang.jahrgang FROM studiengang;
END;
/
