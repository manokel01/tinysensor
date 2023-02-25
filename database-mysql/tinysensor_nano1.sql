-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tinysensordb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tinysensordb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tinysensordb` DEFAULT CHARACTER SET utf8 ;
USE `tinysensordb` ;

-- -----------------------------------------------------
-- Table `tinysensordb`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `LASTNAME` VARCHAR(50) NOT NULL,
  `FIRSTNAME` VARCHAR(50) NOT NULL,
  `EMAIL` NVARCHAR(255) NOT NULL,
  `STREET` VARCHAR(50) NULL,
  `CITY` VARCHAR(50) NULL,
  `COUNTRY` VARCHAR(50) NULL,
  PRIMARY KEY (`ID`),
  INDEX `LASTNAME_IDX` (`LASTNAME` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DEVICES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `MODEL` VARCHAR(45) NOT NULL,
  `INPUTS` INT NULL,
  `CLOCKSPEED` INT NULL,
  `MEMORY` INT NULL,
  `CONNECTIVITY` VARCHAR(50) NULL,
  `DEVICES_ID` INT NOT NULL,
  INDEX `MODEL_IDX` (`MODEL` ASC) VISIBLE,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinysensordb`.`USERSDEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERSDEVICES` (
  `USERID` INT NOT NULL,
  `DEVICEID` INT NOT NULL,
  PRIMARY KEY (`USERID`, `DEVICEID`),
  INDEX `USER_FK_IDX` (`USERID` ASC) VISIBLE,
  INDEX `DEVICE_FK_idx` (`DEVICEID` ASC) VISIBLE,
  CONSTRAINT `USER_FK`
    FOREIGN KEY (`USERID`)
    REFERENCES `tinysensordb`.`USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `DEVICE_FK`
    FOREIGN KEY (`DEVICEID`)
    REFERENCES `tinysensordb`.`DEVICES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DATASET_1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DATASET_1` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DEVICES_ID` INT NOT NULL,
  `TEMPERATURE` FLOAT NULL,
  `HUMIDITY` FLOAT NULL,
  `LIGHT` INT NULL,
  `RED` INT NULL,
  `GREEN` INT NULL,
  `BLUE` INT NULL,
  `DEGREESX` INT NULL,
  `DEGREESY` INT NULL,
  `TIME` INT NULL,
  `DATASIZE` VARBINARY(256) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_DATASET_1_DEVICES1_idx` (`DEVICES_ID` ASC) VISIBLE,
  CONSTRAINT `fk_DATASET_1_DEVICES1`
    FOREIGN KEY (`DEVICES_ID`)
    REFERENCES `tinysensordb`.`DEVICES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP USER 'manokel';

CREATE USER 'manokel' IDENTIFIED BY '$0t1r10S@@';

GRANT ALL ON `tinysensordb`.* TO 'manokel';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
