CREATE TABLE FahradKomponent(komponentId INTEGER PRIMARY KEY AUTOINCREMENT, fahradKomponentTyp varchar(100), fahradTyp varchar(100), fahradKlase varchar(100), name varchar(100),marke varchar(100), material varchar(100),farbe varchar(100), preis double,  gewicht double);
        
        
CREATE TABLE MainFahrad(faradId INTEGER PRIMARY KEY AUTOINCREMENT, nameFahrad varchar(100), gewicht double,  preis double);


CREATE TABLE komponentStatus  (
  komponentId INTEGER REFERENCES FahradKomponent,
  fahradId INTEGER REFERENCES MeinFahrad,
  PRIMARY KEY (komponentId, fahradId)
);