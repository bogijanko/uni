-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2020 at 04:22 PM
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
-- Database: `apoteka`
--

-- --------------------------------------------------------

--
-- Table structure for table `farmaceut`
--

CREATE TABLE `farmaceut` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `farmaceut`
--

INSERT INTO `farmaceut` (`id`, `username`, `password`) VALUES
(1, 'bogdan', 'bogdan'),
(2, 'ana1234', 'ana1234');

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `id` int(10) UNSIGNED NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `broj_knjizice` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `klijent`
--

INSERT INTO `klijent` (`id`, `ime`, `prezime`, `broj_knjizice`) VALUES
(1, 'Petar', 'Jovanovic', '1234567891234'),
(2, 'Mila', 'Kostic', '3332221114324'),
(3, 'Lena', 'Livadic', '2432675234906'),
(4, 'Lazar', 'Perić', '231990690751'),
(5, 'Predrag', 'Tošić', '1247542789543'),
(6, 'Miloš', 'Ilić', '1986537699909');

-- --------------------------------------------------------

--
-- Table structure for table `lek`
--

CREATE TABLE `lek` (
  `id` int(10) UNSIGNED NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `proizvodjac` varchar(50) NOT NULL,
  `jacina` int(5) NOT NULL,
  `cena` int(6) NOT NULL,
  `kolicina` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lek`
--

INSERT INTO `lek` (`id`, `naziv`, `proizvodjac`, `jacina`, `cena`, `kolicina`) VALUES
(1, 'Oligovit', 'Galenika', 200, 645, 22),
(2, 'Kardiopirin', 'Bayer', 5, 320, 39),
(3, 'Hemomicin', 'Hemofarm', 500, 870, 13),
(4, 'Aspirin', 'Bayer', 125, 285, 103),
(5, 'Monopril', 'PharmaSwiss', 10, 390, 32),
(6, 'Paracetamol', 'Hemofarm', 500, 280, 115);

-- --------------------------------------------------------

--
-- Table structure for table `porudzbina`
--

CREATE TABLE `porudzbina` (
  `id` int(10) UNSIGNED NOT NULL,
  `klijent_id` int(10) UNSIGNED NOT NULL,
  `stavka_porudzbine_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `porudzbina`
--

INSERT INTO `porudzbina` (`id`, `klijent_id`, `stavka_porudzbine_id`) VALUES
(1, 3, 24),
(2, 5, 27),
(3, 5, 30),
(4, 3, 32),
(5, 1, 33),
(6, 1, 35);

-- --------------------------------------------------------

--
-- Table structure for table `stavka_porudzbine`
--

CREATE TABLE `stavka_porudzbine` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_leka` int(10) UNSIGNED NOT NULL,
  `kolicina` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stavka_porudzbine`
--

INSERT INTO `stavka_porudzbine` (`id`, `id_leka`, `kolicina`) VALUES
(23, 5, 3),
(24, 4, 1),
(26, 3, 2),
(27, 4, 1),
(29, 5, 3),
(30, 4, 1),
(31, 3, 3),
(32, 2, 1),
(33, 4, 4),
(34, 3, 5),
(35, 5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `farmaceut`
--
ALTER TABLE `farmaceut`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lek`
--
ALTER TABLE `lek`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `porudzbina`
--
ALTER TABLE `porudzbina`
  ADD PRIMARY KEY (`id`),
  ADD KEY `klijent_id` (`klijent_id`),
  ADD KEY `stavka_porudzbine_id` (`stavka_porudzbine_id`);

--
-- Indexes for table `stavka_porudzbine`
--
ALTER TABLE `stavka_porudzbine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_leka` (`id_leka`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `farmaceut`
--
ALTER TABLE `farmaceut`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `klijent`
--
ALTER TABLE `klijent`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `lek`
--
ALTER TABLE `lek`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `porudzbina`
--
ALTER TABLE `porudzbina`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `stavka_porudzbine`
--
ALTER TABLE `stavka_porudzbine`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `porudzbina`
--
ALTER TABLE `porudzbina`
  ADD CONSTRAINT `porudzbina_ibfk_1` FOREIGN KEY (`klijent_id`) REFERENCES `klijent` (`id`),
  ADD CONSTRAINT `porudzbina_ibfk_2` FOREIGN KEY (`stavka_porudzbine_id`) REFERENCES `stavka_porudzbine` (`id`);

--
-- Constraints for table `stavka_porudzbine`
--
ALTER TABLE `stavka_porudzbine`
  ADD CONSTRAINT `stavka_porudzbine_ibfk_1` FOREIGN KEY (`id_leka`) REFERENCES `lek` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
