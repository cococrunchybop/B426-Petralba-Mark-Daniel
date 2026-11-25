/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.46 : Database - recording_studio_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`recording_studio_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `recording_studio_db`;

/*Table structure for table `albums` */

DROP TABLE IF EXISTS `albums`;

CREATE TABLE `albums` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `year` int NOT NULL,
  `artist_id` int NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_albums_artist` (`artist_id`),
  CONSTRAINT `fk_albums_artist` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `albums` */

insert  into `albums`(`id`,`name`,`year`,`artist_id`,`is_archived`) values 
(1,'PAUTANG NG PAG-IBIG',2022,1,0),
(2,'1st',2026,4,0),
(3,'IMPAKTO',2026,2,0),
(4,'For Keeps',2026,5,0),
(5,'Persona:2000',2026,3,0),
(22,'Coco',1990,1,0);

/*Table structure for table `artists` */

DROP TABLE IF EXISTS `artists`;

CREATE TABLE `artists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `artists` */

insert  into `artists`(`id`,`name`,`is_archived`) values 
(1,'Hev Abi',0),
(2,'Uncle Dags',0),
(3,'Fern.',0),
(4,'Gat Putch',0),
(5,'Flow G',0),
(22,'IV of Spades',0);

/*Table structure for table `playlist_songs` */

DROP TABLE IF EXISTS `playlist_songs`;

CREATE TABLE `playlist_songs` (
  `playlist_id` int NOT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`playlist_id`,`song_id`),
  KEY `fk_playlist_songs_song` (`song_id`),
  CONSTRAINT `fk_playlist_songs_playlist` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_playlist_songs_song` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `playlist_songs` */

insert  into `playlist_songs`(`playlist_id`,`song_id`) values 
(1,1),
(3,2),
(3,3),
(1,4),
(1,5),
(1,6),
(2,7),
(2,8),
(2,9),
(3,9),
(2,10);

/*Table structure for table `playlists` */

DROP TABLE IF EXISTS `playlists`;

CREATE TABLE `playlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_playlists_user` (`user_id`),
  CONSTRAINT `fk_playlists_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `playlists` */

insert  into `playlists`(`id`,`name`,`date_created`,`user_id`,`is_archived`) values 
(1,'My Hip-Hop Favorites','2026-11-18 18:30:00',2,0),
(2,'Late Night Chill','2026-11-18 18:32:00',3,0),
(3,'Admin Picks','2026-11-18 18:35:00',1,0);

/*Table structure for table `songs` */

DROP TABLE IF EXISTS `songs`;

CREATE TABLE `songs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `genre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `length` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `album_id` int NOT NULL,
  `is_archived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_songs_album` (`album_id`),
  CONSTRAINT `fk_songs_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `songs` */

insert  into `songs`(`id`,`title`,`genre`,`length`,`album_id`,`is_archived`) values 
(1,'PWEDE BANG?','Hip-Hop','2:24',1,0),
(2,'ICED COFFEE O MANGO SHAKE','Hip-Hop','3:43',1,0),
(3,'Patagal Nang Patagal ','Hip-Hop','2:47',2,0),
(4,'Homie','Hip-Hop','4:04',2,0),
(5,'Dugkat','Hip-Hop','3:04',3,0),
(6,'Origimal','Hip-Hop','2:38',3,0),
(7,'Ikaw Lang Din','Hip-Hop','4:05',4,0),
(8,'Sayo Na Nga','Hip-Hop','3:03',4,0),
(9,'GILIW','Hip-Hop','3:49',5,0),
(10,'Care 4 U','R&B','2:06',5,0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'USER',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `chk_user_role` CHECK ((`role` in (_utf8mb4'USER',_utf8mb4'ADMIN')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`email`,`role`,`created_at`,`is_archived`) values 
(1,'admin','$2a$10$Xe50xrDtRAPa66ChUdrkb.lTdxhW7wUxGZwvSk8p/obW9NEoXk4oS','admin@example.com','ADMIN','2026-11-18 18:20:00',0),
(2,'juandelacruz','$2a$10$FHG0zbXV2xIQ9ZX5ED3Hn.gvhEJxVN/3qHrhvyfdoR9.2iAdefJQ.','juan@example.com','USER','2026-11-18 18:22:00',0),
(3,'mariasantos','$2a$10$2BfktGSKQgelMNEQZ4oSH.tzrv51/wQ2EHUkaebTx13Q7wktkEFdG','maria@example.com','USER','2026-11-18 18:25:00',0),
(4,'user','$2a$10$FHo7GMNc3Ykys39rXUWKxukZyRTIOU39/UX.cPCcACDhAabCn0lam','user@g.com','USER','2026-11-23 19:50:11',0),
(5,'admin1','admin1','admin@g.com','ADMIN','2026-11-23 20:22:17',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
