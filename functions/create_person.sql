/*********************************************************************
/**
/** Procedure: create_person
/** In: f_vorname, f_nachname, f_geburtsdatum - Name and day of birth of a person
/** Description: Inserts a new entry into table person
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE create_person (f_vorname IN VARCHAR2, f_nachname IN VARCHAR2, f_geburtsdatum IN VARCHAR2)
AS
BEGIN
	INSERT INTO person (VORNAME, NACHNAME, GEBURTSDATUM)
	VALUES (f_vorname, f_nachname, to_date(f_geburtsdatum, 'DD.MM.YYYY'));
END;
/
