-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2017 at 07:52 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `melony`
--

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `name` varchar(15) NOT NULL,
  `lat` float(13,6) NOT NULL,
  `lng` float(13,6) NOT NULL,
  `description` text NOT NULL,
  `image1` varchar(230) NOT NULL,
  `image2` varchar(230) NOT NULL,
  `image3` varchar(230) NOT NULL,
  `image4` varchar(230) NOT NULL,
  `size` varchar(10) NOT NULL,
  `special` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploads`
--

INSERT INTO `uploads` (`name`, `lat`, `lng`, `description`, `image1`, `image2`, `image3`, `image4`, `size`, `special`) VALUES
('', 42.006069, 23.103941, '', 'uploads/20170618120340145600.jpeg', '', '', '', 'Small', 'grapes, '),
('jaja', 42.003258, 23.103949, '', 'uploads/20170618125637481800.jpeg', '', '', '', 'Small', 'grapes, '),
('Name', 42.006069, 23.103941, '', 'uploads/20170618120324222800.jpeg', 'uploads/20170618120339472500.jpeg', '', '', 'Small', 'grapes, ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `uploads`
--
ALTER TABLE `uploads`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `image` (`image1`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
