SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(256) NOT NULL,
  `netto` varchar(256) NOT NULL,
  `vat` varchar(256) NOT NULL
);

ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

INSERT INTO products ( name , netto , vat) VALUES ( 'Chair' , '1000' , '23') ;
INSERT INTO products ( name , netto , vat) VALUES ( 'Desk' , '500' , '23') ;
