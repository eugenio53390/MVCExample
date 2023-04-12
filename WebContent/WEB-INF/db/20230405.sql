-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 05, 2023 alle 10:07
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Dump dei dati per la tabella `user`
-- password= 1234
ALTER TABLE `user` CHANGE `password` `password` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL;
INSERT INTO `user` ( `username`, `first_name`, `last_name`, `email`, `cellphone`, `birth_date`, `date_access`, `date_insert`, `password`, 'role') VALUES
( 'prova', 'utente', 'prova', 'prova@ciao.it', '1234', '2001-01-01', NULL, '2023-04-05 00:00:00', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Dirigenza');

