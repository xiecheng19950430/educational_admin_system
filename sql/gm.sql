-- MySQL dump 10.17  Distrib 10.3.13-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: gm
-- ------------------------------------------------------
-- Server version	10.3.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gm_class`
--

DROP TABLE IF EXISTS `gm_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classNo` varchar(50) DEFAULT NULL COMMENT '班级编号',
  `name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `amount` int(11) DEFAULT NULL COMMENT '人数',
  `status` int(11) DEFAULT NULL COMMENT '班级状态，0:在校，1：已毕业',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `teacherId` int(11) DEFAULT NULL COMMENT '班主任id',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除，1：删除，0：未删除',
  `teacherName` varchar(50) DEFAULT NULL COMMENT '班主任名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_class`
--

LOCK TABLES `gm_class` WRITE;
/*!40000 ALTER TABLE `gm_class` DISABLE KEYS */;
INSERT INTO `gm_class` VALUES (15,'1901','一(1)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL),(16,'1902','一(2)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL),(17,'1903','二(1)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL),(18,'1904','二(2)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL),(19,'1905','三(1)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL),(20,'1906','三(2)班',2019,50,0,'2019-03-10 21:16:15',NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `gm_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gm_class_has_gm_teacher`
--

DROP TABLE IF EXISTS `gm_class_has_gm_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_class_has_gm_teacher` (
  `gm_class_id` int(11) NOT NULL,
  `gm_teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`gm_class_id`,`gm_teacher_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_class_has_gm_teacher`
--

LOCK TABLES `gm_class_has_gm_teacher` WRITE;
/*!40000 ALTER TABLE `gm_class_has_gm_teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `gm_class_has_gm_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gm_course`
--

DROP TABLE IF EXISTS `gm_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_course` (
  `id` int(11) NOT NULL,
  `courseNo` varchar(50) DEFAULT NULL COMMENT '课程编号',
  `studentId` int(11) NOT NULL COMMENT '学生id',
  `courseName` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `openGrade` int(11) DEFAULT NULL COMMENT '开设年级',
  `openTerm` varchar(50) DEFAULT NULL COMMENT '开设学期，上学期：lastTerm,下学期：nextTerm',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `classAt` datetime DEFAULT NULL COMMENT '建课时间',
  `type` int(11) DEFAULT NULL COMMENT '课程类型，1：主修，0：辅修',
  `status` int(11) DEFAULT NULL COMMENT '课程性质，1：正常，0：停用',
  `description` varchar(500) DEFAULT NULL COMMENT '课程描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_course`
--

LOCK TABLES `gm_course` WRITE;
/*!40000 ALTER TABLE `gm_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `gm_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gm_gradeinfo`
--

DROP TABLE IF EXISTS `gm_gradeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_gradeinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeNo` varchar(50) NOT NULL COMMENT '成绩编号',
  `courseName` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `studentNo` varchar(50) DEFAULT NULL COMMENT '学号',
  `name` varchar(50) DEFAULT NULL COMMENT '学生姓名',
  `grade_ordinary` int(11) DEFAULT NULL COMMENT '平时成绩',
  `grade_mid` int(11) DEFAULT NULL COMMENT '期中成绩',
  `grade_final` int(11) DEFAULT NULL COMMENT '期末成绩',
  `grade_all` int(11) DEFAULT NULL COMMENT '总评',
  `classId` int(11) NOT NULL COMMENT '班级Id',
  `teacherId` int(11) NOT NULL COMMENT '教师Id',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `isPass` int(11) DEFAULT NULL COMMENT '是否及格，0：不及格，1：及格',
  `term` varchar(50) DEFAULT NULL COMMENT '学期，上学期：lastTerm,下学期：nextTerm',
  `schoolYear` int(11) DEFAULT NULL COMMENT '学年',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除，1：删除，0：未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_gradeinfo`
--

LOCK TABLES `gm_gradeinfo` WRITE;
/*!40000 ALTER TABLE `gm_gradeinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `gm_gradeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gm_student`
--

DROP TABLE IF EXISTS `gm_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_student` (
  `id` int(11) NOT NULL,
  `classId` int(11) NOT NULL COMMENT '学生所在班级id',
  `studentNo` varchar(50) DEFAULT NULL COMMENT '学号',
  `name` varchar(50) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(11) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_student`
--

LOCK TABLES `gm_student` WRITE;
/*!40000 ALTER TABLE `gm_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `gm_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gm_teacher`
--

DROP TABLE IF EXISTS `gm_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gm_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workNo` varchar(50) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `loginId` varchar(50) NOT NULL COMMENT '登陆账号',
  `password` varchar(50) DEFAULT NULL COMMENT '登陆密码',
  `sex` varchar(11) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `hiredate` datetime DEFAULT NULL COMMENT '入职日期',
  `position` varchar(50) DEFAULT NULL COMMENT '职称',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL COMMENT '角色，超级管理员为superAdmin，其他为角色id（多个用"，"隔开）,班主任classTeacher，系统管理员sysAdmin,教务管理员eduAdmin，教学领导teachLeader',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除，1：删除，0：未删除',
  `classId` int(11) DEFAULT NULL COMMENT '班级Id',
  `roleIds` varchar(500) DEFAULT NULL COMMENT '绑定角色id，多个用“，”隔开',
  `roleNames` varchar(500) DEFAULT NULL COMMENT '绑定角色名称，多个用“，”隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gm_teacher`
--

LOCK TABLES `gm_teacher` WRITE;
/*!40000 ALTER TABLE `gm_teacher` DISABLE KEYS */;
INSERT INTO `gm_teacher` VALUES (1,NULL,'超级管理员','admin','admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'superAdmin',NULL,NULL,NULL,NULL),(2,'1','1','1','123456','1',NULL,NULL,NULL,NULL,'2019-03-10 17:59:10','2019-03-10 18:42:01','teacher',0,NULL,'2,3,4,5','教务管理员,教学领导,班主任,教师'),(3,'2','2','2','2','2',NULL,NULL,NULL,NULL,'2019-03-10 19:09:17',NULL,'teacher',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `gm_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_module_relation`
--

DROP TABLE IF EXISTS `role_module_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_module_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `moduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_module_relation`
--

LOCK TABLES `role_module_relation` WRITE;
/*!40000 ALTER TABLE `role_module_relation` DISABLE KEYS */;
INSERT INTO `role_module_relation` VALUES (3,5,2),(4,4,1),(5,4,2);
/*!40000 ALTER TABLE `role_module_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_role_relation`
--

DROP TABLE IF EXISTS `teacher_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_role_relation`
--

LOCK TABLES `teacher_role_relation` WRITE;
/*!40000 ALTER TABLE `teacher_role_relation` DISABLE KEYS */;
INSERT INTO `teacher_role_relation` VALUES (13,2,2),(14,2,3),(15,2,4),(16,2,5);
/*!40000 ALTER TABLE `teacher_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_module`
--

DROP TABLE IF EXISTS `user_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `pid` int(11) DEFAULT NULL COMMENT '父模块',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_module`
--

LOCK TABLES `user_module` WRITE;
/*!40000 ALTER TABLE `user_module` DISABLE KEYS */;
INSERT INTO `user_module` VALUES (1,'模块管理','/module',NULL),(2,'角色管理','/role',NULL);
/*!40000 ALTER TABLE `user_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL COMMENT '角色代码',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名',
  `moduleIds` varchar(500) DEFAULT NULL COMMENT '模块Ids',
  `moduleUrls` varchar(500) DEFAULT NULL COMMENT '模块路径',
  `type` int(11) DEFAULT NULL COMMENT '类型1：系统角色（不可更改），2自定义角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'systemAdmin','系统管理员',NULL,NULL,1),(2,'educationAdmin','教务管理员',NULL,NULL,1),(3,'teachLeader','教学领导',NULL,NULL,1),(4,'headmaster','班主任',NULL,'/module,/role',1),(5,'teacher','教师',NULL,'/role',1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-17 22:19:29
