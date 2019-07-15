CREATE TABLE `reader` (
  `username` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `reader_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs18x37wsirnegkj90wnxwdg0g` (`reader_username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

INSERT INTO `test`.`reader` (`username`, `fullname`, `password`, `role`) 
VALUES ('craig', 'Craig Walls', '$2a$10$usGif1C5fxfqGNyGrIKvbOP6TX554s7qxPhJuwOOIzVptwFvqBDgS', 'ROLE_READER');