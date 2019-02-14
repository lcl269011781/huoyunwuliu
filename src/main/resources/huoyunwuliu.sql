/*
 Navicat Premium Data Transfer

 Source Server         : lichunliang
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : huoyunwuliu

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 12/02/2019 11:30:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for age_range
-- ----------------------------
DROP TABLE IF EXISTS `age_range`;
CREATE TABLE `age_range`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `age_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of age_range
-- ----------------------------
INSERT INTO `age_range` VALUES (1, '面议');
INSERT INTO `age_range` VALUES (2, '20及以下');
INSERT INTO `age_range` VALUES (3, '21~30');
INSERT INTO `age_range` VALUES (4, '31~40');
INSERT INTO `age_range` VALUES (5, '41~50');
INSERT INTO `age_range` VALUES (6, '51~60');
INSERT INTO `age_range` VALUES (7, '60以上');

-- ----------------------------
-- Table structure for car_information
-- ----------------------------
DROP TABLE IF EXISTS `car_information`;
CREATE TABLE `car_information`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `shipper_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货主姓名',
  `departure_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发城市',
  `reaching_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '到达城市',
  `weight_or_volume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '重量体积',
  `vehicle_type_id` int(10) NOT NULL COMMENT '车辆类型',
  `vehicle_volume_id` int(10) NOT NULL COMMENT '车辆数量',
  `vehicle_length_id` int(10) NOT NULL COMMENT '车辆长度',
  `delivery_call` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人电话',
  `delivery_time` datetime(6) NOT NULL COMMENT '发车时间',
  `user_id` int(10) NOT NULL COMMENT '车主信息',
  `status` smallint(1) NOT NULL COMMENT '状态0过期1正常',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `vehicle_type_id`(`vehicle_type_id`) USING BTREE,
  INDEX `vehicle_volume_id`(`vehicle_volume_id`) USING BTREE,
  INDEX `vehicle_length_id`(`vehicle_length_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `car_information_ibfk_1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `car_information_ibfk_2` FOREIGN KEY (`vehicle_volume_id`) REFERENCES `vehicle_volume` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `car_information_ibfk_3` FOREIGN KEY (`vehicle_length_id`) REFERENCES `vehicle_length` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `car_information_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car_information
-- ----------------------------
INSERT INTO `car_information` VALUES (1, '250105516', '李春亮', '广东省-深圳市-南山区', '天津市-天津市-宝坻区', '1111立方米', 2, 19, 2, '17853305102', '2019-01-30 00:00:00.000000', 2, 0);
INSERT INTO `car_information` VALUES (2, '516516161', '李春亮', '广东省-深圳市-南山区', '天津市-天津市-宝坻区', '1111立方米', 2, 19, 2, '17853305102', '2019-01-30 00:00:00.000000', 2, 1);
INSERT INTO `car_information` VALUES (4, '1548919697420', '李春亮', '山东省-青岛市-市南区', '湖北省-湖北省直辖县级行政单位-仙桃市', '15千克', 3, 18, 7, '17853305102', '2019-01-31 15:45:05.352000', 2, 1);
INSERT INTO `car_information` VALUES (5, '1548991058118', 'lichunliang', '西藏自治区-那曲地区-嘉黎县', '山东省-青岛市-李沧区', '55千克', 2, 18, 3, '17811111111', '2019-02-24 00:00:00.000000', 1, 1);

-- ----------------------------
-- Table structure for driving_license_information
-- ----------------------------
DROP TABLE IF EXISTS `driving_license_information`;
CREATE TABLE `driving_license_information`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `driving_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾驶人姓名',
  `driving_license_type_id` int(10) NOT NULL COMMENT '驾照类型',
  `driving_license_score_id` int(10) NOT NULL COMMENT '驾照分数',
  `driving_address_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在城市',
  `delivery_call` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货电话',
  `status` smallint(1) NOT NULL COMMENT '状态',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `driving_license_type_id`(`driving_license_type_id`) USING BTREE,
  INDEX `driving_license_score_id`(`driving_license_score_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `driving_license_information_ibfk_1` FOREIGN KEY (`driving_license_type_id`) REFERENCES `driving_license_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `driving_license_information_ibfk_2` FOREIGN KEY (`driving_license_score_id`) REFERENCES `driving_license_score` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `driving_license_information_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driving_license_information
-- ----------------------------
INSERT INTO `driving_license_information` VALUES (1, '李春亮', 3, 8, '浙江省-杭州市-上城区', '17853305102', 0, 2);
INSERT INTO `driving_license_information` VALUES (2, '李春亮', 4, 8, '广东省-广州市-海珠区', '17853305102', 1, 2);
INSERT INTO `driving_license_information` VALUES (3, '李春亮', 4, 11, '广东省-广州市-从化市', '17853305102', 0, 2);
INSERT INTO `driving_license_information` VALUES (4, '李春亮', 14, 9, '山东省-青岛市-市南区', '17853305102', 1, 2);
INSERT INTO `driving_license_information` VALUES (5, '李春亮', 2, 6, '山东省-青岛市-市南区', '17853305102', 0, 2);
INSERT INTO `driving_license_information` VALUES (6, 'lichunliang', 4, 8, '浙江省-杭州市-临安市', '17811111111', 1, 1);
INSERT INTO `driving_license_information` VALUES (8, '李春亮', 3, 2, '山东省-青岛市-崂山区', '17853305102', 1, 2);

-- ----------------------------
-- Table structure for driving_license_score
-- ----------------------------
DROP TABLE IF EXISTS `driving_license_score`;
CREATE TABLE `driving_license_score`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `driving_license_score_name` int(2) NOT NULL COMMENT '驾照分数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driving_license_score
-- ----------------------------
INSERT INTO `driving_license_score` VALUES (1, 1);
INSERT INTO `driving_license_score` VALUES (2, 2);
INSERT INTO `driving_license_score` VALUES (3, 3);
INSERT INTO `driving_license_score` VALUES (4, 4);
INSERT INTO `driving_license_score` VALUES (5, 5);
INSERT INTO `driving_license_score` VALUES (6, 6);
INSERT INTO `driving_license_score` VALUES (7, 7);
INSERT INTO `driving_license_score` VALUES (8, 8);
INSERT INTO `driving_license_score` VALUES (9, 9);
INSERT INTO `driving_license_score` VALUES (10, 10);
INSERT INTO `driving_license_score` VALUES (11, 11);
INSERT INTO `driving_license_score` VALUES (12, 12);

-- ----------------------------
-- Table structure for driving_license_type
-- ----------------------------
DROP TABLE IF EXISTS `driving_license_type`;
CREATE TABLE `driving_license_type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `driving_license_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾照类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driving_license_type
-- ----------------------------
INSERT INTO `driving_license_type` VALUES (1, 'A1');
INSERT INTO `driving_license_type` VALUES (2, 'A2');
INSERT INTO `driving_license_type` VALUES (3, 'A3');
INSERT INTO `driving_license_type` VALUES (4, 'B1');
INSERT INTO `driving_license_type` VALUES (5, 'B2');
INSERT INTO `driving_license_type` VALUES (6, 'C1');
INSERT INTO `driving_license_type` VALUES (7, 'C2');
INSERT INTO `driving_license_type` VALUES (8, 'C3');
INSERT INTO `driving_license_type` VALUES (9, 'C4');
INSERT INTO `driving_license_type` VALUES (10, 'D');
INSERT INTO `driving_license_type` VALUES (11, 'E');
INSERT INTO `driving_license_type` VALUES (12, 'F');
INSERT INTO `driving_license_type` VALUES (13, 'M');
INSERT INTO `driving_license_type` VALUES (14, 'N');
INSERT INTO `driving_license_type` VALUES (15, 'P');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言者姓名',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复',
  `release_time` datetime(6) NULL DEFAULT NULL COMMENT '留言发布时间，用来排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '马小兵', '新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接？', '新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接新广货运联盟实现了车找货、货找车的实时连接', '2019-02-02 12:38:39.000000');
INSERT INTO `message` VALUES (2, '马小跳', '测试留言1', '666', '2019-02-02 17:47:19.000000');
INSERT INTO `message` VALUES (3, '测试一号', '哈哈哈哈哈', '666', '2019-02-02 17:47:23.000000');
INSERT INTO `message` VALUES (4, '测试二号', '哟与忧郁爱福家阿尔法呢', '666', '2019-02-02 17:47:25.000000');

-- ----------------------------
-- Table structure for recruitment_information
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_information`;
CREATE TABLE `recruitment_information`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `identity_id` smallint(1) NOT NULL COMMENT '身份1招聘2应聘',
  `post_id` smallint(1) NOT NULL COMMENT '岗位1司机2其他',
  `corporate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `address_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司所在地址',
  `salary_id` int(10) NULL DEFAULT NULL COMMENT '薪资待遇',
  `age_id` int(10) NULL DEFAULT NULL COMMENT '年龄范围',
  `job_requirements` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位要求',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `user_id` int(10) NOT NULL,
  `status` smallint(1) NOT NULL COMMENT '状态1正常0过期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `salary_id`(`salary_id`) USING BTREE,
  INDEX `age_id`(`age_id`) USING BTREE,
  CONSTRAINT `recruitment_information_ibfk_1` FOREIGN KEY (`salary_id`) REFERENCES `salary_range` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `recruitment_information_ibfk_2` FOREIGN KEY (`age_id`) REFERENCES `age_range` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruitment_information
-- ----------------------------
INSERT INTO `recruitment_information` VALUES (1, 1, 1, '千峰教育一', '广东省-广州市-海珠区', 9, 3, 'java讲师', '17853305102', 2, 1);
INSERT INTO `recruitment_information` VALUES (2, 2, 2, '千峰教育2', '广东省-深圳市-南山区', 2, 1, '哈哈哈', '17853305102', 2, 0);
INSERT INTO `recruitment_information` VALUES (3, 2, 2, '千峰教育3', '广东省-深圳市-福田区', 5, 1, '哈哈哈', '17853305102', 2, 1);
INSERT INTO `recruitment_information` VALUES (4, 2, 2, '千峰教育4', '上海市-上海市-嘉定区', 6, 7, '6666', '17853305102', 2, 0);
INSERT INTO `recruitment_information` VALUES (5, 1, 1, '千峰教育5', '北京市-北京市-平谷区', 9, 5, '77777', '17853305102', 2, 1);
INSERT INTO `recruitment_information` VALUES (7, 2, 2, '666', '广东省-深圳市-宝安区', 2, 4, '666', '17853305102', 2, 1);

-- ----------------------------
-- Table structure for salary_range
-- ----------------------------
DROP TABLE IF EXISTS `salary_range`;
CREATE TABLE `salary_range`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `salary_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_range
-- ----------------------------
INSERT INTO `salary_range` VALUES (1, '面议');
INSERT INTO `salary_range` VALUES (2, '1000及以下');
INSERT INTO `salary_range` VALUES (3, '1001~2000');
INSERT INTO `salary_range` VALUES (4, '2001~3000');
INSERT INTO `salary_range` VALUES (5, '3001~4000');
INSERT INTO `salary_range` VALUES (6, '4001~5000');
INSERT INTO `salary_range` VALUES (7, '5001~6000');
INSERT INTO `salary_range` VALUES (8, '6001~7000');
INSERT INTO `salary_range` VALUES (9, '7000以上');

-- ----------------------------
-- Table structure for source_information
-- ----------------------------
DROP TABLE IF EXISTS `source_information`;
CREATE TABLE `source_information`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `shipper_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货主姓名',
  `departure_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发城市',
  `reaching_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '到达城市',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货物名称',
  `vehicle_type_id` int(10) NOT NULL COMMENT '车辆类型',
  `vehicle_volume_id` int(10) NOT NULL COMMENT '车辆数量',
  `delivery_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货电话',
  `weight_or_volume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重量(吨)',
  `vehicle_length_id` int(10) NOT NULL COMMENT '车辆长度',
  `freight` float(10, 2) NULL DEFAULT NULL COMMENT '运费',
  `pick_up_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货电话',
  `user_id` int(10) NOT NULL COMMENT '用户',
  `goods_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货物描述',
  `release_time` datetime(6) NOT NULL COMMENT '发布时间',
  `status` smallint(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `vehicle_type_id`(`vehicle_type_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `vehicle_volume_id`(`vehicle_volume_id`) USING BTREE,
  INDEX `vehicle_length_id`(`vehicle_length_id`) USING BTREE,
  CONSTRAINT `source_information_ibfk_1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `source_information_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `source_information_ibfk_3` FOREIGN KEY (`vehicle_volume_id`) REFERENCES `vehicle_volume` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `source_information_ibfk_4` FOREIGN KEY (`vehicle_length_id`) REFERENCES `vehicle_length` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of source_information
-- ----------------------------
INSERT INTO `source_information` VALUES (2, '李春亮', '浙江省-杭州市-临安市', '浙江省-杭州市-临安市', '大棍子', 1, 18, '17853305102', '12立方米', 5, 1235888.00, '', 2, NULL, '2019-01-31 11:58:59.952000', 0);
INSERT INTO `source_information` VALUES (3, '李春亮', '浙江省-宁波市-江东区', '河南省-郑州市-新密市', '蔬菜', 3, 9, '17853305102', '12千克', 2, 1235888.00, '', 2, '蔬菜', '2019-01-19 00:00:00.000000', 1);
INSERT INTO `source_information` VALUES (4, '李春亮', '广东省-广州市-黄埔区', '山东省-日照市-岚山区', '水果', 2, 18, '17853305102', '12千克', 5, 55555.00, '17853305102', 2, '水果', '2019-02-01 17:16:56.268000', 0);
INSERT INTO `source_information` VALUES (5, '李春亮', '浙江省-宁波市-海曙区', '安徽省-阜阳市-颍上县', '大西瓜', 1, 20, '17853305102', '12千克', 4, 99999.00, '17853305102', 2, '大西瓜', '2019-01-31 11:58:53.694000', 1);
INSERT INTO `source_information` VALUES (6, '李春亮', '广西壮族自治区-桂林市-雁山区', '湖北省-宜昌市-远安县', '军刀', 2, 15, '17853305102', '1111立方米', 7, 1888888.00, '17853305102', 2, '军刀', '2019-01-31 12:35:52.687000', 1);
INSERT INTO `source_information` VALUES (7, 'lichunliang', '浙江省-杭州市-富阳市', '四川省-眉山市-东坡区', '大棍子', 2, 17, '17811111111', '55立方米', 3, 89999.00, '17853305102', 1, '大棍子', '2019-01-31 13:00:30.172000', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `rel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `card` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `address_city` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `card`(`card`, `email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '17811111111', '123', 'lichunliang', '370112199711052011', '2690117811@qq.com', '山东济南');
INSERT INTO `user` VALUES (2, '17853305102', '123', '李春亮', '370112199711052014', '269011781@qq.com', '山东济南');

-- ----------------------------
-- Table structure for vehicle_length
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_length`;
CREATE TABLE `vehicle_length`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vehicle_length_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆长度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_length
-- ----------------------------
INSERT INTO `vehicle_length` VALUES (1, '零担');
INSERT INTO `vehicle_length` VALUES (2, '4.2米以下');
INSERT INTO `vehicle_length` VALUES (3, '4.2米');
INSERT INTO `vehicle_length` VALUES (4, '4.2米~6.8米');
INSERT INTO `vehicle_length` VALUES (5, '6.8米~9.6米');
INSERT INTO `vehicle_length` VALUES (6, '9.6米');
INSERT INTO `vehicle_length` VALUES (7, '9.6米~13米');
INSERT INTO `vehicle_length` VALUES (8, '13米');
INSERT INTO `vehicle_length` VALUES (9, '13米~17.5米');
INSERT INTO `vehicle_length` VALUES (10, '17.5米');

-- ----------------------------
-- Table structure for vehicle_transaction
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_transaction`;
CREATE TABLE `vehicle_transaction`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `identity_id` smallint(1) NOT NULL COMMENT '身份2卖车1买车',
  `vehicle_type_id` int(10) NOT NULL COMMENT '车辆类型',
  `vehicle_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆描述',
  `address_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在城市',
  `license_plate_time` datetime(6) NOT NULL COMMENT '首次上牌时间',
  `vehicle_length_id` int(10) NOT NULL COMMENT '车辆长度',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆图片',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `user_id` int(10) NOT NULL,
  `status` smallint(1) NOT NULL COMMENT '状态0过期1正常',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `vehicle_type_id`(`vehicle_type_id`) USING BTREE,
  INDEX `vehicle_length_id`(`vehicle_length_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `vehicle_transaction_ibfk_1` FOREIGN KEY (`vehicle_type_id`) REFERENCES `vehicle_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vehicle_transaction_ibfk_2` FOREIGN KEY (`vehicle_length_id`) REFERENCES `vehicle_length` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `vehicle_transaction_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_transaction
-- ----------------------------
INSERT INTO `vehicle_transaction` VALUES (1, 1, 4, '哈哈哈哈哈', '广东省-广州市-海珠区', '2019-02-11 00:00:00.000000', 2, '/images/5b7e6891edd5a.jpg', '17853305102', 2, 1);
INSERT INTO `vehicle_transaction` VALUES (2, 2, 2, '222', '广东省-广州市-海珠区', '2019-02-26 00:00:00.000000', 3, '/images/7ca561faab2441aa1b60ae534bc46b37.jpg', '17853305102', 2, 1);
INSERT INTO `vehicle_transaction` VALUES (3, 1, 1, '333', '广东省-广州市-黄埔区', '2019-02-27 00:00:00.000000', 9, '/images/7ca561faab2441aa1b60ae534bc46b37.jpg', '17853305102', 2, 1);
INSERT INTO `vehicle_transaction` VALUES (4, 2, 2, '444', '广东省-深圳市-南山区', '2019-02-04 00:00:00.000000', 4, '/images/211efd1ba5f7c1a3f1fe4b1d23e091d7.jpg', '17853305102', 2, 0);
INSERT INTO `vehicle_transaction` VALUES (5, 1, 2, '1111', '江苏省-苏州市-常熟市', '2019-02-19 00:00:00.000000', 1, '/images/589bff5e66a2c.jpg', '17853305102', 2, 1);
INSERT INTO `vehicle_transaction` VALUES (6, 2, 2, '111', '浙江省-杭州市-桐庐县', '2019-02-11 00:00:00.000000', 2, '/images/154537ujxutueesyj3mzt0.jpg', '17853305102', 2, 0);
INSERT INTO `vehicle_transaction` VALUES (7, 0, 2, '66', '广东省-广州市-海珠区', '2019-01-28 00:00:00.000000', 7, '/images/1111.jpg', '17853305102', 2, 1);
INSERT INTO `vehicle_transaction` VALUES (8, 1, 2, '111', '广东省-深圳市-罗湖区', '2019-01-28 00:00:00.000000', 2, '/images/5b7e6891edd5a.jpg', '17853305102', 2, 1);

-- ----------------------------
-- Table structure for vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_type`;
CREATE TABLE `vehicle_type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vehicle_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_type
-- ----------------------------
INSERT INTO `vehicle_type` VALUES (1, '高栏车');
INSERT INTO `vehicle_type` VALUES (2, '标箱车');
INSERT INTO `vehicle_type` VALUES (3, '平板车');
INSERT INTO `vehicle_type` VALUES (4, '其他');

-- ----------------------------
-- Table structure for vehicle_volume
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_volume`;
CREATE TABLE `vehicle_volume`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `vehicle_volume_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle_volume
-- ----------------------------
INSERT INTO `vehicle_volume` VALUES (1, '1辆');
INSERT INTO `vehicle_volume` VALUES (2, '2辆');
INSERT INTO `vehicle_volume` VALUES (3, '3辆');
INSERT INTO `vehicle_volume` VALUES (4, '4辆');
INSERT INTO `vehicle_volume` VALUES (5, '5辆');
INSERT INTO `vehicle_volume` VALUES (6, '6辆');
INSERT INTO `vehicle_volume` VALUES (7, '7辆');
INSERT INTO `vehicle_volume` VALUES (8, '8辆');
INSERT INTO `vehicle_volume` VALUES (9, '9辆');
INSERT INTO `vehicle_volume` VALUES (10, '10辆');
INSERT INTO `vehicle_volume` VALUES (11, '11辆');
INSERT INTO `vehicle_volume` VALUES (12, '12辆');
INSERT INTO `vehicle_volume` VALUES (13, '13辆');
INSERT INTO `vehicle_volume` VALUES (14, '14辆');
INSERT INTO `vehicle_volume` VALUES (15, '15辆');
INSERT INTO `vehicle_volume` VALUES (16, '16辆');
INSERT INTO `vehicle_volume` VALUES (17, '17辆');
INSERT INTO `vehicle_volume` VALUES (18, '18辆');
INSERT INTO `vehicle_volume` VALUES (19, '19辆');
INSERT INTO `vehicle_volume` VALUES (20, '20辆');

SET FOREIGN_KEY_CHECKS = 1;
