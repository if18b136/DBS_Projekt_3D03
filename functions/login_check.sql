CREATE OR REPLACE FUNCTION login_check (f_uid IN VARCHAR2, f_pw IN VARCHAR2)
RETURN boolean AS 
found number := 0;
pw varchar2(255);
login_success boolean := false;
BEGIN
	
	SELECT count(*) INTO found FROM account WHERE USERNAME = f_uid;
	
	IF found >= 1 THEN
		dbms_output.put_line('STUDENT FOUND');
		SELECT PASSWORD INTO pw FROM student WHERE USERNAME = f_uid;
		IF f_pw = pw THEN
			login_success := TRUE;
			dbms_output.put_line('LOGIN SUCCESSFUL!');
			RETURN login_success;
		ELSE
			login_success := false;
			dbms_output.put_line('LOGIN FAILED!');
			RETURN login_success;
		END IF;
	ELSE 
		login_success := false;
		dbms_output.put_line('NO STUDENT FOUND, SEARCHING INTO PERSONAL');
	END IF;
	
	SELECT count(*) INTO found FROM personal WHERE USERNAME = f_uid;
	IF found >= 1 THEN
		dbms_output.put_line('PERSONAL FOUND');
		SELECT PASSWORD INTO pw FROM personal WHERE USERNAME = f_uid;
		IF f_pw = pw THEN
			login_success := TRUE;
			dbms_output.put_line('LOGIN SUCCESSFUL!');
			RETURN login_success;
		ELSE
			login_success := false;
			dbms_output.put_line('LOGIN FAILED!');
		END IF;
	ELSE 
		login_success := false;
		dbms_output.put_line('NO PERSONAL FOUND, LOGIN FAILED');
	END IF;
	RETURN login_success;
END;
/
