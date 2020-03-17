/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : clms

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/03/2020 21:15:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cl_article
-- ----------------------------
DROP TABLE IF EXISTS `cl_article`;
CREATE TABLE `cl_article`  (
  `article_id` int(31) NOT NULL AUTO_INCREMENT COMMENT '文章id编号',
  `article_title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `article_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章图片',
  `article_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `article_good` int(11) NOT NULL DEFAULT 0 COMMENT '文章点赞量',
  `article_read` int(11) NOT NULL DEFAULT 0 COMMENT '文章阅读量',
  `article_collection` int(11) NOT NULL DEFAULT 0 COMMENT '文章收藏量',
  `article_type` int(11) NOT NULL COMMENT '文章分类',
  `article_comment` int(11) NOT NULL DEFAULT 0 COMMENT '文章评论',
  `article_source` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章来源',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_enabled` int(1) NOT NULL DEFAULT 1 COMMENT '是否启用（0否1是 默认1）',
  `is_deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0否1是 默认0）',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
