
--SP_LOGIN_STUDENT

CREATE OR REPLACE function SP_login_student
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
		dbms_output.put_line('NO STUDENT FOUND');
	END IF;
	
	

return login_success;
end;
/

--SP_LOGIN_LEKTOR

CREATE OR REPLACE function SP_login_lektor
(f_uid in varchar2,f_pw in varchar2)
return number
is
found number := 0;
pw varchar2(255);
rg number :=0;
login_success number(8) := 0;
begin
SELECT count(*) INTO found FROM personal WHERE USERNAME = f_uid;
SELECT rang INTO rg FROM personenrang
INNER JOIN person ON person.person_id = personenrang.fk_person_id
INNER JOIN personal ON personal.fk_person_id = person.person_id
WHERE personal.username = f_uid;
	
	IF rg = 2 THEN
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
				RETURN login_success;
			END IF;
		ELSE 
			login_success := 0;
			dbms_output.put_line('NO LEKTOR FOUND');
		END IF;
	ELSE
		login_success := 0;
		dbms_output.put_line('wrong rank');
	END IF;

return login_success;
end;
/

--SP_LOGIN_SOG

CREATE OR REPLACE function SP_login_sgo
(f_uid in varchar2,f_pw in varchar2)
return number
is
found number := 0;
pw varchar2(255);
rg number :=0;
login_success number(8) := 0;
begin
SELECT count(*) INTO found FROM personal WHERE USERNAME = f_uid;
SELECT rang INTO rg FROM personenrang
INNER JOIN person ON person.person_id = personenrang.fk_person_id
INNER JOIN personal ON personal.fk_person_id = person.person_id
WHERE personal.username = f_uid;
	
	IF rg = 3 THEN
		IF found >= 1 THEN
			dbms_output.put_line('SGO FOUND');
			SELECT PASSWORD INTO pw FROM personal WHERE USERNAME = f_uid;
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
			dbms_output.put_line('NO SGO FOUND');
		END IF;
	ELSE
		login_success := 0;
		dbms_output.put_line('WRONG RANK');
	END IF;

return login_success;
end;
/

--SP_LOGIN_ADMIN

CREATE OR REPLACE function SP_login_admin
(f_uid in varchar2,f_pw in varchar2)
return number
is
found number := 0;
pw varchar2(255);
rg number :=0;
login_success number(8) := 0;
begin
SELECT count(*) INTO found FROM personal WHERE USERNAME = f_uid;
SELECT rang INTO rg FROM personenrang
INNER JOIN person ON person.person_id = personenrang.fk_person_id
INNER JOIN personal ON personal.fk_person_id = person.person_id
WHERE personal.username = f_uid;
	
	IF rg = 4 THEN
		IF found >= 1 THEN
			dbms_output.put_line('ADMIN FOUND');
			SELECT PASSWORD INTO pw FROM personal WHERE USERNAME = f_uid;
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
			dbms_output.put_line('NO ADMIN FOUND');
		END IF;
	ELSE
		login_success := 0;
		dbms_output.put_line('WRONG RANK');
	END IF;

return login_success;
end;
/
