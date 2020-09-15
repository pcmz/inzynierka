DROP TABLE IF EXISTS customers, order_details, orders, products, carts, invoices, addresses, delivery_addresses, delivery, suppliers;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";


-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers`
(
    `CustomerID`    int(11) NOT NULL auto_increment,
    `CompanyName`   varchar(100) character set utf8 default NULL,
    `CustomerEmail` varchar(40)                     default NULL,
    `CustomerPhone` varchar(40)                     default NULL,
    `Address`       int(11)                         default NULL,
    `NIP`           varchar(10)                     default NULL,
    PRIMARY KEY (`CustomerID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `CompanyName`, `CustomerEmail`, `CustomerPhone`, `Address`, `NIP`)
VALUES (1, 'Jan Kowalski sp. z.o.o', 'jan.kowalski@jk', '123456789', 1, '6762281710'),
       (2, 'Jan Nowak sp. z.o.o', 'jan.nowak@jn', '987654321', 2, '6750002236'),
       (3, 'Bud-bud sp. z.o.o', 'bud.bud@bb', '123498765', 3, '6750006257');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders`
(
    `OrderID`    int(11) NOT NULL auto_increment,
    `CustomerID` varchar(5) default NULL,
    `CreateDate` DATETIME   default null,
    `UpdateDate` DATETIME   default null,
    PRIMARY KEY (`OrderID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE IF NOT EXISTS `invoices`
(
    `ID`                  int(11) NOT NULL auto_increment,
    `OrderID`             int(11)     default NULL,
    `FakturaXlIDProforma` varchar(40) default NULL,
    `InvoiceNameProforma` varchar(40) default NULL,
    `FakturaXlIDVat`      varchar(40) default NULL,
    `InvoiceNameVat`      varchar(40) default NULL,
    PRIMARY KEY (`ID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE IF NOT EXISTS `carts`
(
    `odID`      int(11) NOT NULL auto_increment,
    `ProductID` int(11)     default NULL,
    `Quantity`  smallint(6) default NULL,
    `Subtotal`  float(11)   default '0',
    PRIMARY KEY (`odID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

--
-- Table structure for table `order_details`
--

CREATE TABLE IF NOT EXISTS `order_details`
(
    `odID`      int(11) NOT NULL auto_increment,
    `OrderID`   int(11)     default NULL,
    `ProductID` int(11)     default NULL,
    `Quantity`  smallint(6) default NULL,
    `Subtotal`  float(11)   default '0',

    PRIMARY KEY (`odID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products`
(
    `ProductID`        int(11) NOT NULL auto_increment,
    `ProductName`      varchar(40)  default NULL,
    `Unit`             varchar(40)  default NULL,
    `SupplierID`       int(11)      default NULL,
    `QuantityPerUnit`  varchar(20)  default NULL,
    `UnitPrice`        float(11)    default '0',
    `Ipath`            varchar(250) default NULL,
    `AvailableColours` varchar(40)  default NULL,
    `Quantity`         int(11)      default NULL,
    `VAT`              int(3)       default '23',
    PRIMARY KEY (`ProductID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `ProductName`, `Unit`, `SupplierID`, `QuantityPerUnit`, `UnitPrice`, Ipath,
                        AvailableColours, Quantity, VAT)
VALUES (1, 'Cobble 10x20x4cm', 'm2', 1, '52 pieces', 20.92, 'assets/details/cobble.JPG', 'grey, red, yellow', 1000, 23),
       (2, 'Cobble 10x20x6cm', 'm2', 1, '52 pieces', 21.56, 'assets/details/cobble.JPG', 'grey, red, yellow', 900, 23),
       (3, 'Cobble 10x20x8cm', 'm2', 1, '52 pieces', 22.12, 'assets/details/cobble.JPG', 'grey, red, yellow', 1200, 23),
       (4, 'Edge 6x20x100cm', 'lm', 2, '1 piece', 10.12, 'assets/details/edge.JPG', 'grey', 850, 23),
       (5, 'Edge 6x25x100cm', 'lm', 2, '1 piece', 11.25, 'assets/details/edge.JPG', 'grey', 450, 23),
       (6, 'Edge 8x25x100cm', 'lm', 2, '1 piece', 12.31, 'assets/details/edge.JPG', 'grey', 1500, 23),
       (7, 'Edge 8x30x100cm', 'lm', 2, '1 piece', 13.89, 'assets/details/edge.JPG', 'grey', 120, 23),
       (8, 'Edge 6x20x50cm', 'lm', 2, '2 pieces', 11.42, 'assets/details/edge.JPG', 'grey', 45, 23),
       (9, 'Edge 6x25x50cm', 'lm', 2, '2 pieces', 12.55, 'assets/details/edge.JPG', 'grey', 120, 23),
       (10, 'Edge 8x25x50cm', 'lm', 2, '2 pieces', 13.61, 'assets/details/edge.JPG', 'grey', 180, 23),
       (11, 'Edge 8x30x50cm', 'lm', 2, '2 pieces', 15.19, 'assets/details/edge.JPG', 'grey', 200, 23),
       (12, 'Kerb 15x30x100cm ', 'lm', 3, '1 piece', 26.46, 'assets/details/kerb.JPG', 'grey', 890, 23),
       (13, 'Kerb 20x30x100cm ', 'lm', 3, '1 piece', 27.72, 'assets/details/kerb.JPG', 'grey', 150, 23),
       (14, 'Kerb 25x30x100cm ', 'lm', 3, '1 piece', 28.36, 'assets/details/kerb.JPG', 'grey', 490, 23);

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE IF NOT EXISTS `suppliers`
(
    `SupplierID`  int(11) NOT NULL auto_increment,
    `CompanyName` varchar(40) default NULL,
    PRIMARY KEY (`SupplierID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 30;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `CompanyName`)
VALUES (1, 'Own product'),
       (2, 'Company 1'),
       (3, 'Company 2');


CREATE TABLE IF NOT EXISTS `addresses`
(
    `AddressID`    int(11) NOT NULL auto_increment,
    `City`         varchar(40) character set utf8 default NULL,
    `Code`         varchar(40) character set utf8 default NULL,
    `Country`      varchar(40) character set utf8 default NULL,
    `House_number` varchar(40) character set utf8 default NULL,
    `Post_office`  varchar(40) character set utf8 default NULL,
    `Street`       varchar(40) character set utf8 default NULL,
    PRIMARY KEY (`AddressID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;

INSERT INTO `addresses` (`AddressID`, `City`, `Code`, `Country`, `House_number`, `Post_office`, `Street`)
VALUES (1, 'Cracow', '32-389', 'Poland', '1', 'Cracow', 'Domowa'),
       (2, 'Wroclaw', '90-123', 'Poland', '78', 'Wroclaw', 'Parkowa'),
       (3, 'Gdansk', '78-001', 'Poland', '63', 'Gdansk', 'Ogrodowa');


CREATE TABLE IF NOT EXISTS `delivery_addresses`
(
    `ID`           int(11) NOT NULL auto_increment,
    `City`         varchar(40) character set utf8 default NULL,
    `Code`         varchar(40) character set utf8 default NULL,
    `Country`      varchar(40) character set utf8 default NULL,
    `House_number` varchar(40) character set utf8 default NULL,
    `Post_office`  varchar(40) character set utf8 default NULL,
    `Street`       varchar(40) character set utf8 default NULL,
    PRIMARY KEY (`ID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;



CREATE TABLE IF NOT EXISTS `delivery`
(
    `ID`                int(11) NOT NULL auto_increment,
    `OrderID`           int(11)                        default NULL,
    `DeliveryAddressID` int(11)                        default NULL,
    `ExternalID`        varchar(40) character set utf8 default NULL,
    `State`             varchar(40) character set utf8 default NULL,
    PRIMARY KEY (`ID`)
) ENGINE = MyISAM
  DEFAULT CHARSET = latin1
  AUTO_INCREMENT = 1;
