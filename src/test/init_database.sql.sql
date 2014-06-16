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
    SECTION TEXT,
    COMPTE_ANALYTIQUE VARCHAR(4),
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
    I_DEVISE TEXT,
    AFFECTATION TEXT
);


INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "MARGE", "4000", "Salaires");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "MARGE",  "2010", "Sous-traitance Groupe");

INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "MARGE", "4500", "Charges Sociales" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "MARGE",  "4800", "Autres Charges/Salaires");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "MARGE", "4900", "Honoraires vs Salaires" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FRAIS GENERAUX","3000", "Services Extérieurs" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FRAIS GENERAUX","3010", "Services extérieurs Groupe" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FRAIS GENERAUX", "3100","Frais de Mission" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FRAIS GENERAUX", "3300","Impôts" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FRAIS GENERAUX","4600", "Salaire gérance" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "AUTRES CHARGES","5000", "Autres Charges" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "FINANCIERS", "6600","Financiers" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "EXCEPTIONNEEL", "6700","Exceptionnel" );
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "DOTATIONS", "6800","Dotations");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "REPARTITIONS",  "6900","Répartition");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", "REPARTITIONS",  "9000","Résultat");
INSERT INTO STRUCTURE_ANALYTIQUE (ALIAS_STRUCTURE, SECTION, COMPTE_ANALYTIQUE, LIBELLE) VALUES ("Default Model", NULL,  NULL,"Calcul : $t$ - Expression : \"$t2$\"");

INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Thalès", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Bull", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("HP", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("Google", "Aucune", "Default Model");
INSERT INTO ENTREPRISE (NAME_ENTREPRISE, DESCRIPTION_ENTREPRISE, STRUCTURE_ANALYTIQUE) VALUES ("EDF", "Aucune", "Default Model");
