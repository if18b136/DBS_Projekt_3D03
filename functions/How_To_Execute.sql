set serveroutput on

--HOW TO EXECUTE FUNCTIONS


--PERSON_COUNT()
/
DECLARE
	n number;
BEGIN
	n := person_count(); 
END;
/

--LOGIN_CHECK()
/
DECLARE
	n boolean;
BEGIN
	n := login_check('if18b176', '1234'); 
END;
/

--CREATE_PERSON()
/
BEGIN
create_person('Berk', 'Berkmann', '10.10.1969');
END;
/ 


--SP_GET_STUDIENGANG
/
DECLARE
	n varchar2(255);
BEGIN
	n := SP_Get_studiengang('if18b176'); 
END;
/
