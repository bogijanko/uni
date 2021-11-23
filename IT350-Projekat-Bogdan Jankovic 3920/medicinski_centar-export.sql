-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2021 at 10:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medicinski centar`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosijepacijenta`
--

CREATE TABLE `dosijepacijenta` (
  `iddosijea` int(11) NOT NULL,
  `idpregleda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dosijepacijenta`
--

INSERT INTO `dosijepacijenta` (`iddosijea`, `idpregleda`) VALUES
(2, NULL),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `klinika`
--

CREATE TABLE `klinika` (
  `idklinike` int(11) NOT NULL,
  `nazivklinike` char(100) DEFAULT NULL,
  `mesto` char(50) NOT NULL,
  `brojtelefona` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `klinika`
--

INSERT INTO `klinika` (`idklinike`, `nazivklinike`, `mesto`, `brojtelefona`) VALUES
(7, 'Sveti vid', 'Beograd', '0112351322'),
(9, 'Sveti vid', 'Beograd', '0112351322'),
(22, 'Remedia', 'Nis', '018234234'),
(23, 'Sveti vid', 'Beograd', '0112351322');

-- --------------------------------------------------------

--
-- Table structure for table `lek`
--

CREATE TABLE `lek` (
  `idleka` int(11) NOT NULL,
  `naziv` char(100) NOT NULL,
  `proizvodjac` char(100) NOT NULL,
  `cena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lek`
--

INSERT INTO `lek` (`idleka`, `naziv`, `proizvodjac`, `cena`) VALUES
(1, 'Aspirin', 'Bayer', 195),
(2, 'Hemomycin', 'Hemofarm', 545),
(3, 'Klometol', 'Galenika', 310);

-- --------------------------------------------------------

--
-- Table structure for table `lekar`
--

CREATE TABLE `lekar` (
  `idlekara` int(11) NOT NULL,
  `imelekara` char(20) NOT NULL,
  `prezimelekara` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lekar`
--

INSERT INTO `lekar` (`idlekara`, `imelekara`, `prezimelekara`) VALUES
(2, 'Marko', 'Kamenov'),
(9, 'Petra', 'Ilic'),
(10, 'Aleksandar', 'Petrovic');

-- --------------------------------------------------------

--
-- Table structure for table `lekaropsteprakse`
--

CREATE TABLE `lekaropsteprakse` (
  `idlekara` int(11) NOT NULL,
  `imelekara` char(20) NOT NULL,
  `prezimelekara` char(20) NOT NULL,
  `telefon` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lekaropsteprakse`
--

INSERT INTO `lekaropsteprakse` (`idlekara`, `imelekara`, `prezimelekara`, `telefon`) VALUES
(9, 'Petra', 'Ilic', '0612351289');

-- --------------------------------------------------------

--
-- Table structure for table `lekarspecijalista`
--

CREATE TABLE `lekarspecijalista` (
  `idlekara` int(11) NOT NULL,
  `imelekara` char(20) NOT NULL,
  `prezimelekara` char(20) NOT NULL,
  `specijalnost` char(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lekarspecijalista`
--

INSERT INTO `lekarspecijalista` (`idlekara`, `imelekara`, `prezimelekara`, `specijalnost`) VALUES
(2, 'Marko', 'Kamenov', 'Ortopedija'),
(10, 'Aleksandar', 'Petrovic', 'Onkologija');

-- --------------------------------------------------------

--
-- Table structure for table `pacijent`
--

CREATE TABLE `pacijent` (
  `idpacijenta` int(11) NOT NULL,
  `iddosijea` int(11) NOT NULL,
  `idlekara` int(11) NOT NULL,
  `ime` char(50) NOT NULL,
  `adresa` char(100) DEFAULT NULL,
  `kontakttelefon` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pacijent`
--

INSERT INTO `pacijent` (`idpacijenta`, `iddosijea`, `idlekara`, `ime`, `adresa`, `kontakttelefon`) VALUES
(1, 1, 9, 'Sofija Pavlovic', 'Kosovke devojke 22', '018251232'),
(2, 2, 9, 'Lena Tosic', 'Milesevska 34', '062380379');

-- --------------------------------------------------------

--
-- Table structure for table `pregled`
--

CREATE TABLE `pregled` (
  `idpregleda` int(11) NOT NULL,
  `idklinike` int(11) DEFAULT NULL,
  `idpacijenta` int(11) DEFAULT NULL,
  `idlekara` int(11) DEFAULT NULL,
  `idzakazivanja` int(11) DEFAULT NULL,
  `nalaz_opis` text NOT NULL,
  `dijagnoza` text NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pregled`
--

INSERT INTO `pregled` (`idpregleda`, `idklinike`, `idpacijenta`, `idlekara`, `idzakazivanja`, `nalaz_opis`, `dijagnoza`, `datum`, `vreme`) VALUES
(3, 7, 1, 2, 1, 'Dioprtija levog oka', '+0.5', '2021-01-11', '09:33:00'),
(4, 22, 2, 10, 3, 'Onkoloski pregled pluca', 'Plucna embolija', '2021-01-20', '17:10:00');

-- --------------------------------------------------------

--
-- Stand-in structure for view `pregledpacijenataidijagnoza`
-- (See below for the actual view)
--
CREATE TABLE `pregledpacijenataidijagnoza` (
`IDPACIJENTA` int(11)
,`IME` char(50)
,`imelekara` char(20)
,`broj_dijagnoza` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `radi`
--

CREATE TABLE `radi` (
  `idlekara` int(11) NOT NULL,
  `idklinike` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `radi`
--

INSERT INTO `radi` (`idlekara`, `idklinike`) VALUES
(2, 7),
(2, 9),
(10, 9);

-- --------------------------------------------------------

--
-- Table structure for table `recept`
--

CREATE TABLE `recept` (
  `sifrarecepta` int(11) NOT NULL,
  `idleka` int(11) NOT NULL,
  `idpregleda` int(11) NOT NULL,
  `nazivleka` char(100) NOT NULL,
  `nacinupotrebe` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recept`
--

INSERT INTO `recept` (`sifrarecepta`, `idleka`, `idpregleda`, `nazivleka`, `nacinupotrebe`) VALUES
(211, 2, 4, 'Hemomycin', 'jedna tableta na 8h'),
(212, 2, 4, 'Hemomycin', 'jedna tableta na 8h');

-- --------------------------------------------------------

--
-- Table structure for table `zakazivanje`
--

CREATE TABLE `zakazivanje` (
  `idzakazivanja` int(11) NOT NULL,
  `idklinike` int(11) NOT NULL,
  `idpacijenta` int(11) NOT NULL,
  `idlekara` int(11) NOT NULL,
  `datumzakazivanja` date NOT NULL,
  `vremezakazivanja` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `zakazivanje`
--

INSERT INTO `zakazivanje` (`idzakazivanja`, `idklinike`, `idpacijenta`, `idlekara`, `datumzakazivanja`, `vremezakazivanja`) VALUES
(1, 7, 1, 2, '2021-01-06', '17:30:00'),
(2, 22, 1, 2, '2021-01-14', '14:00:00'),
(3, 22, 2, 10, '2021-01-17', '10:00:00');

-- --------------------------------------------------------

--
-- Structure for view `pregledpacijenataidijagnoza`
--
DROP TABLE IF EXISTS `pregledpacijenataidijagnoza`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pregledpacijenataidijagnoza`  AS  select `pac`.`idpacijenta` AS `IDPACIJENTA`,`pac`.`ime` AS `IME`,`l`.`imelekara` AS `imelekara`,count(0) AS `broj_dijagnoza` from ((`lekar` `l` join `pacijent` `pac` on(`l`.`idlekara` = `pac`.`idlekara`)) join `pregled` `pre` on(`pac`.`idpacijenta` = `pre`.`idpacijenta`)) group by `pre`.`dijagnoza` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosijepacijenta`
--
ALTER TABLE `dosijepacijenta`
  ADD PRIMARY KEY (`iddosijea`),
  ADD UNIQUE KEY `iddosijea` (`iddosijea`),
  ADD KEY `fk_upisivanje` (`idpregleda`);

--
-- Indexes for table `klinika`
--
ALTER TABLE `klinika`
  ADD PRIMARY KEY (`idklinike`),
  ADD UNIQUE KEY `idklinike` (`idklinike`);

--
-- Indexes for table `lek`
--
ALTER TABLE `lek`
  ADD PRIMARY KEY (`idleka`),
  ADD UNIQUE KEY `idleka` (`idleka`);

--
-- Indexes for table `lekar`
--
ALTER TABLE `lekar`
  ADD PRIMARY KEY (`idlekara`),
  ADD UNIQUE KEY `idlekara` (`idlekara`);

--
-- Indexes for table `lekaropsteprakse`
--
ALTER TABLE `lekaropsteprakse`
  ADD PRIMARY KEY (`idlekara`),
  ADD UNIQUE KEY `idlekara` (`idlekara`);

--
-- Indexes for table `lekarspecijalista`
--
ALTER TABLE `lekarspecijalista`
  ADD PRIMARY KEY (`idlekara`),
  ADD UNIQUE KEY `idlekara` (`idlekara`);

--
-- Indexes for table `pacijent`
--
ALTER TABLE `pacijent`
  ADD PRIMARY KEY (`idpacijenta`),
  ADD UNIQUE KEY `idpacijenta` (`idpacijenta`),
  ADD KEY `fk_istorija` (`iddosijea`),
  ADD KEY `fk_prati` (`idlekara`);

--
-- Indexes for table `pregled`
--
ALTER TABLE `pregled`
  ADD PRIMARY KEY (`idpregleda`),
  ADD UNIQUE KEY `idpregleda` (`idpregleda`),
  ADD KEY `fk_mestopregleda` (`idklinike`),
  ADD KEY `fk_pregled` (`idpacijenta`),
  ADD KEY `fk_pregledalekar` (`idlekara`),
  ADD KEY `fk_zakazivanjepregleda` (`idzakazivanja`);

--
-- Indexes for table `radi`
--
ALTER TABLE `radi`
  ADD PRIMARY KEY (`idlekara`,`idklinike`),
  ADD KEY `fk_radi` (`idklinike`);

--
-- Indexes for table `recept`
--
ALTER TABLE `recept`
  ADD PRIMARY KEY (`sifrarecepta`),
  ADD UNIQUE KEY `sifrarecepta` (`sifrarecepta`),
  ADD KEY `fk_izdati` (`idpregleda`),
  ADD KEY `fk_prepisan` (`idleka`);

--
-- Indexes for table `zakazivanje`
--
ALTER TABLE `zakazivanje`
  ADD PRIMARY KEY (`idzakazivanja`),
  ADD UNIQUE KEY `idzakazivanja` (`idzakazivanja`),
  ADD KEY `fk_gde` (`idklinike`),
  ADD KEY `fk_kod` (`idlekara`),
  ADD KEY `fk_zakazuje` (`idpacijenta`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dosijepacijenta`
--
ALTER TABLE `dosijepacijenta`
  MODIFY `iddosijea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `klinika`
--
ALTER TABLE `klinika`
  MODIFY `idklinike` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `lek`
--
ALTER TABLE `lek`
  MODIFY `idleka` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `lekar`
--
ALTER TABLE `lekar`
  MODIFY `idlekara` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `pacijent`
--
ALTER TABLE `pacijent`
  MODIFY `idpacijenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pregled`
--
ALTER TABLE `pregled`
  MODIFY `idpregleda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `zakazivanje`
--
ALTER TABLE `zakazivanje`
  MODIFY `idzakazivanja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dosijepacijenta`
--
ALTER TABLE `dosijepacijenta`
  ADD CONSTRAINT `fk_upisivanje` FOREIGN KEY (`idpregleda`) REFERENCES `pregled` (`idpregleda`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `lekaropsteprakse`
--
ALTER TABLE `lekaropsteprakse`
  ADD CONSTRAINT `fk_specijalizacija` FOREIGN KEY (`idlekara`) REFERENCES `lekar` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `lekarspecijalista`
--
ALTER TABLE `lekarspecijalista`
  ADD CONSTRAINT `fk_specijalizacija2` FOREIGN KEY (`idlekara`) REFERENCES `lekar` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pacijent`
--
ALTER TABLE `pacijent`
  ADD CONSTRAINT `fk_istorija` FOREIGN KEY (`iddosijea`) REFERENCES `dosijepacijenta` (`iddosijea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_prati` FOREIGN KEY (`idlekara`) REFERENCES `lekaropsteprakse` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pregled`
--
ALTER TABLE `pregled`
  ADD CONSTRAINT `fk_mestopregleda` FOREIGN KEY (`idklinike`) REFERENCES `klinika` (`idklinike`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pregled` FOREIGN KEY (`idpacijenta`) REFERENCES `pacijent` (`idpacijenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pregledalekar` FOREIGN KEY (`idlekara`) REFERENCES `lekarspecijalista` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zakazivanjepregleda` FOREIGN KEY (`idzakazivanja`) REFERENCES `zakazivanje` (`idzakazivanja`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `radi`
--
ALTER TABLE `radi`
  ADD CONSTRAINT `fk_radi` FOREIGN KEY (`idklinike`) REFERENCES `klinika` (`idklinike`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_radi2` FOREIGN KEY (`idlekara`) REFERENCES `lekarspecijalista` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `recept`
--
ALTER TABLE `recept`
  ADD CONSTRAINT `fk_izdati` FOREIGN KEY (`idpregleda`) REFERENCES `pregled` (`idpregleda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_prepisan` FOREIGN KEY (`idleka`) REFERENCES `lek` (`idleka`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zakazivanje`
--
ALTER TABLE `zakazivanje`
  ADD CONSTRAINT `fk_gde` FOREIGN KEY (`idklinike`) REFERENCES `klinika` (`idklinike`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_kod` FOREIGN KEY (`idlekara`) REFERENCES `lekarspecijalista` (`idlekara`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zakazuje` FOREIGN KEY (`idpacijenta`) REFERENCES `pacijent` (`idpacijenta`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
