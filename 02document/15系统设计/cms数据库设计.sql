/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.17 : Database - cms
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
  `accessory` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `creattime` datetime DEFAULT NULL,
  `eid` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0为待审批，1为已发布',
  `stick` int(11) DEFAULT '0' COMMENT '0为不置顶，数值越大置顶等级越高',
  `author` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `aboutimg` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_article` */

insert  into `t_article`(`aid`,`title`,`subhead`,`mainbody`,`accessoryname`,`accessory`,`creattime`,`eid`,`status`,`stick`,`author`,`aboutimg`,`cid`) values (1,'习近平两会强军之道2','央视网消息：强国必须强军，军强才能国安。','2019年3月12日，中共中央总书记、国家主席、中央军委主席习近平出席十三届全国人大二次会议解放军和武警部队代表团全体会议并发表重要讲话。\r\n\r\n　　从2013年两会到今年两会，习近平已经七次参加解放军代表团会议。每次会议，习近平都耐心听取代表汇报，与他们面对面谈科技、谋打赢、论改革，引领铿锵务实的强军制胜之道！（中央广播电视总台央视网）','习近平.pnj',NULL,'2019-03-05 16:41:17',1,0,0,'admin','4',7),(2,'美警告德国勿使用华为 默克尔的回复堪称“打脸”','我们自己做主！默克尔猛击美国“涉华警告”','参考消息网3月14日报道 最近一段时间，美国正在欧洲开展活动，劝阻欧洲国家不要使用中国华为公司的设备来建设5G移动网络基础设施，甚至直接致信德国，警告其勿用华为5G技术。路透社认为，美国此举让德国很恼火。而此后默克尔的回复也堪称“打脸”。\r\n\r\n　　据路透社报道，默克尔12日说，德国政府极为重视包括5G在内的数字网络安全性，但德国将保留自己的看法。“安全性、尤其是5G网络扩张方面的安全性，以及数字领域的其他层面，是德国政府关心的重要问题，因此我们将制定自己的标准。”默克尔说。她说，德国政府将与欧洲伙伴国、“以及美国相应部门”讨论其关心的问题。\r\n\r\n　　除默克尔外，德国议会保守党派领袖迈克尔·格罗塞-布勒默也称，德国有能力处理自身安全问题，并表示：“无需美国大使给出暗示。”格罗塞-布勒默还说，如果出现问题，默克尔会直接与美国总统特朗普对话。\r\n\r\n　　事实上，面对美国的施压，德国展现出的态度在意料之中。\r\n\r\n　　舆论观察到，此次也并非德国近期首次“打脸”美国。在不久前举行的慕尼黑安全会议上，默克尔就曾“炮轰”美国“独断专行”，表示不能轻易破坏多边主义组织，并警告美国总统特朗普，在核武裁军协议、叙利亚撤军和贸易问题方面，不能将欧洲排除在外。美联社称，默克尔慷慨陈词，为全球多边主义辩护，并支持欧洲遵守伊朗核协议的决定，发言赢得了经久不息的掌声。\r\n\r\n　　中国现代国际关系研究院美国研究所学者孙成昊对参考消息网称，美国利用盟友体系打压中国企业的意图十分明显。德国作为欧盟的领头羊，始终强调自身的战略独立性。不过，德国仍面临两难境地。当前，美国毕竟在美欧——这种“不对称”的同盟关系中占据主导地位。在与欧洲国家结盟时，美国必然要求相对弱小的国家在具体问题上做出一些牺牲，以换取美国在安全问题上的保障及经济上的支持。德国对此亦心知肚明。如何找到平衡点，对德国而言是一个较大的挑战。\r\n\r\n　　外界注意到，虽然德国政府对外口径保持一致，表示无确凿证据表明华为设备存在安全漏洞，但美对德多番施压后，德政府对5G网络建设的讨论由最初的网络覆盖问题逐渐转向设备安全问题。各党派亦出现呼吁政府进一步收紧外资审查标准，将企业在基础设施领域的合作也纳入审查范畴，进一步提高网络安全标准等提案。在最新的工业战略草案中，也可观察到政府寻求加强经济干预的倾向。\r\n\r\n　　不过，禁用华为5G设备对德国来说，又受到来自企业及自身多年来数字建设“欠账”的牵制。中国国际问题研究院欧洲所学者吴妍告诉参考消息网，德国数字基础设施建设滞后，缺乏本国电信硬件产业，华为的技术、服务和价格优势得到德电信运营商的普遍认可，且华为长期与德运营商展开联合研究和5G网络商用化实验，禁用华为设备势必提高德企业技术和设备采购成本，同时进一步加剧德数字化建设滞后的问题，影响政府数字化目标的实现。\r\n\r\n　　据悉，德国将在3月19日公布对本国5G设备供应商的认证门槛。届时，德国究竟将何去何从，外界拭目以待。','默克尔.png',NULL,'2019-03-06 16:41:35',1,0,0,'zhangsan','4',7),(3,'坐在火山口的波音还拒不认账 特朗普突然大义灭亲','特朗普突然大义灭亲了！','坐在火山口的波音还拒不认账，美国交通部长特意乘坐737max-8以示支持，在全世界纷纷抵制波音737max-8时，美国似乎铁板一块。\r\n\r\n　　关键时刻，特朗普出手了！\r\n\r\n　　大义灭亲！\r\n\r\n　　北京时间3月14日凌晨2：54分，特朗普在推特上贴出一段视频，他宣布：\r\n\r\n　　我们将会发布紧急命令，禁止所有波音737max8和737 MAX 9型飞机飞行。美国联邦航空管理局（FAA）准备很快就此作出宣布，此刻正在飞行的（涉事机型）可以飞抵目的地，之后它们就要停飞，等待进一步指示……\r\n\r\n　　特朗普传递的信息，也是很明确的：\r\n\r\n　　1，这是紧急命令，事情很紧迫，风险不容忽视。\r\n\r\n　　2，停飞的不仅是引发两起空难的737max8，737max9也一并停飞。\r\n\r\n　　2，FAA将很快宣布，但特朗普先宣布。\r\n\r\n　　重大突发消息，推特驻白宫首席记者特朗普第一时间发布，尤其是在推特上首发，这也成了美国舆论传播的新生态。\r\n\r\n　　可怜，美国交通部长几个小时前刚乘坐737max8型飞机，美FAA代理局长也才宣布：飞机没显示出系统性问题，因此不足以停飞。\r\n\r\n　　但特朗普马上就宣布：紧急停飞。\r\n\r\n　　美国官员估计脸都要绿了：这个世界，唯一可以确定的，就是特朗普的不确定性。','特朗普.png',NULL,'2019-03-07 16:44:53',1,0,0,'wjs','4',7);

/*Table structure for table `t_articletype` */

DROP TABLE IF EXISTS `t_articletype`;

CREATE TABLE `t_articletype` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_articletype` */

insert  into `t_articletype`(`eid`,`ename`) values (1,'情感类'),(2,'新闻类'),(3,'生活类');

/*Table structure for table `t_channel` */

DROP TABLE IF EXISTS `t_channel`;

CREATE TABLE `t_channel` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `calias` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `cnamepath` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cidpath` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `creattime` datetime DEFAULT NULL,
  `tsequence` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_channel` */

insert  into `t_channel`(`cid`,`cname`,`calias`,`pid`,`cnamepath`,`cidpath`,`creattime`,`tsequence`,`lid`) values (1,'首页','首页',0,'/首页','/1','2019-03-14 16:22:07',1,1),(2,'公司业务','公司业务',0,'/公司业务','/2','2019-03-11 16:27:05',2,1),(3,'个人业务','个人业务',0,'/个人业务','/3','2019-03-05 16:27:24',3,1),(4,'互联网金融','互联网金融',0,'/互联网金融','/4','2019-03-04 16:27:30',4,1),(5,'国际业务','国际业务',0,'/国际业务','/5','2019-03-11 16:27:34',5,1),(6,'重要通知','重要通知',1,'/首页/重要通知','/1/6','2019-03-05 16:53:45',6,1),(7,'热点推荐','热点推荐',1,'/首页/热点推荐','/1/7','2019-03-06 16:55:09',7,1),(8,'常用信息','常用信息',1,'/首页/常用信息','/1/8','2019-03-05 16:55:04',8,1),(9,'用户登录区','用户登录区',1,'/首页/用户登录区','/1/9','2019-03-06 16:54:48',9,1),(10,'关于我们','关于我们',0,'/关于我们','/10','2019-03-01 13:29:33',6,1);

/*Table structure for table `t_control` */

DROP TABLE IF EXISTS `t_control`;

CREATE TABLE `t_control` (
  `control_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `control_name` varchar(50) NOT NULL COMMENT '权限名称',
  `control_explain` varchar(200) DEFAULT NULL COMMENT '权限说明',
  `by1` varchar(300) DEFAULT NULL COMMENT '备用1',
  `by2` varchar(300) DEFAULT NULL COMMENT '备用2',
  PRIMARY KEY (`control_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `t_control` */

insert  into `t_control`(`control_id`,`control_name`,`control_explain`,`by1`,`by2`) values (1,'用户管理','进入用户管理界面对用户进行操作','admin-list.html',NULL),(2,'站点管理','进入站点语种管理界面对站点进行操作',NULL,NULL),(3,'频道管理','对站点下的频道进行操作',NULL,NULL),(4,'信息管理','能进入信息管理界面对发布信息进行操作以及设置录入员，审批员',NULL,NULL),(5,'审批管理','对发布信息进行审批',NULL,NULL),(6,'录入信息','进行文章信息的录入',NULL,NULL);

/*Table structure for table `t_institution` */

DROP TABLE IF EXISTS `t_institution`;

CREATE TABLE `t_institution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `idpath` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `namepath` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_institution` */

insert  into `t_institution`(`id`,`iname`,`fid`,`idpath`,`namepath`) values (1,'牛耳总公司',0,'/1','/牛耳总公司'),(2,'五一校区',1,'/1/2','/牛耳总公司/五一校区'),(14,'保卫部',2,'/1/2/14','/牛耳总公司/五一校区/教学部'),(31,'W180801j',14,'/1/2/14/31','/牛耳总公司/五一校区/教学部/W180801j'),(32,'你好',14,'/1/2/14/32','/牛耳总公司/五一校区/教学部/你好');

/*Table structure for table `t_language` */

DROP TABLE IF EXISTS `t_language`;

CREATE TABLE `t_language` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `lname` varchar(50) COLLATE utf8_bin NOT NULL,
  `abbreviation` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `isactivation` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_language` */

insert  into `t_language`(`lid`,`lname`,`abbreviation`,`isactivation`,`tid`) values (1,'简体中文','cn',1,1),(2,'Enlish','us',1,2);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `duty` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `isusable` int(2) DEFAULT '1' COMMENT '是否可用 1.可用，0.不可用',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_role` */

insert  into `t_role`(`rid`,`rname`,`duty`,`isusable`) values (1,'系统管理员','管理用户，站点等',1),(2,'站点管理员','管理站点',1),(3,'频道管理员','管理频道',1),(4,'信息管理员','管理信息',1),(5,'审批员','审批文章信息',1),(6,'录入员','录入文章',1);

/*Table structure for table `t_role_control` */

DROP TABLE IF EXISTS `t_role_control`;

CREATE TABLE `t_role_control` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `control_id` bigint(20) DEFAULT NULL COMMENT '权限'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_control` */

insert  into `t_role_control`(`role_id`,`control_id`) values (1,1),(1,2),(2,2),(3,3),(4,4),(4,5),(4,6),(5,5),(6,6);

/*Table structure for table `t_site` */

DROP TABLE IF EXISTS `t_site`;

CREATE TABLE `t_site` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(50) COLLATE utf8_bin NOT NULL,
  `tintro` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tid`,`tname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_site` */

insert  into `t_site`(`tid`,`tname`,`tintro`) values (1,'牛耳总公司1','培训机构'),(2,'贝尔安亲','培训机构');

/*Table structure for table `t_site_user` */

DROP TABLE IF EXISTS `t_site_user`;

CREATE TABLE `t_site_user` (
  `tid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_site_user` */

insert  into `t_site_user`(`tid`,`uid`) values (1,1),(2,2);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `upassword` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `six` int(11) DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `islogin` int(11) DEFAULT '1',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`uname`,`upassword`,`six`,`phone`,`email`,`id`,`islogin`) values (1,'不必','123456',1,'12345678901','123@qq.com',1,0),(2,'admin','123456',0,'22222222222','admin@qq.com',2,1),(3,'李四','123456',0,'23243432432','123@qq.com',3,1),(4,'王五','123456',0,'23234343434','123@qq.com',2,1),(5,'root','123456',0,'1222222222','ad@qq.com',1,1),(6,'qw','123456',0,'2132','qw@qq.com',3,1),(8,'nihao','123456',1,'12345678911','123@qq.com',3,1),(9,'nihao','123456',1,'12345678911','123@qq.com',3,1),(10,'nihao','123456',1,'12345678911','123@qq.com',3,1),(11,'nihao','123456',1,'12345678911','123@qq.com',3,1),(25,'asdasd','888888',1,'18390116868','zhulu@qq.com',3,1),(26,'xdxd','888888',0,'18390116868','zhulu@qq.com',5,1),(27,'zhului','888888',1,'18390116868','zhulu@qq.com',3,1);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `rid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`rid`,`uid`) values (1,1),(2,2),(3,3),(4,4),(3,5),(3,6),(1,25),(1,26),(1,27);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
