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
-- Table `tinysensordb`.`DATASETS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DATASETS` (
  `DATASETID` INT NOT NULL AUTO_INCREMENT,
  `TIMESTAMP` VARCHAR(50) NULL DEFAULT NULL,
  `SENSOR1` FLOAT NULL DEFAULT NULL,
  `SENSOR2` FLOAT NULL DEFAULT NULL,
  `SENSOR3` FLOAT NULL DEFAULT NULL,
  `SENSOR4` FLOAT NULL DEFAULT NULL,
  `SENSOR5` FLOAT NULL DEFAULT NULL,
  `SENSOR6` FLOAT NULL DEFAULT NULL,
  `RUNTIME` INT NULL DEFAULT NULL,
  `BATTERY` INT NULL DEFAULT NULL,
  PRIMARY KEY (`DATASETID`),
  UNIQUE INDEX `DATASETID_UNIQUE` (`DATASETID` ASC) VISIBLE,
  INDEX `MODEL_IDX` (`TIMESTAMP` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DBACCOUNTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DBACCOUNTS` (
  `ACCOUNTID` INT NOT NULL,
  `USERNAME` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ACCOUNTID`),
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) VISIBLE,
  UNIQUE INDEX `ACCOUNTID_UNIQUE` (`ACCOUNTID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DEVICES` (
  `DEVICEID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NULL DEFAULT NULL,
  `INPUTS` INT NULL DEFAULT NULL,
  PRIMARY KEY (`DEVICEID`),
  INDEX `NAME_IDX` (`NAME` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DEVICESDATASETS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DEVICESDATASETS` (
  `DEVICEID` INT NOT NULL,
  `DATASETID` INT NOT NULL,
  PRIMARY KEY (`DEVICEID`, `DATASETID`),
  UNIQUE INDEX `DEVICEID_UNIQUE` (`DEVICEID` ASC) VISIBLE,
  UNIQUE INDEX `DATASETID_UNIQUE` (`DATASETID` ASC) VISIBLE,
  CONSTRAINT `DATASETID_FK1`
    FOREIGN KEY (`DATASETID`)
    REFERENCES `tinysensordb`.`DATASETS` (`DATASETID`),
  CONSTRAINT `DEVICEID_FK1`
    FOREIGN KEY (`DEVICEID`)
    REFERENCES `tinysensordb`.`DEVICES` (`DEVICEID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tinysensordb`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERS` (
  `USERID` INT NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `LASTNAME` VARCHAR(50) NULL DEFAULT NULL,
  `FIRSTNAME` VARCHAR(50) NULL DEFAULT NULL,
  `POSTCODE` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`USERID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  UNIQUE INDEX `USERID_UNIQUE` (`USERID` ASC) VISIBLE,
  INDEX `LASTNAME_IDX` (`LASTNAME` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tinysensordb`.`USERSDEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERSDEVICES` (
  `USERID` INT NOT NULL,
  `DEVICEID` INT NOT NULL,
  PRIMARY KEY (`USERID`, `DEVICEID`),
  INDEX `DEVICEID_idx` (`DEVICEID` ASC) VISIBLE,
  INDEX `USERID_IDX` (`USERID` ASC) VISIBLE,
  CONSTRAINT `DEVICEID_FK`
    FOREIGN KEY (`DEVICEID`)
    REFERENCES `tinysensordb`.`DEVICES` (`DEVICEID`),
  CONSTRAINT `USERID_FK`
    FOREIGN KEY (`USERID`)
    REFERENCES `tinysensordb`.`USERS` (`USERID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE USER 'manokel' IDENTIFIED BY 'aa141075';

GRANT ALL ON `tinysensordb`.* TO 'manokel';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
