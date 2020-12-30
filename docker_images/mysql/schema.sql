CREATE TABLE `restaurant` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
                              `category` varchar(100) COLLATE utf8mb4_bin NOT NULL,
                              `gu` varchar(100) COLLATE utf8mb4_bin NOT NULL,
                              `address` varchar(100) COLLATE utf8mb4_bin NOT NULL,
                              `road_address` varchar(45) COLLATE utf8mb4_bin NOT NULL,
                              `additional_info` text COLLATE utf8mb4_bin NOT NULL,
                              `sha256_hash` varchar(32) COLLATE utf8mb4_bin NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `name_idx` (`name`) /*!80000 INVISIBLE */,
                              KEY `gu_idx` (`gu`),
                              KEY `hash_idx` (`sha256_hash`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
