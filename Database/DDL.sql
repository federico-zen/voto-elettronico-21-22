CREATE TABLE `utente` (
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` enum('Elettore','Scrutinatore') DEFAULT 'Elettore',
  PRIMARY KEY (`username`)
);
