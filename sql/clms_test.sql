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

 Date: 25/03/2020 12:01:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cl_article
-- ----------------------------
DROP TABLE IF EXISTS `cl_article`;
CREATE TABLE `cl_article`  (
  `article_id` int(31) NOT NULL AUTO_INCREMENT COMMENT '文章id编号',
  `article_title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `article_author` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `article_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章图片',
  `article_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `article_good` int(11) NOT NULL DEFAULT 0 COMMENT '文章点赞量',
  `article_read` int(11) NOT NULL DEFAULT 0 COMMENT '文章阅读量',
  `article_collection` int(11) NOT NULL DEFAULT 0 COMMENT '文章收藏量',
  `article_type` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章分类',
  `article_comment` int(11) NOT NULL DEFAULT 0 COMMENT '文章评论',
  `article_source` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章来源',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用（0否1是 默认1）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0否1是 默认0）',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_article_collection
-- ----------------------------
DROP TABLE IF EXISTS `cl_article_collection`;
CREATE TABLE `cl_article_collection`  (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `article_id` int(31) NOT NULL COMMENT '收藏文章id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `collection_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`collection_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_article_type
-- ----------------------------
DROP TABLE IF EXISTS `cl_article_type`;
CREATE TABLE `cl_article_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章分类id',
  `type_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `type_count` int(11) NOT NULL DEFAULT 0 COMMENT '文章数',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用，0否1是 默认1',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是 默认0',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_classes
-- ----------------------------
DROP TABLE IF EXISTS `cl_classes`;
CREATE TABLE `cl_classes`  (
  `classes_id` int(11) NOT NULL,
  `classes_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `classes_states` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`classes_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_college
-- ----------------------------
DROP TABLE IF EXISTS `cl_college`;
CREATE TABLE `cl_college`  (
  `college_id` int(20) NOT NULL,
  `college_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院名称',
  `college_states` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`college_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_comment
-- ----------------------------
DROP TABLE IF EXISTS `cl_comment`;
CREATE TABLE `cl_comment`  (
  `comment_id` int(31) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `comment_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `comment_user` int(11) NOT NULL COMMENT '评价人',
  `comment_article` int(31) NOT NULL COMMENT '评论帖子id',
  `comment_good` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment_type` tinyint(11) NOT NULL DEFAULT 0 COMMENT '评论类型',
  `pid` int(31) NOT NULL DEFAULT 0 COMMENT '父级id',
  `comment_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用,  0否1是 默认1',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是 默认0',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for cl_githubuser
-- ----------------------------
DROP TABLE IF EXISTS `cl_githubuser`;
CREATE TABLE `cl_githubuser`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(31) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户姓名',
  `account_id` varchar(31) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `github_token` char(31) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'GitHub Token',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `user_right` tinyint(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户权限',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_group
-- ----------------------------
DROP TABLE IF EXISTS `cl_group`;
CREATE TABLE `cl_group`  (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `group_states` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_log`;
CREATE TABLE `cl_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `log_url` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求路径',
  `log_params` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数',
  `log_status` int(1) NOT NULL DEFAULT 1 COMMENT '访问状态，1正常0异常',
  `log_message` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
  `log_method` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式，get、post等',
  `log_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '响应时间',
  `log_result` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回值',
  `log_ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求ip',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 173 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '接口访问日志表' ROW_FORMAT = Dynamic;

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
-- Table structure for cl_position
-- ----------------------------
DROP TABLE IF EXISTS `cl_position`;
CREATE TABLE `cl_position`  (
  `position_id` int(11) NOT NULL AUTO_INCREMENT,
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位名称',
  `position_status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for cl_report
-- ----------------------------
DROP TABLE IF EXISTS `cl_report`;
CREATE TABLE `cl_report`  (
  `report_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '报告Id',
  `report_type` int(1) NOT NULL COMMENT '报告类型 0日报 1周报',
  `work_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '工作内容',
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '遇到的困难',
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '解决方法',
  `experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '心得体会',
  `plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '后续计划',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_enabled` int(1) NOT NULL DEFAULT 0 COMMENT '是否可以更改 0可更改 1不可更改',
  `is_deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否已删除 0未删除 1已删除',
  `is_checked` int(1) NOT NULL DEFAULT 0 COMMENT '组长是否批阅 0未批阅 1已批阅',
  PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_report_marking
-- ----------------------------
DROP TABLE IF EXISTS `cl_report_marking`;
CREATE TABLE `cl_report_marking`  (
  `report_id` int(11) UNSIGNED ZEROFILL NOT NULL COMMENT '报告id',
  `keys` varchar(31) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '操作类型',
  `values` varchar(127) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '操作内容',
  `user_name` varchar(31) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '批阅人',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  INDEX `report_id`(`report_id`) USING BTREE,
  CONSTRAINT `cl_report_marking_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `cl_report` (`report_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for cl_user
-- ----------------------------
DROP TABLE IF EXISTS `cl_user`;
CREATE TABLE `cl_user`  (
  `user_id` int(63) NOT NULL AUTO_INCREMENT COMMENT '用户序号',
  `user_name` varchar(31) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '账号',
  `user_password` varchar(31) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码',
  `user_college_id` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '学院id',
  `user_classes_id` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级id',
  `user_group_id` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户组id',
  `user_icon` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '头像',
  `user_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `user_position_id` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户职称id',
  `user_states` int(255) NULL DEFAULT NULL COMMENT '用户状态（0,1）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_user_report
-- ----------------------------
DROP TABLE IF EXISTS `cl_user_report`;
CREATE TABLE `cl_user_report`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '连接id',
  `report_id` int(11) UNSIGNED ZEROFILL NOT NULL COMMENT '报告id',
  `user_id` int(11) UNSIGNED ZEROFILL NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `report_id`(`report_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cl_xx
-- ----------------------------
DROP TABLE IF EXISTS `cl_xx`;
CREATE TABLE `cl_xx`  (
  `xx_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'xxid标识',
  `xx_name` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'xx名字',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用（0否1是 默认1）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0否1是 默认0）',
  PRIMARY KEY (`xx_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
