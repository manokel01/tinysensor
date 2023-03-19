-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tsdb22db
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tinysensordb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tinysensordb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tinysensordb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `tinysensordb` ;

-- -----------------------------------------------------
-- Table `tinysensordb`.`DEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DEVICES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL COMMENT 'NAME may contain device model type and location details ',
  `MAC` VARCHAR(48) NOT NULL COMMENT 'MAC address of device. In case of Bluetooth it is the Bluetooth MAC address (BD_ADDR).',
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `MAC_UNIQUE` (`MAC` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DATASETS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DATASETS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DATA_1` DOUBLE NULL DEFAULT NULL COMMENT 'Data from sensor 1.\\nSame for all other DATA_#',
  `DATA_2` DOUBLE NULL DEFAULT NULL,
  `DATA_3` DOUBLE NULL DEFAULT NULL,
  `DATA_4` DOUBLE NULL DEFAULT NULL,
  `DATA_5` DOUBLE NULL DEFAULT NULL,
  `DATA_6` DOUBLE NULL DEFAULT NULL,
  `BATTERY` INT NULL DEFAULT NULL COMMENT 'Battery indicator (values 0 - 100)',
  `RUNTIME` INT NULL DEFAULT NULL COMMENT 'Device runtime in milliseconds',
  `TIMESTAMP` VARCHAR(24) NULL DEFAULT NULL COMMENT 'Timestamp is IDO 8601 format (2018-12-10T13:49:51.141Z)',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 22895
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DEVICE_DATASET`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DEVICE_DATASET` (
  `DEVICE_ID` INT NOT NULL,
  `DATASET_ID` INT NOT NULL,
  `LOCATION` VARCHAR(100) NOT NULL COMMENT 'LOCATION may be public IP + MAC or description',
  PRIMARY KEY (`DATASET_ID`, `DEVICE_ID`),
  INDEX `DEVICE_FK1` (`DEVICE_ID` ASC) VISIBLE,
  CONSTRAINT `DATASET_FK1`
    FOREIGN KEY (`DATASET_ID`)
    REFERENCES `tinysensordb`.`DATASETS` (`ID`),
  CONSTRAINT `DEVICE_FK1`
    FOREIGN KEY (`DEVICE_ID`)
    REFERENCES `tinysensordb`.`DEVICES` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tinysensordb`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERS` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT 'Surrogate key',
  `FIRSTNAME` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User’s firstname.',
  `LASTNAME` VARCHAR(255) NOT NULL COMMENT 'User’s lastname.',
  `EMAIL` VARCHAR(255) NOT NULL COMMENT 'User’s email.',
  `ADDRESS` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User’s postal address.',
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  INDEX `LASTNAME_IDX` (`LASTNAME` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tinysensordb`.`USERS_DEVICES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`USERS_DEVICES` (
  `USER_ID` INT NOT NULL,
  `DEVICE_ID` INT NOT NULL,
  PRIMARY KEY (`USER_ID`, `DEVICE_ID`),
  INDEX `DEVICE_FK_idx` (`DEVICE_ID` ASC) VISIBLE,
  CONSTRAINT `DEVICE_FK`
    FOREIGN KEY (`DEVICE_ID`)
    REFERENCES `tinysensordb`.`DEVICES` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `USER_FK`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `tinysensordb`.`USERS` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tinysensordb`.`DBUSERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tinysensordb`.`DBUSERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(50) NOT NULL COMMENT 'The database user’s username for login.',
  `password` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE USER 'tsdbuser' IDENTIFIED BY '123456';

GRANT ALL ON `tsdb22db`.* TO 'tsdbuser';
GRANT ALL ON `tinysensordb`.* TO 'tsdbuser';
CREATE USER 'user2' IDENTIFIED BY '123456';

GRANT ALL ON `tsdb22db`.* TO 'user2';
GRANT ALL ON `tinysensordb`.* TO 'user2';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
