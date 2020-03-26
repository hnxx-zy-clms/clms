/*
 Navicat Premium Data Transfer

 Source Server         : linux175.24.45.179
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 175.24.45.179:3306
 Source Schema         : clms_test

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 25/03/2020 23:47:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cl_good
-- ----------------------------
DROP TABLE IF EXISTS `cl_good`;
CREATE TABLE `cl_good`  (
  `good_id` int(63) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `article_id` int(31) NOT NULL DEFAULT 0 COMMENT '文章id',
  `comment_id` int(31) NOT NULL DEFAULT 0 COMMENT '评论id',
  PRIMARY KEY (`good_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
