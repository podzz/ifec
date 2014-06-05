DROP TABLE IF EXISTS ENTREPRISE;
DROP TABLE IF EXISTS EXERCICE;
DROP TABLE IF EXISTS STRUCTURE_ANALYTIQUE;
DROP TABLE IF EXISTS FEC;

CREATE TABLE ENTREPRISE
(
    ID_ENTREPRISE SERIAL primary key,
    NAME_ENTREPRISE VARCHAR(80),
    DESCRIPTION_ENTREPRISE TEXT,
    STRUCTURE_ANALYTIQUE VARCHAR(80)
);

CREATE TABLE EXERCICE
(
    ID_EXERCICE SERIAL primary key,
    ENTREPRISE INT,
    FEC INT,
    DATE_BEGIN DATE,
    DATE_END DATE
);

CREATE TABLE STRUCTURE_ANALYTIQUE
(
    ID_STRUCTURE SERIAL primary key,
    ALIAS_STRUCTURE VARCHAR(80),
    SECTION VARCHAR(4),
    COMPTE_ANALYTIQUE TEXT,
    LIBELLE TEXT
);

CREATE TABLE FEC
(
    ID_FEC SERIAL primary key,
    FEC INT,
    JOURNAL_CODE VARCHAR(3),
    JOURNAL_LIB TEXT,
    ECRITURE_NUM INT,
    ECRITURE_DATE DATE,
    COMPTE_NUM VARCHAR(6),
    COMPTE_LIB TEXT,
    COMPTE_AUX_NUM TEXT,
    COMPTE_AUX_LIB TEXT,
    PIECE_REF VARCHAR(3),
    PIECE_DATE DATE,
    ECRITURE_LIB TEXT,
    MONTANT FLOAT,
    SENS CHAR,
    ECRITURE_LET TEXT,
    DATE_LET DATE,
    VALID_DATE DATE,
    MONTANT_DEVISE FLOAT,
    I_DEVISE TEXT
);



INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "2010", "Sous-traitance Groupe", "1-MARGE");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "4000", "Salaires", "1-MARGE");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "4500", "Charges Sociales", "1-MARGE");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "4800", "Autres Charges/Salaires", "1-MARGE");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "4900", "Honoraires vs Salaires", "1-MARGE");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "3000", "Services Extérieurs", "3-FRAIS GENERAUX");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "3010", "Services extérieurs Groupe", "3-FRAIS GENERAUX");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "3100", "Frais de Mission", "3-FRAIS GENERAUX");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "3300", "Impôts", "3-FRAIS GENERAUX");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "4600", "Salaire gérance", "3-FRAIS GENERAUX");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "5000", "Autres Charges", "5-AUTRES CHARGES");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "6600", "Financiers", "6-FINANCIERS");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "6700", "Exceptionnel", "7-EXCEPTIONNEL");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "6800", "Dotations", "8-DOTATIONS");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "6900", "Répartition", "9-REPARTITION");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "9000", "Résultat", "9-REPARTITION");

INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Thalès", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Bull", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("HP", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Google", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("EDF", "Aucune", "Default Model");
