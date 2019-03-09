/*
 Navicat Premium Data Transfer

 Source Server         : gradeManage
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : grade_manage_system

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 27/02/2019 21:49:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gm_class
-- ----------------------------
DROP TABLE IF EXISTS `gm_class`;
CREATE TABLE `gm_class`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `classNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级编号',
                           `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名称',
                           `year` int(11) DEFAULT NULL COMMENT '年份',
                           `amount` int(11) DEFAULT NULL COMMENT '人数',
                           `status` int(11) DEFAULT NULL COMMENT '班级状态，0:在校，1：已毕业',
                           `createAt` datetime(0) DEFAULT NULL,
                           `updateAt` datetime(0) DEFAULT NULL,
                           `teacherId` int(11)  COMMENT '教师Id',
                           `isDelete` int(11) DEFAULT NULL COMMENT '是否删除，1：删除，0：未删除',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gm_class_has_gm_teacher
-- ----------------------------
DROP TABLE IF EXISTS `gm_class_has_gm_teacher`;
CREATE TABLE `gm_class_has_gm_teacher`  (
                                          `gm_class_id` int(11) NOT NULL,
                                          `gm_teacher_id` int(11) NOT NULL,
                                          PRIMARY KEY (`gm_class_id`, `gm_teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gm_course
-- ----------------------------
DROP TABLE IF EXISTS `gm_course`;
CREATE TABLE `gm_course`  (
                            `id` int(11) NOT NULL,
                            `courseNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程编号',
                            `studentId` int(11) NOT NULL COMMENT '学生id',
                            `courseName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
                            `openGrade` int(11) DEFAULT NULL COMMENT '开设年级',
                            `openTerm` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开设学期，上学期：lastTerm,下学期：nextTerm',
                            `createAt` datetime(0) DEFAULT NULL,
                            `updateAt` datetime(0) DEFAULT NULL,
                            `classAt` datetime(0) DEFAULT NULL COMMENT '建课时间',
                            `type` int(11) DEFAULT NULL COMMENT '课程类型，1：主修，0：辅修',
                            `status` int(11) DEFAULT NULL COMMENT '课程性质，1：正常，0：停用',
                            `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程描述',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gm_gradeinfo
-- ----------------------------
DROP TABLE IF EXISTS `gm_gradeinfo`;
CREATE TABLE `gm_gradeinfo`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `gradeNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成绩编号',
                               `courseName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
                               `studentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学号',
                               `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
                               `grade_ordinary` int(11) DEFAULT NULL COMMENT '平时成绩',
                               `grade_mid` int(11) DEFAULT NULL COMMENT '期中成绩',
                               `grade_final` int(11) DEFAULT NULL COMMENT '期末成绩',
                               `grade_all` int(11) DEFAULT NULL COMMENT '总评',
                               `classId` int(11) NOT NULL COMMENT '班级Id',
                               `teacherId` int(11) NOT NULL COMMENT '教师Id',
                               `createAt` datetime(0) DEFAULT NULL,
                               `updateAt` datetime(0) DEFAULT NULL,
                               `isPass` int(11) DEFAULT NULL COMMENT '是否及格，0：不及格，1：及格',
                               `term` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学期，上学期：lastTerm,下学期：nextTerm',
                               `schoolYear` int(11) DEFAULT NULL COMMENT '学年',
                               `isDelete` int(11) DEFAULT NULL COMMENT '是否删除，1：删除，0：未删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gm_student
-- ----------------------------
DROP TABLE IF EXISTS `gm_student`;
CREATE TABLE `gm_student`  (
                             `id` int(11) NOT NULL,
                             `classId` int(11) NOT NULL COMMENT '学生所在班级id',
                             `studentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学号',
                             `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
                             `sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
                             `birthday` datetime(0) DEFAULT NULL,
                             `createAt` datetime(0) DEFAULT NULL,
                             `updateAt` datetime(0) DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gm_teacher
-- ----------------------------
DROP TABLE IF EXISTS `gm_teacher`;
create table gm_teacher
(
  id       int auto_increment
    primary key,
  workNo   varchar(50)  null comment '工号',
  name     varchar(100) null comment '姓名',
  loginId  varchar(50)  not null comment '登陆账号',
  password varchar(50)  null comment '登陆密码',
  sex      varchar(11)  null comment '性别',
  birthday datetime     null comment '出生日期',
  hiredate datetime     null comment '入职日期',
  position varchar(50)  null comment '职称',
  phone    varchar(50)  null comment '联系电话',
  createAt datetime     null,
  updateAt datetime     null,
  role     varchar(500)  null comment '角色，超级管理员为superAdmin，其他为角色id（多个用"，"隔开）,班主任classTeacher，系统管理员sysAdmin,教务管理员eduAdmin，教学领导teachLeader',
  isDelete int          null comment '是否删除，1：删除，0：未删除',
  classId  int          null comment '班级Id'
)
  charset = utf8;

--超管账号
insert into gm_teacher(loginId, password,role) values ('admin', 'admin','superAdmin');


-- ----------------------------
-- Table structure for user_module
-- ----------------------------
DROP TABLE IF EXISTS `user_module`;
create table user_module
(
  id         int auto_increment
    primary key,
  moduleName varchar(50)  null comment '模块名称',
  url        varchar(255) null comment '路径',
  pid        int          null comment '父模块'
)
  charset = utf8;
-- 现有模块
-- insert into user_module(moduleName, url) values ('', '');


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
create table user_role
(
  id         int auto_increment
    primary key,
  role       varchar(50)  not null comment '角色代码',
  roleName   varchar(50)  null comment '角色名',
  moduleIds  varchar(500)  null comment '模块Ids',
  moduleUrls varchar(500) null comment '模块路径',
  type  int null comment '类型1：系统角色（不可更改），2自定义角色'
)
  charset = utf8;

-- 系统角色
insert into user_role(role, roleName,type) values ('systemAdmin', '系统管理员',1);
insert into user_role(role, roleName,type) values ('educationAdmin', '教务管理员',1);
insert into user_role(role, roleName,type) values ('teachLeader', '教学领导',1);
insert into user_role(role, roleName,type) values ('headmaster', '班主任',1);
insert into user_role(role, roleName,type) values ('teacher', '教师',1);


DROP TABLE IF EXISTS `role_module_relation`;
create table role_module_relation
(
  id       int auto_increment
    primary key,
  roleId   int null,
  moduleId int null
);

DROP TABLE IF EXISTS `teacher_role_relation`;
create table teacher_role_relation
(
  id        int auto_increment
    primary key,
  teacherId int null,
  roleId    int null
);





