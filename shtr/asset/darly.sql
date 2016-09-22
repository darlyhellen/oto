-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 darly 的数据库结构
CREATE DATABASE IF NOT EXISTS `darly` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `darly`;


-- 导出  表 darly.admin_user 结构
CREATE TABLE IF NOT EXISTS `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  darly.admin_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
REPLACE INTO `admin_user` (`id`, `name`, `pass`) VALUES
	(1, '111', '123'),
	(2, '112', '123');
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;


-- 导出  表 darly.client_comp_homepage 结构
CREATE TABLE IF NOT EXISTS `client_comp_homepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comp_icon` varchar(255) NOT NULL,
  `comp_firstname` varchar(255) NOT NULL,
  `comp_secname` varchar(255) NOT NULL,
  `comp_description` int(11) NOT NULL,
  `comp_url` varchar(255) NOT NULL,
  `comp_action` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 正在导出表  darly.client_comp_homepage 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `client_comp_homepage` DISABLE KEYS */;
REPLACE INTO `client_comp_homepage` (`id`, `comp_icon`, `comp_firstname`, `comp_secname`, `comp_description`, `comp_url`, `comp_action`) VALUES
	(1, 'http://172.3.200.228:8080/shtrImage/homepage/ask_doc.png', '报告解读', '点击找医生快速解读（天气信息）', 0, 'com.darly.dlclent.ui.video.WeahterActivity', 0),
	(2, 'http://172.3.200.228:8080/shtrImage/homepage/yuemingyi.png', '约名医', '三甲名医帮你免费预约（纸牌游戏）', 1, 'com.darly.dlclent.ui.games.cards.GameCardActivity', 0),
	(3, 'http://172.3.200.228:8080/shtrImage/homepage/tijianyuyue.png', '体检预约', '游戏贪吃蛇', 1, 'com.darly.dlclent.ui.games.snake.Snake\r\n\r\n', 1),
	(4, 'http://172.3.200.228:8080/shtrImage/homepage/zhinengdaojian.png', '智能导检', '视频列表', 0, 'com.darly.dlclent.ui.video.VideoListActivity', 1),
	(5, 'http://172.3.200.228:8080/shtrImage/homepage/baogaochaxun.png', '报告查询', '计步器页面', 0, 'com.darly.dlclent.ui.pedometer.PedometerActivity\r\n\r\n', 1),
	(6, 'http://172.3.200.228:8080/shtrImage/homepage/baogaojiedu.png', '报告详情', '报告详情', 0, 'http://172.3.200.228:8080/shtr/web/islogin?url=interact.html', 1),
	(7, 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg', '广告1', '广告（非登录状态可用）', 0, 'com.darly.dlclent.ui.bluetooth.BluetoothChat', 2),
	(8, 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg', '广告2', '广告（非登录状态可用）', 0, 'com.darly.dlclent.ui.comment.CommentWithFloorActivity\r\n\r\n', 2),
	(9, 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg', '广告3', '广告（非登录状态可用）', 0, 'com.darly.dlclent.ui.games.pike.PinkerAcitvity\r\n\r\n', 2),
	(10, 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg', '广告4', '广告（非登录状态可用）', 0, 'com.darly.dlclent.ui.video.VideoListActivity', 2);
/*!40000 ALTER TABLE `client_comp_homepage` ENABLE KEYS */;


-- 导出  表 darly.client_theme 结构
CREATE TABLE IF NOT EXISTS `client_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `theme` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  darly.client_theme 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `client_theme` DISABLE KEYS */;
REPLACE INTO `client_theme` (`id`, `name`, `url`, `title`, `theme`) VALUES
	(1, '首页', 'http://172.3.200.228:8080/shtrImage/theme/ia_intel_baogaoxiangqing.png', 'main', 'http://172.3.200.228:8080/shtrImage/theme/home_bg.png'),
	(2, '空闲', 'http://172.3.200.228:8080/shtrImage/theme/ia_intel_baogaochaxun.png', 'list', 'http://172.3.200.228:8080/shtrImage/theme/home_bg.png'),
	(3, '展示', 'http://172.3.200.228:8080/shtrImage/theme/ia_intel_tijianyuyue.png', 'act', 'http://172.3.200.228:8080/shtrImage/theme/home_bg.png'),
	(4, '设置', 'http://172.3.200.228:8080/shtrImage/theme/icon_news_f.png', 'center', 'http://172.3.200.228:8080/shtrImage/theme/home_bg.png');
/*!40000 ALTER TABLE `client_theme` ENABLE KEYS */;


-- 导出  表 darly.client_video 结构
CREATE TABLE IF NOT EXISTS `client_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(255) NOT NULL,
  `video_url` varchar(255) NOT NULL,
  `video_image` varchar(255) NOT NULL,
  `video_descripe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  darly.client_video 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `client_video` DISABLE KEYS */;
REPLACE INTO `client_video` (`id`, `video_name`, `video_url`, `video_image`, `video_descripe`) VALUES
	(1, '铁锅', 'http://172.3.200.228:8080/shtrVideo/video.mp4', 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg', '《钢铁侠》是2008年上映的美国超级英雄电影，是漫威电影宇宙的首部电影，由乔恩·费儒执导，小罗伯特·唐尼及格温妮斯·帕特洛等主演。《钢铁侠》改编自“漫威漫画”中的经典故事，讲述了工业家及发明家托尼·斯塔克遭阴谋绑架，被迫制造最致命的武器，身受重伤的他却暗中制造了一套高科技盔甲，保护自己逃生，从此变身“钢铁侠”保卫地球。');
/*!40000 ALTER TABLE `client_video` ENABLE KEYS */;


-- 导出  表 darly.collection_cart_list_v 结构
CREATE TABLE IF NOT EXISTS `collection_cart_list_v` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.collection_cart_list_v 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `collection_cart_list_v` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection_cart_list_v` ENABLE KEYS */;


-- 导出  表 darly.hh_main_flower 结构
CREATE TABLE IF NOT EXISTS `hh_main_flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `goup` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `temp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 正在导出表  darly.hh_main_flower 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `hh_main_flower` DISABLE KEYS */;
REPLACE INTO `hh_main_flower` (`id`, `description`, `name`, `icon`, `url`, `goup`, `type`, `temp`) VALUES
	(1, '木荷（拉丁学名：Schima superba Gardn. et Champ.）是山茶科木荷树属大乔木，高25米，嫩枝通常无毛。喜光，幼年稍耐庇荫。其分布于浙江、福建、台湾、江西、湖南、广东、海南、广西、贵州等地。 木荷既是一种优良的绿化、用材树种，又是一种较好的耐火、抗火、难燃树种。木荷为中国珍贵的用材树种，树干通直，材质坚韧，结构细致，耐久用，易加工，是纺织工业中制作纱绽、纱管的上等材料；', '木荷', 'http://pic1.huashichang.com/2016/0723/08/5792c162cc7bd.jpg', 'http://www.aihuhua.com/huahui/muhe.html', 'FXL', NULL, NULL),
	(2, '美人蕉（学名：Canna indica L.）：多年生草本植物，高可达1.5米，全株绿色无毛，被蜡质白粉。具块状根茎。地上枝丛生。单叶互生；具鞘状的叶柄；叶片卵状长圆形。总状花序，花单生或对生；萼片3，绿白色，先端带红色；花冠大多红色，外轮退化雄蕊2-3枚，鲜红色；唇瓣披针形，弯曲；蒴果，长卵形，绿色，花、果期3-12月。 美人蕉是亚热带和热带常用的观花植物。喜温暖和充足的阳光，不耐寒。', '美人蕉', 'http://pic1.huashichang.com/2016/0721/06/578ff64081384.jpg', 'http://www.aihuhua.com/huahui/meirenjiao.html', 'FXL', NULL, NULL),
	(3, '春剑常称为正宗川兰，虽云、贵、川均有名品，但以川兰名品最名贵。多分布于四川、贵州和云南。生于杂木丛生山坡上多石之地，海拔1000～2500米。它是我国华西地区的一个独特的兰种，喜湿润清爽的生态环境，野生春剑常分布在高山常绿阔叶林带，多数生长于红壤腐质土中，喜微酸性土壤。', '春剑', 'http://pic1.huashichang.com/2016/0721/21/5790c923840e8.jpg', 'http://www.aihuhua.com/huahui/chunjian.html', 'FXL', NULL, NULL),
	(4, '大叶栀子，Gardenia jasminoides Ellis var. grandiflora Nakai.，属于茜草科栀子属物种，又名大花栀子、荷花栀子、牡丹栀子，为常绿灌木。', '大叶栀子花', 'http://pic1.huashichang.com/2016/0709/13/57808ff2c2700.jpg', 'http://www.aihuhua.com/huahui/dayezhizihua.html', 'FXL', NULL, NULL),
	(5, '亳菊是《中国药典》中以“亳”字命名的中药材之一，亳菊是菊花中的珍品，2014年被中国农业部登记为地理标志性农产品，《中药大辞典》中记载：“白菊主产安徽亳县，称亳菊，品质最佳”，亳菊疏风散热、解暑明目，多为春、夏两季用药，历年来为中医首选的菊花品种。', '亳菊', 'http://pic1.huashichang.com/2016/0518/00/573b469f85be7.jpg', 'http://www.aihuhua.com/huahui/boju.html', 'FXL', NULL, NULL),
	(6, '红玉兰为落叶大灌木。花枝开展。叶互生，倒卵形，尖端短而突尖。花大，单生枝顶，白色有香气，等片与花瓣相似，每三片排成一轮，呈钟状。聚合果圆筒状，红色至淡红褐色，果成熟后裂开，种子具鲜红色肉质状外种皮。 同属植物我国有30余种，均为优美的观花树木，各地常见栽培的有：白玉兰、黄玉兰、如意玉兰、二乔玉兰等。', '红玉兰', 'http://pic1.huashichang.com/2016/0412/01/570bdcc213d3d.jpg', 'http://www.aihuhua.com/huahui/hongyulan.html', 'FXL', NULL, NULL),
	(7, '百子莲是石蒜科百子莲属植物。宿根植物，盛夏至初秋开花，花色深蓝色或白色。有根状茎；叶线状披针形，近革质；花茎直立，高可达60厘米；伞形花序，有花10朵～50朵，花漏斗状，深蓝色或白色，花药最初为黄色，后变成黑色；花期7月～8月。喜温暖、湿润和充足的阳光。相对休眠期的冬季盆土应保持稍干燥；越冬温度不低于8℃。北方需温室越冬。温暖地区可庭园种植。有白花和花叶品种。', '百子莲', 'http://pic1.huashichang.com/2016/0525/00/574480df8d7d4.jpg', 'http://www.aihuhua.com/huahui/baizilian.html', 'FXL', NULL, NULL),
	(8, '夹竹桃（学名：Nerium indicum Mill.）夹竹桃族夹竹桃属常绿直立大灌木，高可达5米，枝条灰绿色，嫩枝条具稜，被微毛，老时毛脱落。叶3-4枚轮生，叶面深绿，叶背浅绿色，中脉在叶面陷入，叶柄扁平，聚伞花序顶生，花冠深红色或粉红色，花冠为单瓣呈5裂时，其花冠为漏斗状，种子长圆形，花期几乎全年，夏秋为最盛；果期一般在冬春季，栽培很少结果。 夹竹桃在中国各省区有栽培，尤以中国南方为多，常在...', '夹竹桃', 'http://pic1.huashichang.com/2016/0518/23/573c8b637662c.jpg', 'http://www.aihuhua.com/huahui/jiazhutao.html', 'FXL', NULL, NULL),
	(9, '光叶蔷薇（学名：Rosa wichurana Crep.）是攀援灌木，高3-5米，枝条平卧，节上易生根；小枝红褐色，皮刺小，常带紫红色，小叶5-7，叶片椭圆形、卵形或倒卵形，花多朵成伞房花序；苞片卵形，萼片披针形或卵状披针形，花瓣白色，有香味，倒卵形，果实球形或近球形，紫黑褐色，有光泽，花期4-7月，果期10-11月。 光叶蔷薇生长高度海拔150-500米。光叶蔷薇分布于中国、日本和朝鲜。', '光叶蔷薇', 'http://pic1.huashichang.com/2015/1223/23/567ac145b1e22.jpg', 'http://www.aihuhua.com/huahui/guangyeqiangwei.html', 'FXL', NULL, NULL),
	(10, '海娜花是千屈菜科、散沫花属无毛大灌木，高可达6米；小枝略呈4棱形。叶交互对生，薄革质，椭圆形或椭圆状披针形，顶端短尖，基部楔形或渐狭成叶柄，纤细，在两面微凸起。花极香，白色或玫瑰红色至朱红色，裂片阔卵状三角形；略长于萼裂，边缘内卷，有齿；花丝丝状，子房近球形，花柱丝状，略长于雄蕊，柱头钻状。蒴果扁球形，种子多数，肥厚，三角状尖塔形。海娜花的花期6-10月，果期12月。 ', '海娜花', 'http://pic1.huashichang.com/2016/0424/00/571ba4b7153f2.jpg', 'http://www.aihuhua.com/huahui/hainahua.html', 'FXL', NULL, NULL);
/*!40000 ALTER TABLE `hh_main_flower` ENABLE KEYS */;


-- 导出  表 darly.user_action_theme 结构
CREATE TABLE IF NOT EXISTS `user_action_theme` (
  `id` int(11) unsigned zerofill NOT NULL,
  `description` varchar(255) NOT NULL,
  `imagePath` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  darly.user_action_theme 的数据：~33 rows (大约)
/*!40000 ALTER TABLE `user_action_theme` DISABLE KEYS */;
REPLACE INTO `user_action_theme` (`id`, `description`, `imagePath`) VALUES
	(00000104233, '104233description', 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg'),
	(00000105063, '105063description', 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg'),
	(00000106713, '106713description', 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg'),
	(00000108017, '108017description', 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg'),
	(00000113670, '113670description', 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg'),
	(00000114481, '114481description', 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg'),
	(00000114604, '114604description', 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg'),
	(00000115574, '115574description', 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg'),
	(00000120836, '120836description', 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg'),
	(00000125521, '125521description', 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg'),
	(00000126318, '126318description', 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg'),
	(00000128241, '128241description', 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg'),
	(00000133143, '133143description', 'http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg'),
	(00000134071, '134071description', 'http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg'),
	(00000135807, '135807description', 'http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg'),
	(00000142300, '142300description', 'http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg'),
	(00000146655, '146655description', 'http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg'),
	(00000147844, '147844description', 'http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg'),
	(00000154845, '154845description', 'http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg'),
	(00000156375, '156375description', 'http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg'),
	(00000156652, '156652description', 'http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg'),
	(00000165378, '165378description', 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg'),
	(00000165608, '165608description', 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg'),
	(00000167041, '167041description', 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg'),
	(00000171506, '171506description', 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg'),
	(00000172373, '172373description', 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg'),
	(00000173780, '173780description', 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg'),
	(00000180350, '180350description', 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg'),
	(00000188204, '188204description', 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg'),
	(00000188441, '188441description', 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg'),
	(00000193888, '193888description', 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg'),
	(00000194170, '194170description', 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg'),
	(00000196031, '196031description', 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg');
/*!40000 ALTER TABLE `user_action_theme` ENABLE KEYS */;


-- 导出  表 darly.user_ecaccount 结构
CREATE TABLE IF NOT EXISTS `user_ecaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usertel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `subaccountsid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `voipaccount` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `datecreated` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `voippwd` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `subtoken` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_ecaccount 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_ecaccount` DISABLE KEYS */;
REPLACE INTO `user_ecaccount` (`id`, `usertel`, `subaccountsid`, `voipaccount`, `datecreated`, `voippwd`, `subtoken`) VALUES
	(1, '17682220956', 'b4dc5d25fd3011e5bb9bac853d9f54f2', '8002731900000021', '2016-04-08 10:22:13', '2BJG6gQj', 'f42838a1c4acd56687d5203b3d03d161'),
	(2, '17693547422', 'b03d2ee3ffbd11e5bb9bac853d9f54f2', '8002731900000022', '2016-04-11 16:16:27', 'DnPPuQCl', 'f21ac55c835da891e37634884027275a'),
	(3, '13891431454', '24556a60005411e6bb9bac853d9f54f2', '8002731900000023', '2016-04-12 10:13:26', 'fRpi6rKh', 'a8a595141c41e5ddb930d5db41f16ff9');
/*!40000 ALTER TABLE `user_ecaccount` ENABLE KEYS */;


-- 导出  表 darly.user_goods_pro 结构
CREATE TABLE IF NOT EXISTS `user_goods_pro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proid` int(11) NOT NULL,
  `commodityid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_goods_pro 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_goods_pro` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_goods_pro` ENABLE KEYS */;


-- 导出  表 darly.user_goods_property 结构
CREATE TABLE IF NOT EXISTS `user_goods_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `design` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `proid` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_goods_property 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_goods_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_goods_property` ENABLE KEYS */;


-- 导出  表 darly.user_goods_showimages 结构
CREATE TABLE IF NOT EXISTS `user_goods_showimages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `commodityid` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_goods_showimages 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_goods_showimages` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_goods_showimages` ENABLE KEYS */;


-- 导出  表 darly.user_home_carousel 结构
CREATE TABLE IF NOT EXISTS `user_home_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_home_carousel 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `user_home_carousel` DISABLE KEYS */;
REPLACE INTO `user_home_carousel` (`id`, `url`, `title`, `icon`, `type`) VALUES
	(1, 'http://www.aihuhua.com/huahui/jubandoulan.html', '巨瓣兜兰', 'http://imgsrc.baidu.com/baike/pic/item/f7246b600c3387445b9f8dcc520fd9f9d72aa02c.jpg', 'com.hellen.mvplogin.WebViewActivity'),
	(2, 'http://www.aihuhua.com/huahu/jiegumu.html', '接骨木', 'http://imgsrc.baidu.com/baike/pic/item/caef76094b36acaf7225bb547ed98d1000e99c54.jpg', 'com.hellen.mvplogin.WebViewActivity'),
	(3, 'http://www.aihuhua.com/huahui/fengxinzi.html', '风信子', 'http://imgsrc.baidu.com/baike/pic/item/7acb0a46f21fbe0941a3e1436f600c338644ade3.jpg', 'com.hellen.mvplogin.WebViewActivity');
/*!40000 ALTER TABLE `user_home_carousel` ENABLE KEYS */;


-- 导出  表 darly.user_home_goods 结构
CREATE TABLE IF NOT EXISTS `user_home_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(65,0) NOT NULL,
  `original` decimal(65,0) NOT NULL,
  `commodityid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `collect` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_home_goods 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_home_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_home_goods` ENABLE KEYS */;


-- 导出  表 darly.user_home_menu 结构
CREATE TABLE IF NOT EXISTS `user_home_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_home_menu 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `user_home_menu` DISABLE KEYS */;
REPLACE INTO `user_home_menu` (`id`, `url`, `title`, `icon`) VALUES
	(1, 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg', '林黛玉', 'http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg'),
	(2, 'http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg', '薛宝钗', 'http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg'),
	(3, 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg', '贾元春', 'http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg'),
	(4, 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg', '贾迎春', 'http://pic13.nipic.com/20110424/818468_090858462000_2.jpg'),
	(5, 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg', '贾探春', 'http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg'),
	(6, 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg', '贾惜春', 'http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg'),
	(7, 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg', '史湘云', 'http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg'),
	(8, 'http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg', '王熙凤', 'http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg'),
	(9, 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg', '贾巧姐', 'http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg'),
	(10, 'http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg', '秦可卿', 'http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg');
/*!40000 ALTER TABLE `user_home_menu` ENABLE KEYS */;


-- 导出  表 darly.user_table_address 结构
CREATE TABLE IF NOT EXISTS `user_table_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `province` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 NOT NULL,
  `district` varchar(255) CHARACTER SET utf8 NOT NULL,
  `zipcode` varchar(255) CHARACTER SET utf8 NOT NULL,
  `usertel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_table_address 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user_table_address` DISABLE KEYS */;
REPLACE INTO `user_table_address` (`id`, `name`, `tel`, `province`, `city`, `district`, `zipcode`, `usertel`) VALUES
	(1, '122222', '18255554444', '上海市', '上海市', '宝山区', '200000', '17682220956'),
	(2, '1', '18255554444', '福建省', '福州市', '仓山区', '350000', '17682220956'),
	(3, '221', '18255554444', '湖北省', '鄂州市', '鄂城区', '436000', '17682220956'),
	(4, '223', '18355554444', '青海省', '果洛藏族自治州', '班玛县', '814000', '17682220956'),
	(5, '宇辉', '18321127513', '陕西省', '西安市', '临潼区', '710000', '17693547422'),
	(6, '2u饿u', '13891464315', '青海省', '果洛藏族自治州', '班玛县', '814000', '13891431454');
/*!40000 ALTER TABLE `user_table_address` ENABLE KEYS */;


-- 导出  表 darly.user_user_info 结构
CREATE TABLE IF NOT EXISTS `user_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sim` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idcard` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `money` double(255,0) NOT NULL,
  `same` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 正在导出表  darly.user_user_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_user_info` DISABLE KEYS */;
REPLACE INTO `user_user_info` (`id`, `name`, `pass`, `sim`, `icon`, `idcard`, `money`, `same`, `sex`, `tel`, `token`, `login`) VALUES
	(1, '小编', '123', '+8617688248420', 'http://172.3.200.228:8080/ImageIcon/17682220956head.png', '610123198610036773', 0, 'true', '男', '17682220956', 'MTc2ODIyMjA5NTY=', 'true'),
	(2, '奶妈', '123', '+8617688248420', 'http://172.3.200.228:8080/ImageIcon/17693547422head.png', '610123198610036773', 0, 'true', '女', '17693547422', 'MTc2OTM1NDc0MjI=', 'false'),
	(3, '娜娜', '123', '18321127312', 'http://172.3.200.228:8080/ImageIcon/13891431454head.png', '511702197409284963', 0, 'false', '女', '13891431454', 'MTM4OTE0MzE0NTQ=', 'false');
/*!40000 ALTER TABLE `user_user_info` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
