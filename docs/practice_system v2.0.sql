/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.5.27 : Database - practice_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`practice_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `practice_system`;

/*Table structure for table `announce` */

DROP TABLE IF EXISTS `announce`;

CREATE TABLE `announce` (
  `a_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '通知编号',
  `type` tinyint(1) NOT NULL COMMENT '通知类型 1教师 2学生',
  `title` varchar(32) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知内容',
  `time` date NOT NULL COMMENT '通知日期',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `announce` */

insert  into `announce`(`a_id`,`type`,`title`,`content`,`time`) values (6,2,'选课规则','选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。','2019-12-06'),(8,2,'选课规则','选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。','2019-12-05'),(9,1,'选课规则','选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。','2019-12-20'),(10,2,'选课规则','你随便看','2019-12-23'),(11,2,'aaa','<p>aa</p>','2019-12-01'),(12,1,'eeeee','<p>eee</p><p>eee</p><p>ee</p>','2019-12-01'),(13,1,'1123','<p><strong>axxzxxsxz</strong></p><p><strong><em>adxadaxX</em></strong></p><p><strong style=\"color: rgb(230, 0, 0);\"><em>xzccxcsdd</em></strong></p>','2019-12-04');

/*Table structure for table `choice` */

DROP TABLE IF EXISTS `choice`;

CREATE TABLE `choice` (
  `c_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '志愿编号',
  `p_id` int(12) NOT NULL COMMENT '项目id',
  `m_id` int(12) NOT NULL COMMENT '人员id',
  `type` tinyint(1) NOT NULL COMMENT '类型 1第一志愿 2第二志愿 3第三志愿',
  PRIMARY KEY (`c_id`),
  KEY `p_id` (`p_id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `choice_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `project` (`p_id`),
  CONSTRAINT `choice_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `choice` */

insert  into `choice`(`c_id`,`p_id`,`m_id`,`type`) values (1,1,2,1),(2,2,3,2),(3,3,4,0),(4,4,5,1),(5,5,6,2),(6,6,7,0),(7,7,8,1),(8,8,9,2),(9,9,10,0);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `m_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '人员编号',
  `user_name` char(11) NOT NULL COMMENT '用户名（学号）',
  `password` char(32) NOT NULL COMMENT '密码',
  `type` tinyint(1) NOT NULL COMMENT '类型 1管理员 2教师 3学生',
  `college` varchar(12) DEFAULT NULL COMMENT '学院',
  `major` varchar(12) DEFAULT NULL COMMENT '专业',
  `grade` tinyint(1) DEFAULT NULL COMMENT '年级',
  `classes` tinyint(1) DEFAULT NULL COMMENT '班级',
  `address` varchar(20) DEFAULT NULL COMMENT '地址',
  `phone` char(11) DEFAULT NULL COMMENT '电话',
  `name` varchar(12) NOT NULL COMMENT '姓名',
  `selected` tinyint(1) DEFAULT '0' COMMENT '是否被选中',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `teacher_id` int(12) DEFAULT NULL COMMENT '学生的老师id',
  `project_id` int(12) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(20) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`m_id`),
  UNIQUE KEY `username` (`user_name`),
  KEY `project_id` (`project_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`p_id`),
  CONSTRAINT `member_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`m_id`,`user_name`,`password`,`type`,`college`,`major`,`grade`,`classes`,`address`,`phone`,`name`,`selected`,`email`,`teacher_id`,`project_id`,`project_name`) values (1,'admin','bced6fd149cfcdb85741768da12e41c6',1,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三',0,NULL,NULL,NULL,NULL),(2,'admin1','1a449bd939aa083e2aa5456fa3c4ce68',3,'电子信息学院','计算机科学与技术',1,7,'aa','123eew','张三1',0,'bbb',NULL,NULL,NULL),(3,'admin2','89b9ce5628a3f3768ce94ce03035fe1b',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三2',0,NULL,NULL,NULL,NULL),(4,'admin3','a3778f507d587da3966bfd2060117afa',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三3',0,NULL,NULL,NULL,NULL),(5,'admin4','14e980a748d13e733db3365e7c33fe0e',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三4',0,NULL,NULL,NULL,NULL),(6,'admin5','c1aa7e544e2f74654401fb0cb8d1bf68',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三5',0,NULL,NULL,NULL,NULL),(7,'admin6','43495b325bfd8884f524898d41a9767f',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三6',0,NULL,NULL,NULL,NULL),(8,'admin7','99bbc50e65de5f7bf56326cdc6005a55',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三7',0,NULL,NULL,NULL,NULL),(9,'admin8','2e5ad2d81313e009744d5ecb94153983',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三8',0,NULL,NULL,NULL,NULL),(10,'admin9','980eddc3ce342383990b0d8760ef6744',3,'电子信息学院','计算机科学与技术',4,4,NULL,NULL,'张三9',0,NULL,NULL,NULL,NULL),(11,'teacher1','64ba6998217648fe27f291c51557a5a2',2,NULL,NULL,NULL,NULL,NULL,NULL,'李教师1',0,NULL,NULL,NULL,NULL),(12,'teacher2','4f85da3f159de6811d08a221d443a476',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师2',0,NULL,NULL,NULL,NULL),(13,'teacher3','2bb43ffa83c7890ee94cbfd5e815c10f',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师3',0,NULL,NULL,NULL,NULL),(14,'teacher4','205a5ef138c2227545daefbd9687391c',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师4',0,NULL,NULL,NULL,NULL),(15,'teacher5','57f4af9563c43394ebf4b2053fade347',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师5',0,NULL,NULL,NULL,NULL),(16,'teacher6','ccd972a2ee31897d51a47f3d1b9d84bb',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师6',0,NULL,NULL,NULL,NULL),(17,'teacher7','1d6ec11a98e18818de78230ab8052e22',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师7',0,NULL,NULL,NULL,NULL),(18,'teacher8','bc4665e40bac914615a7bc836015397f',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师8',0,NULL,NULL,NULL,NULL),(19,'teacher9','afa912e9113afe571db6e85a99a4afdf',3,NULL,NULL,NULL,NULL,NULL,NULL,'李教师9',0,NULL,NULL,NULL,NULL);

/*Table structure for table `pm` */

DROP TABLE IF EXISTS `pm`;

CREATE TABLE `pm` (
  `pm_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '项目学生表',
  `p_id` int(12) NOT NULL COMMENT '项目id',
  `m_id` int(12) NOT NULL COMMENT '学生id',
  PRIMARY KEY (`pm_id`),
  KEY `p_id` (`p_id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `pm_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `project` (`p_id`),
  CONSTRAINT `pm_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `pm` */

insert  into `pm`(`pm_id`,`p_id`,`m_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,1,2),(11,2,3),(12,3,4),(13,4,5),(14,5,6),(15,6,7),(16,7,8),(17,8,9),(18,9,10);

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `p_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `teacher_id` int(12) NOT NULL COMMENT '教师id',
  `p_name` varchar(20) NOT NULL COMMENT '项目名称',
  `member` tinyint(1) NOT NULL COMMENT '需求人数',
  `content` text NOT NULL COMMENT '内容',
  `file` varchar(30) DEFAULT NULL COMMENT '文档',
  `teacher_name` varchar(12) NOT NULL COMMENT '教师名称',
  `is_full` tinyint(1) DEFAULT '0' COMMENT '是否满人',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 1创建 2审核 3驳回 4通过',
  `selected` tinyint(1) DEFAULT '0' COMMENT ' 已选人数',
  `first` tinyint(1) DEFAULT '0' COMMENT '第一志愿人数',
  `second` tinyint(1) DEFAULT '0' COMMENT '第二志愿人数',
  `third` tinyint(1) DEFAULT '0' COMMENT '第三志愿人数',
  PRIMARY KEY (`p_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`p_id`,`teacher_id`,`p_name`,`member`,`content`,`file`,`teacher_name`,`is_full`,`status`,`selected`,`first`,`second`,`third`) values (1,11,'综合实践管理系统1',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher1',0,1,2,0,0,0),(2,12,'综合实践管理系统2',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher2',0,2,2,0,0,0),(3,13,'综合实践管理系统3',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher3',0,3,2,0,0,0),(4,14,'综合实践管理系统4',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher4',0,4,2,0,0,0),(5,15,'综合实践管理系统5',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher5',0,1,2,0,0,0),(6,16,'综合实践管理系统6',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher6',0,2,2,0,0,0),(7,17,'综合实践管理系统7',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher7',0,3,2,0,0,0),(8,18,'综合实践管理系统8',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher8',0,4,2,0,0,0),(9,19,'综合实践管理系统9',5,'选课规则：学生选择三个项目，分为一二三志愿提交，然后老师先从选了自己项目为一志愿的学生中挑选，若没选满，则从第二志愿选中其项目而又没在第一志愿被选取得学生中挑选。若三个志愿都没选到，则进入二次选择。从未满人的项目的选择，重复之前操作， 若二次选择还是没有呗选上的学生，管理员直接帮其加入剩下的未满人的项目中。 可以重复提交，已最后一次提交为准。',NULL,'teacher9',0,1,2,0,0,0),(10,11,'aaa',3,'<p>项目详情：</p><p>各项目组制定项目计划，有步骤的完成项目内容并提交有关成果。项目实施过程中要求：</p><p>（一）每位学生每周必须参加一次工程实践课程小组学习，由指导老师到场指导。填写《教师指导记录》</p><p>（二）每位学生每周必须参加两次工程实践课程集体学习（讲座形式，由各指导老师和企业导师主讲）。填写《讲座考勤表》</p><p>（三）每位学生按照计划完成每周工作。填写《学生工作日志》</p><p>（四）每组有步骤的完成项目内容。需提交应用型成果及相应文档，如《规划说明书》、《软件需求分析说明书》、《软件设计说明书》、《软件测试说明书》、《推广实施说明书》、《资金预算表》、《资金执行计划表》、《用户手册》等。</p><p>（五）每位学生需撰写项目工程实践报告。实践报告要求书写规范、文字通顺、图表清晰，不得少于8000字，要求文字打印，统一格式，统一封面，装订成册。撰写《项目工程实践报告》</p>',NULL,'李教师1',0,2,2,0,0,0),(11,11,'bbb',7,'<p>项目详情：</p><p>各项目组制定项目计划，有步骤的完成项目内容并提交有关成果。项目实施过程中要求：</p><p>（一）每位学生每周必须参加一次工程实践课程小组学习，由指导老师到场指导。填写《教师指导记录》</p><p>（二）每位学生每周必须参加两次工程实践课程集体学习（讲座形式，由各指导老师和企业导师主讲）。填写《讲座考勤表》</p><p>（三）每位学生按照计划完成每周工作。填写《学生工作日志》</p><p>（四）每组有步骤的完成项目内容。需提交应用型成果及相应文档，如《规划说明书》、《软件需求分析说明书》、《软件设计说明书》、《软件测试说明书》、《推广实施说明书》、《资金预算表》、《资金执行计划表》、《用户手册》等。</p><p>（五）每位学生需撰写项目工程实践报告。实践报告要求书写规范、文字通顺、图表清晰，不得少于8000字，要求文字打印，统一格式，统一封面，装订成册。撰写《项目工程实践报告》</p>','','李教师1',0,1,0,0,0,0),(12,11,'ccc',6,'<p>项目详情：</p><p>各项目组制定项目计划，有步骤的完成项目内容并提交有关成果。项目实施过程中要求：</p><p>（一）每位学生每周必须参加一次工程实践课程小组学习，由指导老师到场指导。填写《教师指导记录》</p><p>（二）每位学生每周必须参加两次工程实践课程集体学习（讲座形式，由各指导老师和企业导师主讲）。填写《讲座考勤表》</p><p>（三）每位学生按照计划完成每周工作。填写《学生工作日志》</p><p>（四）每组有步骤的完成项目内容。需提交应用型成果及相应文档，如《规划说明书》、《软件需求分析说明书》、《软件设计说明书》、《软件测试说明书》、《推广实施说明书》、《资金预算表》、《资金执行计划表》、《用户手册》等。</p><p>（五）每位学生需撰写项目工程实践报告。实践报告要求书写规范、文字通顺、图表清晰，不得少于8000字，要求文字打印，统一格式，统一封面，装订成册。撰写《项目工程实践报告》</p>','','李教师1',0,1,0,0,0,0),(13,11,'ddd',1,'<p>项目详情：</p><p>各项目组制定项目计划，有步骤的完成项目内容并提交有关成果。项目实施过程中要求：</p><p>（一）每位学生每周必须参加一次工程实践课程小组学习，由指导老师到场指导。填写《教师指导记录》</p><p>（二）每位学生每周必须参加两次工程实践课程集体学习（讲座形式，由各指导老师和企业导师主讲）。填写《讲座考勤表》</p><p>（三）每位学生按照计划完成每周工作。填写《学生工作日志》</p><p>（四）每组有步骤的完成项目内容。需提交应用型成果及相应文档，如《规划说明书》、《软件需求分析说明书》、《软件设计说明书》、《软件测试说明书》、《推广实施说明书》、《资金预算表》、《资金执行计划表》、《用户手册》等。</p><p>（五）每位学生需撰写项目工程实践报告。实践报告要求书写规范、文字通顺、图表清晰，不得少于8000字，要求文字打印，统一格式，统一封面，装订成册。撰写《项目工程实践报告》</p>','','李教师1',0,1,0,0,0,0);

/*Table structure for table `project_doc` */

DROP TABLE IF EXISTS `project_doc`;

CREATE TABLE `project_doc` (
  `pd_id` int(12) NOT NULL AUTO_INCREMENT COMMENT '文档编号',
  `pd_name` varchar(20) NOT NULL COMMENT '文档名称',
  `type` tinyint(1) NOT NULL COMMENT '文档类型',
  `student_id` int(12) NOT NULL COMMENT '学生id',
  `student_name` varchar(20) NOT NULL COMMENT '学生姓名',
  `teacher_id` int(12) NOT NULL COMMENT '教师id',
  `teacher_name` varchar(20) NOT NULL COMMENT '教师名称',
  `time` date NOT NULL COMMENT '上传日期',
  `url` varchar(128) DEFAULT NULL COMMENT '文档路径',
  PRIMARY KEY (`pd_id`),
  KEY `student_id` (`student_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `project_doc_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `member` (`m_id`),
  CONSTRAINT `project_doc_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `project_doc` */

insert  into `project_doc`(`pd_id`,`pd_name`,`type`,`student_id`,`student_name`,`teacher_id`,`teacher_name`,`time`,`url`) values (1,'文档1',1,2,'admin1',11,'teacher1','2019-11-30','www.baidu.com'),(2,'文档2',2,3,'admin2',12,'teacher2','2019-11-30','www.baidu.com'),(3,'文档3',3,4,'admin3',13,'teacher3','2019-11-30','www.baidu.com'),(4,'文档4',4,5,'admin4',14,'teacher4','2019-11-30','www.baidu.com'),(5,'文档5',5,6,'admin5',15,'teacher5','2019-11-30','www.baidu.com'),(6,'文档6',0,7,'admin6',16,'teacher6','2019-11-30','www.baidu.com'),(7,'文档7',1,8,'admin7',17,'teacher7','2019-11-30','www.baidu.com'),(8,'文档8',2,9,'admin8',18,'teacher8','2019-11-30','www.baidu.com'),(9,'文档9',3,10,'admin9',19,'teacher9','2019-11-30','www.baidu.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
