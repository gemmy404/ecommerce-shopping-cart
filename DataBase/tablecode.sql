/* To create products table */
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(450) NOT NULL,
  `category` varchar(450) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(450) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* To insert product data in table */
INSERT INTO `products` (`id`, `name`, `category`, `price`, `image`) VALUES
(1, 'Apple iPhone 13', 'Smartphone', 799.99, 'iphone13pro.jpg'),
(2, 'Keyboard and Mouse', 'Accessories', 149.99, 'keyboard_mouse.jpg'),
(3, 'PlayStation Controller', 'Gaming', 259.99, 'ps.jpg'),
(4, 'Apple MacBook Pro 16', 'Laptop', 2399.99, 'macbookpro.jpg'),
(5, 'Headphones', 'Accessories', 349.99, 'headphone.jpg'),
(6, 'iPhone Cover', 'Accessories', 199.99, 'applecover.jpg');

----------------------------------------------------------------------------------------------------------------------------

/* To create orders table */
CREATE TABLE `orders` (
  `o_id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NOT NULL,
  `u_id` int NOT NULL,
  `o_quantity` int NOT NULL,
  `o_date` varchar(450) NOT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

----------------------------------------------------------------------------------------------------------------------------

/* To create users table */
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;