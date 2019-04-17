/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.25 : Database - house
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`house` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `house`;

/*Table structure for table `houselist` */

DROP TABLE IF EXISTS `houselist`;

CREATE TABLE `houselist` (
  `houseid` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `detail` varchar(2048) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `userlist_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`houseid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `houselist` */

insert  into `houselist`(`houseid`,`address`,`price`,`status`,`detail`,`userlist_id`,`userlist_name`) values (17,'深圳市盐田区沙头角856号4楼',1300.00,'已出租','1、不能使用灶火。\n2、基本家电齐全，拎包入住。',31,'何小琳'),(20,'深圳市盐田区大梅沙66号1楼',900.00,'已出租','无',32,'王大锤'),(21,'深圳市盐田区大梅沙66号3楼',800.00,'已出租','无',29,'周晓二'),(23,'深圳市龙华新区民塘路绿景公馆1699（南区）',2000.00,'未出租','无',NULL,NULL),(26,'深圳市盐田区大梅沙96号1楼',900.00,'未出租','家电齐全，拎包入住。',NULL,NULL),(27,'深圳市盐田区大梅沙909号3楼',1800.00,'未出租','无',NULL,NULL);

/*Table structure for table `paid` */

DROP TABLE IF EXISTS `paid`;

CREATE TABLE `paid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `paydate` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `houseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `paid` */

insert  into `paid`(`id`,`address`,`price`,`date`,`paydate`,`name`,`userlist_id`,`status`,`houseid`) values (30,'深圳市盐田区沙头角856号4楼',1300.00,'2019-04-01',NULL,'何小琳',31,'未缴',17),(31,'深圳市盐田区大梅沙66号1楼',900.00,'2019-04-01',NULL,'王大锤',32,'未缴',20),(32,'深圳市盐田区大梅沙66号3楼',800.00,'2019-04-01',NULL,'周晓二',29,'未缴',21);

/*Table structure for table `schedule` */

DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` int(11) DEFAULT NULL COMMENT '公告持续天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `schedule` */

insert  into `schedule`(`id`,`date`,`content`,`time`) values (6,'2019-04-01','注意及时缴纳本月房租',7),(8,'2019-04-15','电梯停止使用一周，敬请见谅。',7),(9,'2019-04-15','本月还未缴纳月租的租客请注意及时缴纳月租。',7);

/*Table structure for table `solve` */

DROP TABLE IF EXISTS `solve`;

CREATE TABLE `solve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userlist_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `houseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `solve` */

insert  into `solve`(`id`,`address`,`date`,`detail`,`name`,`userlist_id`,`status`,`houseid`) values (11,'深圳市盐田区沙头角856号4楼','2019-04-16','房门门栓损坏。','何小琳',31,'未处理',17);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1为管理员，2为普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`type`) values (1,'admin','88888888',1),(19,'13192664967','13192664967',2),(21,'13192665859','13192665859',2),(22,'13356998564','13356998564',2);

/*Table structure for table `userlist` */

DROP TABLE IF EXISTS `userlist`;

CREATE TABLE `userlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `userlist` */

insert  into `userlist`(`id`,`name`,`idcard`,`phone`,`userid`) values (27,'李老铁','440582199712267459','13199772826',1),(29,'周晓二','445215966585236985','13192664967',19),(31,'何小琳','440582199512137569','13192665859',21),(32,'王大锤','440569199514285698','13356998564',22);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
