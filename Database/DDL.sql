/*Questo file contiene la Definizione del Database utilizzato per il progetto*/
CREATE DATABASE votoelettronico;

USE votoelettronico;

/*Definizione delle Tabelle*/

CREATE TABLE utente (
  	nome varchar(255) NOT NULL,
  	cognome varchar(255) NOT NULL,
  	username varchar(255) NOT NULL,
 	password varchar(255) NOT NULL,
  	ruolo enum('Elettore','Scrutinatore') DEFAULT 'Elettore',
	PRIMARY KEY (username)
);

CREATE TABLE candidato (
	id integer AUTO_INCREMENT NOT NULL,
	nome VARCHAR(255) NOT NULL,
	cognome VARCHAR(255) ,
	is_p BOOLEAN DEFAULT false,
	logo LONGBLOB ,
	idPartito integer,
	PRIMARY KEY (id),
	FOREIGN KEY (idPartito) REFERENCES candidato(id) ON DELETE CASCADE
);


CREATE TABLE sessione (
	id integer NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	modalita_voto enum('Ordinale','Categorico','Categorico-Preferenze','Referendum') NOT NULL,
	modalita_vittoria enum('Maggioranza','Maggioranza-Assoluta','Quorum','Senza-Quorum') NOT NULL,
	domanda VARCHAR (255),
	stato BOOLEAN NOT NULL,
	PRIMARY KEY (id)
);
/* Il candidato/partito partecipa all'elezione*/
CREATE TABLE partecipazione (
	idSessione integer NOT NULL,
	idCandidato integer NOT NULL,
	PRIMARY KEY(idSessione,idCandidato),
	FOREIGN KEY (idSessione) REFERENCES sessione(id) ON DELETE CASCADE,
	FOREIGN KEY (idCandidato) REFERENCES candidato(id) ON DELETE CASCADE

);
/*L'utente Vota nella sessione*/
CREATE TABLE votazione(
	idUtente varchar(255) NOT NULL,
	idSessione integer NOT NULL,
	PRIMARY KEY (idUtente,idSessione),
	FOREIGN KEY (idSessione) REFERENCES sessione(id) ON DELETE CASCADE,
	FOREIGN KEY (idUtente) REFERENCES utente(username) ON DELETE CASCADE
);

/*Voto Utente*/
CREATE TABLE voto (
	id integer NOT NULL AUTO_INCREMENT,
	idSessione integer NOT NULL ,
	ordine integer , 
	risposta BOOLEAN ,
	idCandidato integer ,	
	PRIMARY KEY (id),
	FOREIGN KEY (idSessione) REFERENCES sessione(id) ON DELETE CASCADE,
	FOREIGN KEY (idCandidato) REFERENCES candidato(id) ON DELETE CASCADE
	
);

