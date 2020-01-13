/*********************************************************************
/**
/** Function: person_count
/** Out: number of all persons
/** Description: Counts all persons in the database
/**
/*********************************************************************

CREATE OR REPLACE FUNCTION person_count
return number
AS total number(2) := 0; 

BEGIN
	SELECT count(*) into total
	FROM PERSON;
	DBMS_OUTPUT.PUT_LINE('Total no. of People: ' || total);
	RETURN total;
END;
/
