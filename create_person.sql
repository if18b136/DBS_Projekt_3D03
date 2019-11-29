CREATE OR REPLACE PROCEDURE create_person (f_vorname IN VARCHAR2, f_nachname IN VARCHAR2, f_geburtsdatum IN VARCHAR2)
AS
BEGIN
	INSERT INTO person (VORNAME, NACHNAME, GEBURTSDATUM)
	VALUES (f_vorname, f_nachname, to_date(f_geburtsdatum, 'DD.MM.YYYY'));
END;
/
