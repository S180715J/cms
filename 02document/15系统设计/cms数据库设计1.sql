/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.51 : Database - cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `cms`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `subhead` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `mainbody` text COLLATE utf8_bin,
  `accessoryname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `accessory` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `creattime` datetime DEFAULT NULL,
  `eid` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0为待审批，1为已发布',
  `stick` int(11) DEFAULT '0' COMMENT '0为不置顶，数值越大置顶等级越高',
  `author` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `aboutimg` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_article` */

insert  into `t_article`(`aid`,`title`,`subhead`,`mainbody`,`accessoryname`,`accessory`,`creattime`,`eid`,`status`,`stick`,`author`,`aboutimg`,`cid`) values (1,'logo','logo','','logo.png','','2019-03-27 15:56:03',NULL,1,100,'a','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\9c15205154ee4989b6b4df874f30a293.jpg',11),(6,'欢迎','欢迎','<h5>欢迎您的到来</h5><h3>好礼品我们造,用心做好产品</h3><h4>We build good products and make good products.</h4><p><br/></p>','1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\aad430f0f1fe499dab4fbe6d37ff1878.txt','2019-03-27 16:47:59',NULL,1,2,'1','',11),(7,'食品包装1','食品包装1','<h3>食品包装1</h3>','食品包装1','','2019-03-27 17:28:47',NULL,1,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\ec2cacd95bfc409bbea56b6a0fc19a0c.jpg',11),(8,'食品包装2','食品包装2','<h3>食品包装2</h3>','食品包装2','','2019-03-27 17:29:46',NULL,1,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\a45a6565703f4fe4ac15b74e99315de7.jpg',11),(9,'食品包装3','食品包装3','<h3>食品包装3</h3>','食品包装3','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\e8e04ea61413417eb99e8934e43510d0.jpg','2019-03-27 17:30:26',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\8b8b92f25f6a4365b6946cdfa331e705.jpg',11),(10,'食品包装4','食品包装4','<h3>食品包装4</h3>','食品包装4','','2019-03-27 17:30:57',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\746622815295438a84415a57d56eb185.jpg',11),(11,'优秀包装设计美学','优秀包装设计美学','<p>优秀包装设计美学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br/></p><p>本公司专门为您打造定制的商品包装。本公司专门为您打造定制的商品包装。</p><p><br/></p><p><br/></p><p><br/></p>','优秀包装设计美学','','2019-03-27 17:47:51',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\888916b671174855a8bb29d21a2ba4f4.jpg',11),(12,'优秀包装设计美学2','优秀包装设计美学2','','2','','2019-03-27 17:58:05',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\1132f91b98374d1ea792e1fd53178fa6.jpg',11),(13,'上等材质制作包装','上等材质制作包装','<p>上等材质制作包装<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 本公司专门为您打造定制的商品包装。本公司专门为您打造定制的商品包装。</p><p><br/></p>','上等材质制作包装','','2019-03-27 18:09:36',NULL,0,0,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\20d78707dfa84944a01ef57d5ef26cc3.jpg',11),(14,'上等材质制作包装2','上等材质制作包装2','','上等材质制作包装2','','2019-03-27 18:09:38',NULL,0,0,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\3c6a0ff3b0ba49139d0f955d634f9c31.jpg',11),(15,'高级设备流水制作','高级设备流水制作','<p>高级设备流水制作<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 本公司专门为您打造定制的商品包装。本公司专门为您打造定制的商品包装。</p><p><br/></p>','高级设备流水制作','','2019-03-27 18:06:40',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\f2a215df639f45c2a77ac37d0033764b.jpg',11),(16,'高级设备流水制作2','高级设备流水制作2','','高级设备流水制作2','','2019-03-27 18:08:20',NULL,0,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\cef95e9ff7e54595b15c6d54d63c2ace.jpg',11),(101,'背景1','背景1','','1','','2019-03-27 16:59:31',NULL,1,1,'2','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\c9ab2b0091694742920ba4d8f0a00855.jpg',11),(102,'背景2','背景2','','1','','2019-03-27 17:00:05',NULL,1,1,'1','D:\\git_s180715j\\cms3\\01code\\cms\\src\\main\\webapp\\cmshtml\\access\\2019-03\\68751cb60c8e44f8a3410b7a37c10108.jpg',11);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
