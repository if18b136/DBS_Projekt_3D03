/*********************************************************************
/**
/** Procedure: sp_add_rank
/** In: f_person_id, f_rang - ID of a person, rank
/** Description: Adds a new entry to the table personenrang
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_add_rank (f_person_id IN NUMBER, f_rang IN NUMBER)
AS
BEGIN
	INSERT INTO personenrang (fk_person_id, rang)
	VALUES (f_person_id, f_rang);
END;
/

/*********************************************************************
/**
/** Procedure: sp_remove_rank
/** In: f_person_id, f_rang - ID of a person, rank
/** Description: Removes an entry from the table personenrang
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_remove_rank (f_person_id IN NUMBER, f_rang IN NUMBER)
AS
BEGIN
	DELETE FROM personenrang
	WHERE ((f_person_id = fk_person_id) AND (f_rang = rang));
END;
/

/*********************************************************************
/**
/** Procedure: sp_add_staff
/** In: f_person_id, f_rang - ID of a person, wage-level, username, password
/** Description: Adds a new entry to the table personal
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_add_staff (f_person_id IN NUMBER, f_gehaltsklasse IN VARCHAR2, f_username IN VARCHAR2, f_password IN VARCHAR2)
AS
BEGIN
	INSERT INTO personal (fk_person_id, gehaltsklasse, username, password)
	VALUES (f_person_id, f_gehaltsklasse, f_username, f_password);
END;
/

/*********************************************************************
/**
/** Procedure: sp_delete_staff
/** In: f_personal_id - ID of staff
/** Description: Removes an entry from the table personal
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_delete_staff (f_personal_id IN NUMBER)
AS
BEGIN
	DELETE FROM personal
	WHERE personal_id = f_personal_id;
END;
/

/*********************************************************************
/**
/** Procedure: sp_add_student
/** In: f_person_id - ID of a person
/** Description: Adds a new entry to the table student
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_add_student (f_person_id IN NUMBER)
AS
BEGIN
	INSERT INTO student (fk_person_id)
	VALUES (f_person_id);
END;
/

/*********************************************************************
/**
/** Procedure: sp_delete_student
/** In: f_person_id - ID of a person
/** Description: Removes an entry from the table student
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_delete_student (f_student_id IN NUMBER)
AS
BEGIN
	DELETE FROM student
	WHERE student_id = f_student_id;
END;
/

/*********************************************************************
/**
/** Procedure: sp_add_account
/** In: f_student_id, f_studiengang_id, username, password - ID of the student, ID of a degree course, username and password of the account
/** Description: Adds a new entry to the table account
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_add_account (f_student_id IN NUMBER, f_studiengang_id IN NUMBER, f_username IN VARCHAR2, f_password VARCHAR2)
AS
BEGIN
	INSERT INTO account (fk_student_id, fk_studiengang_id, username, password)
	VALUES (f_student_id, f_studiengang_id, f_username, f_password);
END;
/

/*********************************************************************
/**
/** Procedure: sp_delete_student
/** In: f_username - Username of the student's account
/** Description: Removes an entry from the table account
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_delete_account (f_username IN VARCHAR2)
AS
BEGIN
	DELETE FROM account
	WHERE username = f_username;
END;
/

/*********************************************************************
/**
/** Procedure: sp_add_lector_to_lecture
/** In: f_personal_id, f_lva_id - ID of a lector, ID of a lecture
/** Description: Adds a new entry to the table leitet
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_add_lector_to_lecture (f_personal_id IN NUMBER, f_lva_id IN NUMBER)
AS
BEGIN
	INSERT INTO leitet (fk_personal_id, fk_lva_id)
	VALUES (f_personal_id, f_lva_id);
END;
/

/*********************************************************************
/**
/** Procedure: sp_remove_lector_from_lecture
/** In: f_personal_id, f_lva_id - ID of a lector, ID of a lecture
/** Description: Removes an entry from the table leitet
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_remove_lector_from_lecture (f_personal_id IN NUMBER, f_lva_id IN NUMBER)
AS
BEGIN
	DELETE FROM leitet
	WHERE ((f_personal_id = fk_personal_id) AND (f_lva_id = fk_lva_id));
END;
/

/*********************************************************************
/**
/** Procedure: sp_set_head_of_section
/** In: f_personal_id, f_studiengang_id - ID of the head of section, ID of the section
/** Description: Sets a new head of section to the given degree course
/**
/*********************************************************************

CREATE OR REPLACE PROCEDURE sp_set_head_of_section (f_personal_id IN NUMBER, f_studiengang_id IN NUMBER)
AS
BEGIN
	UPDATE studiengang
	SET fk_personal_id = f_personal_id
	WHERE studiengang_id = f_studiengang_id;
END;
/
