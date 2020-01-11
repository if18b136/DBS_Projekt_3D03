create or replace function SP_login
(f_uid in varchar2,f_pw in varchar2)
return number
is
found number := 0;
pw varchar2(255);
login_success number(8) := 0;
begin
SELECT count(*) INTO found FROM account WHERE USERNAME = f_uid;
	
	IF found >= 1 THEN
		dbms_output.put_line('STUDENT FOUND');
		SELECT PASSWORD INTO pw FROM account WHERE USERNAME = f_uid;
		IF f_pw = pw THEN
			login_success := 1;
			dbms_output.put_line('LOGIN SUCCESSFUL!');
			RETURN login_success;
		ELSE
			login_success := 0;
			dbms_output.put_line('LOGIN FAILED!');
			RETURN login_success;
		END IF;
	ELSE 
		login_success := 0;
		dbms_output.put_line('NO STUDENT FOUND, SEARCHING INTO PERSONAL');
	END IF;
	
	SELECT count(*) INTO found FROM personal WHERE USERNAME = f_uid;
	IF found >= 1 THEN
		dbms_output.put_line('PERSONAL FOUND');
		SELECT PASSWORD INTO pw FROM personal WHERE USERNAME = f_uid;
		IF f_pw = pw THEN
			login_success := 1;
			dbms_output.put_line('LOGIN SUCCESSFUL!');
			RETURN login_success;
		ELSE
			login_success := 0;
			dbms_output.put_line('LOGIN FAILED!');
		END IF;
	ELSE 
		login_success := 0;
		dbms_output.put_line('NO PERSONAL FOUND, LOGIN FAILED');
	END IF;

return login_success;
end;
/