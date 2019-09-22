drop database if exists test;
create database test;
use test;
CREATE TABLE `products` (
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test`.`products`
(`product_id`,
`product_name`,
`price`)
VALUES
('100', 'product1', 100);

INSERT INTO `test`.`products`
(`product_id`,
`product_name`,
`price`)
VALUES
('101', 'product2', 200);

INSERT INTO `test`.`products`
(`product_id`,
`product_name`,
`price`)
VALUES
('102', 'product3', 300);

CREATE TABLE `ingredients` (
  `ingredient_id` varchar(45) NOT NULL,
  `ingredient_name` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';

INSERT INTO `test`.`ingredients`
(`ingredient_id`,
`ingredient_name`,
`price`,
`color`)
VALUES
('10', 'INGR1', 10, 'RED');

INSERT INTO `test`.`ingredients`
(`ingredient_id`,
`ingredient_name`,
`price`,
`color`)
VALUES
('11', 'INGR2', 20, 'GREEN');

INSERT INTO `test`.`ingredients`
(`ingredient_id`,
`ingredient_name`,
`price`,
`color`)
VALUES
('12', 'INGR3', 30, 'BLUE');

CREATE TABLE `orders` (
  `order_id` varchar(45) NOT NULL,
  `price` float DEFAULT NULL,
  `product_id` varchar(45),
  `ingredient_id` varchar(45), 
  `email` varchar(45),
  `tel` varchar(45),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

