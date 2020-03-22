/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : team_clms

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 21/03/2020 20:11:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cl_commission
-- ----------------------------
DROP TABLE IF EXISTS `cl_commission`;
CREATE TABLE `cl_commission`  (
  `com_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '今日代办主键id',
  `user_id` int(11) NOT NULL COMMENT '创建者id',
  `com_content` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代办内容',
  `is_did` bit(1) NOT NULL COMMENT '是否完成',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`com_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_notice
-- ----------------------------
DROP TABLE IF EXISTS `cl_notice`;
CREATE TABLE `cl_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `created_id` int(11) NOT NULL COMMENT '创建者id',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `notice_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知内容',
  `notice_title` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通知标题',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，1是0否',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_notice_user`;
CREATE TABLE `cl_notice_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户通知关联表id',
  `notice_id` int(11) NOT NULL COMMENT '通知id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `if_read` tinyint(1) NOT NULL COMMENT '是否已读，1是0否',
  `read_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '阅读时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_registration
-- ----------------------------
DROP TABLE IF EXISTS `cl_registration`;
CREATE TABLE `cl_registration`  (
  `sign_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '签到表id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `sign_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签到时间',
  `sign_class` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第几节课签到，按5节大课划分',
  PRIMARY KEY (`sign_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_task
-- ----------------------------
DROP TABLE IF EXISTS `cl_task`;
CREATE TABLE `cl_task`  (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务表id',
  `created_id` int(11) NOT NULL COMMENT '创建人id',
  `task_title` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务标题',
  `task_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务内容',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_enabled` tinyint(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_task_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_task_user`;
CREATE TABLE `cl_task_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务用户表Id',
  `task_id` int(11) NOT NULL COMMENT '任务id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `is_did` tinyint(1) NOT NULL COMMENT '是否完成，1是0否',
  `did_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
