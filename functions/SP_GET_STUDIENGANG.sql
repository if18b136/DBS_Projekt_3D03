/*********************************************************************
/**
/** Procedure: sp_get_studiengang
/** In: f_uid - the username of student
/** Out: f_studiengang - the student's major
/** Description: Returns the major degree of a student
/**
/*********************************************************************

CREATE OR REPLACE FUNCTION SP_GET_STUDIENGANG (f_uid in varchar2)
return varchar2
IS
f_studiengang varchar2(255) := 'ERROR';
BEGIN
	SELECT BEZEICHNUNG
	INTO f_studiengang
	FROM STUDIENGANG 
	INNER JOIN ACCOUNT ON account.FK_studiengang_ID=studiengang.studiengang_id
	WHERE USERNAME = f_uid;
	dbms_output.put_line(f_studiengang);
return f_studiengang;
end;
/
