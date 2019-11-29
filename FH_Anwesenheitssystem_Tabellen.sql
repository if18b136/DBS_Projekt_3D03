--------------------------------------------------------
--  FH Anwesenheitssystem Datenbank
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PERSON
--  Person (Person_ID, Vorname, Nachname, Geburtsdatum)
--------------------------------------------------------

  CREATE TABLE "PERSON" 
   (  "PERSON_ID" NUMBER, 
  "VORNAME" VARCHAR2(255), 
  "NACHNAME" VARCHAR2(255),
  "GEBURTSDATUM" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table PERSONAL
--  Personal (ID, Person_ID, Gehaltsklasse)
--------------------------------------------------------

  CREATE TABLE "PERSONAL" 
   (  "PERSONAL_ID" NUMBER, 
  "FK_PERSON_ID" NUMBER, 
  "GEHALTSKLASSE" VARCHAR2(255),
  "USERNAME" VARCHAR2(255) UNIQUE,
  "PASSWORD" VARCHAR2(255)
   ) ;
--------------------------------------------------------
--  DDL for Table PERSONENRANG
--  Personal (Person_ID, Rang)
--------------------------------------------------------

  CREATE TABLE "PERSONENRANG" 
   (  "FK_PERSON_ID" NUMBER,
  "RANG" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table STUDENT
--  Student (ID, Vorname, Nachname, Studiengang)
--------------------------------------------------------

  CREATE TABLE "STUDENT" 
   (  "STUDENT_ID" NUMBER, 
  "FK_PERSON_ID" NUMBER,
  "FK_STUDIENGANG_ID" NUMBER,
  "USERNAME" VARCHAR2(255) UNIQUE,
  "PASSWORD" VARCHAR2(255)
   ) ;
--------------------------------------------------------
--  DDL for Table STUDIENGANG
--  Student (ID, Vorname, Nachname, Studiengang)
--------------------------------------------------------

  CREATE TABLE "STUDIENGANG" 
   (  "STUDIENGANG_ID" NUMBER, 
  "BEZEICHNUNG" VARCHAR2(255),
  "JAHRGANG" VARCHAR2(255),
  "FK_PERSONAL_ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table LVA
--  LVA (ID, Titel, Lektor, Studiengang)
--------------------------------------------------------

  CREATE TABLE "LVA" 
   (  "LVA_ID" NUMBER, 
  "FK_STUDIENGANG_ID" NUMBER,
  "TITEL" VARCHAR(255)
   ) ;
--------------------------------------------------------
--  DDL for Table LVA_EINHEIT
--  LVA_Einheit (ID, LVA_ID, Anfang, Ende)
--------------------------------------------------------

  CREATE TABLE "LVA_EINHEIT" 
   (  "LVA_EINHEIT_ID" NUMBER, 
  "FK_LEKTOR_LVA_ID" NUMBER,
  "BEZEICHNUNG" VARCHAR2(255), 
  "ANFANG" DATE,
  "ENDE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table KREUZERL_LISTE
--  Kreuzerl-Liste (LVA_Einh_ID, Anzahl_Kreuze)  
--------------------------------------------------------

  CREATE TABLE "KREUZERL_LISTE" 
   (  "FK_LVA_EINHEIT_ID" NUMBER,
  "ANZAHL_KREUZE" VARCHAR2(6)
   ) ;
--------------------------------------------------------
--  DDL for Table KREUZERL_STUDENT
--  Kreuzerl-Student (LVA_Einh_ID, StudentID, Nummer)
--------------------------------------------------------

  CREATE TABLE "KREUZERL_STUDENT" 
   (  "FK_LVA_EINHEIT_ID" NUMBER,
  "FK_STUDENT_ID" NUMBER,
  "NUMMER" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for Table LEITET
--  Personal leitet lva/lva_einheit (Lektor_LVA_ID, LVA_ID, Personal_ID, )
--------------------------------------------------------

  CREATE TABLE "LEITET" 
   (  "LEKTOR_LVA_ID" NUMBER,
  "FK_LVA_ID" NUMBER,
  "FK_PERSONAL_ID" NUMBER
   ) ;



--------------------------------------------------------
--  DDL for Index SYS_C0040324  // kann man auslassen
--------------------------------------------------------



--------------------------------------------------------
--  Constraints for Table PERSON
--------------------------------------------------------

  ALTER TABLE "PERSON" MODIFY ("PERSON_ID" NOT NULL ENABLE);

  ALTER TABLE "PERSON" MODIFY ("VORNAME" NOT NULL ENABLE);

  ALTER TABLE "PERSON" MODIFY ("NACHNAME" NOT NULL ENABLE);

  ALTER TABLE "PERSON" MODIFY ("GEBURTSDATUM" NOT NULL ENABLE);
 
  ALTER TABLE "PERSON" ADD PRIMARY KEY ("PERSON_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table PERSONAL
--------------------------------------------------------

  ALTER TABLE "PERSONAL" MODIFY ("PERSONAL_ID" NOT NULL ENABLE);

  ALTER TABLE "PERSONAL" MODIFY ("FK_PERSON_ID" NOT NULL ENABLE);

  ALTER TABLE "PERSONAL" MODIFY ("GEHALTSKLASSE" NOT NULL ENABLE);

  ALTER TABLE "PERSONAL" MODIFY ("USERNAME" NOT NULL ENABLE);  
 
  ALTER TABLE "PERSONAL" MODIFY ("PASSWORD" NOT NULL ENABLE);

  ALTER TABLE "PERSONAL" ADD PRIMARY KEY ("PERSONAL_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table PERSONENRANG
--------------------------------------------------------

  ALTER TABLE "PERSONENRANG" MODIFY ("FK_PERSON_ID" NOT NULL ENABLE);

  ALTER TABLE "PERSONENRANG" MODIFY ("RANG" NOT NULL ENABLE);

  ALTER TABLE "PERSONENRANG" ADD PRIMARY KEY ("FK_PERSON_ID", "RANG") ENABLE;

--------------------------------------------------------
--  Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "STUDENT" MODIFY ("STUDENT_ID" NOT NULL ENABLE);

  ALTER TABLE "STUDENT" MODIFY ("FK_PERSON_ID" NOT NULL ENABLE);

  ALTER TABLE "STUDENT" MODIFY ("FK_STUDIENGANG_ID" NOT NULL ENABLE);  

  ALTER TABLE "STUDENT" MODIFY ("USERNAME" NOT NULL ENABLE);  
 
  ALTER TABLE "STUDENT" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "STUDENT" ADD PRIMARY KEY ("STUDENT_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table STUDIENGANG
--------------------------------------------------------

  ALTER TABLE "STUDIENGANG" MODIFY ("STUDIENGANG_ID" NOT NULL ENABLE);

  ALTER TABLE "STUDIENGANG" MODIFY ("BEZEICHNUNG" NOT NULL ENABLE);

  ALTER TABLE "STUDIENGANG" MODIFY ("JAHRGANG" NOT NULL ENABLE);

  ALTER TABLE "STUDIENGANG" MODIFY ("FK_PERSONAL_ID" NOT NULL ENABLE);
 
  ALTER TABLE "STUDIENGANG" ADD PRIMARY KEY ("STUDIENGANG_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table LVA
--------------------------------------------------------

  ALTER TABLE "LVA" MODIFY ("LVA_ID" NOT NULL ENABLE);

  ALTER TABLE "LVA" MODIFY ("FK_STUDIENGANG_ID" NOT NULL ENABLE);

  ALTER TABLE "LVA" MODIFY ("TITEL" NOT NULL ENABLE);
 
  ALTER TABLE "LVA" ADD PRIMARY KEY ("LVA_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table LVA_EINHEIT
--------------------------------------------------------

  ALTER TABLE "LVA_EINHEIT" MODIFY ("LVA_EINHEIT_ID" NOT NULL ENABLE);

  ALTER TABLE "LVA_EINHEIT" MODIFY ("FK_LEKTOR_LVA_ID" NOT NULL ENABLE);

  ALTER TABLE "LVA_EINHEIT" MODIFY ("ANFANG" NOT NULL ENABLE);

  ALTER TABLE "LVA_EINHEIT" MODIFY ("ENDE" NOT NULL ENABLE);
 
  ALTER TABLE "LVA_EINHEIT" ADD PRIMARY KEY ("LVA_EINHEIT_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table KREUZERL_LISTE
--------------------------------------------------------

  ALTER TABLE "KREUZERL_LISTE" MODIFY ("FK_LVA_EINHEIT_ID" NOT NULL ENABLE);

  ALTER TABLE "KREUZERL_LISTE" MODIFY ("ANZAHL_KREUZE" NOT NULL ENABLE);
 
  ALTER TABLE "KREUZERL_LISTE" ADD PRIMARY KEY ("FK_LVA_EINHEIT_ID") ENABLE;

--------------------------------------------------------
--  Constraints for Table KREUZERL_STUDENT
--------------------------------------------------------

  ALTER TABLE "KREUZERL_STUDENT" MODIFY ("FK_LVA_EINHEIT_ID" NOT NULL ENABLE);

  ALTER TABLE "KREUZERL_STUDENT" MODIFY ("FK_STUDENT_ID" NOT NULL ENABLE);

  ALTER TABLE "KREUZERL_STUDENT" MODIFY ("NUMMER" NOT NULL ENABLE);

  ALTER TABLE "KREUZERL_STUDENT" ADD PRIMARY KEY ("FK_LVA_EINHEIT_ID", "FK_STUDENT_ID", "NUMMER") ENABLE;

--------------------------------------------------------
--  Constraints for Table LEITET
--------------------------------------------------------

  ALTER TABLE "LEITET" MODIFY ("LEKTOR_LVA_ID" NOT NULL ENABLE);
 
  ALTER TABLE "LEITET" MODIFY ("FK_LVA_ID" NOT NULL ENABLE);

  ALTER TABLE "LEITET" MODIFY ("FK_PERSONAL_ID" NOT NULL ENABLE);
 
  ALTER TABLE "LEITET" ADD PRIMARY KEY ("LEKTOR_LVA_ID") ENABLE;




--------------------------------------------------------
--  Ref Constraints for Table PERSONAL
--------------------------------------------------------

  ALTER TABLE "PERSONAL" ADD FOREIGN KEY ("FK_PERSON_ID")
    REFERENCES "PERSON" ("PERSON_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table PERSONENRANG
--------------------------------------------------------

  ALTER TABLE "PERSONENRANG" ADD FOREIGN KEY ("FK_PERSON_ID")
    REFERENCES "PERSON" ("PERSON_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "STUDENT" ADD FOREIGN KEY ("FK_PERSON_ID")
    REFERENCES "PERSON" ("PERSON_ID") ENABLE;
 
  ALTER TABLE "STUDENT" ADD FOREIGN KEY ("FK_STUDIENGANG_ID")
    REFERENCES "STUDIENGANG" ("STUDIENGANG_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table STUDIENGANG
--------------------------------------------------------

  ALTER TABLE "STUDIENGANG" ADD FOREIGN KEY ("FK_PERSONAL_ID")
    REFERENCES "PERSONAL" ("PERSONAL_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LVA
--------------------------------------------------------

  ALTER TABLE "LVA" ADD FOREIGN KEY ("FK_STUDIENGANG_ID")
    REFERENCES "STUDIENGANG" ("STUDIENGANG_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table LVA_EINHEIT
--------------------------------------------------------

  ALTER TABLE "LVA_EINHEIT" ADD FOREIGN KEY ("FK_LEKTOR_LVA_ID")
    REFERENCES "LEITET" ("LEKTOR_LVA_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table KREUZERL_LISTE
--------------------------------------------------------

  ALTER TABLE "KREUZERL_LISTE" ADD FOREIGN KEY ("FK_LVA_EINHEIT_ID")
    REFERENCES "LVA_EINHEIT" ("LVA_EINHEIT_ID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table KREUZERL_STUDENT
--------------------------------------------------------

  ALTER TABLE "KREUZERL_STUDENT" ADD FOREIGN KEY ("FK_LVA_EINHEIT_ID")
    REFERENCES "LVA_EINHEIT" ("LVA_EINHEIT_ID") ENABLE;

  ALTER TABLE "KREUZERL_STUDENT" ADD FOREIGN KEY ("FK_STUDENT_ID")
    REFERENCES "STUDENT" ("STUDENT_ID") ENABLE;
    

--------------------------------------------------------
--  Ref Constraints for Table LEITET
--------------------------------------------------------

  ALTER TABLE "LEITET" ADD FOREIGN KEY ("FK_LVA_ID")
    REFERENCES "LVA" ("LVA_ID") ENABLE;

  ALTER TABLE "LEITET" ADD FOREIGN KEY ("FK_PERSONAL_ID")
    REFERENCES "PERSONAL" ("PERSONAL_ID") ENABLE;
 
 

    
--------------------------------------------------------
--  Daten aus Fughafenbase kopieren  // manuelle eingabe von namen, nachname, etc
--------------------------------------------------------

--------------------------------------------------------
--  INSERTING into PERSON from flughafenbase
--------------------------------------------------------
--Insert into PERSON (SELECT * from flughafenbase.PERSON);




COMMIT;
