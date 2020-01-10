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
