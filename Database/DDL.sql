/*Questo file contiene la Definizione del Database utilizzato per il progetto*/
CREATE DATABASE votoelettronico;

USE votoelettronico;

/*Definizione delle Tabelle*/

CREATE TABLE `utente` (
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` enum('Elettore','Scrutinatore') DEFAULT 'Elettore',
  PRIMARY KEY (`username`)
);
