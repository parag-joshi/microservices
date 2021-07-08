DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subscribername` varchar(45) DEFAULT NULL,
  `datesubscribed` date DEFAULT NULL,
  `datereturned` date DEFAULT NULL,
  `bookid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='subscriptions table contains information about book subscriptions.';
