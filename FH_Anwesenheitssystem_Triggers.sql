/*********************************************************************
/**
/** Trigger: tr_br_i_person_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_person_id
	BEFORE INSERT ON person
	FOR EACH ROW
BEGIN
  SELECT sq_person.nextval
  INTO :new.person_id
  FROM dual;
END tr_br_i_person_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_personal_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_personal_id
	BEFORE INSERT ON personal
	FOR EACH ROW
BEGIN
  SELECT sq_personal.nextval
  INTO :new.personal_id
  FROM dual;
END tr_br_i_personal_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_student_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_student_id
	BEFORE INSERT ON student
	FOR EACH ROW
BEGIN
  SELECT sq_student.nextval
  INTO :new.student_id
  FROM dual;
END tr_br_i_student_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_studiengang_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_studiengang_id
	BEFORE INSERT ON studiengang
	FOR EACH ROW
BEGIN
  SELECT sq_studiengang.nextval
  INTO :new.studiengang_id
  FROM dual;
END tr_br_i_studiengang_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_lva_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_lva_id
	BEFORE INSERT ON lva
	FOR EACH ROW
BEGIN
  SELECT sq_lva.nextval
  INTO :new.lva_id
  FROM dual;
END tr_br_i_lva_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_lektor_lva_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_lektor_lva_id
	BEFORE INSERT ON leitet
	FOR EACH ROW
BEGIN
  SELECT sq_lektor_lva.nextval
  INTO :new.lektor_lva_id
  FROM dual;
END tr_br_i_lektor_lva_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_lva_einheit_id
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_lva_einheit_id
	BEFORE INSERT ON lva_einheit
	FOR EACH ROW
BEGIN
  SELECT sq_lva_einheit.nextval
  INTO :new.lva_einheit_id
  FROM dual;
END tr_br_i_lva_einheit_id;
/

/*********************************************************************
/**
/** Trigger: tr_br_i_kid
/** Type: Before row
/** Type Extension: insert
/** Description: adds the auto-increment ID to a new table-entry
/**
/*********************************************************************

CREATE OR REPLACE TRIGGER tr_br_i_kid
  BEFORE INSERT ON kreuzerl_liste
  FOR EACH ROW
BEGIN
  SELECT sq_kreuzerl_liste.nextval
  INTO :new.kid
  FROM dual;
END tr_br_i_kid;
/
