# demo
The project is a backend application for an online auction website. It is built using Java and the Spring Boot framework,
and uses Microsoft SQL Server database to store data.

The application provides several REST APIs for creating, updating, and retrieving products and campaigns.
Products represent items that are up for auction, while campaigns represent time-limited sales events that include a group of related products.

Users can place bids on products through the APIs, and the application keeps track of the highest bid for each product.
The application also provides an API for retrieving the product with the highest bid.

The project uses validation to ensure that all input data is properly formatted and within acceptable ranges.
It also uses exception handling to provide informative error messages in case of failures.

Overall, the application provides a reliable and scalable backend for an online auction website.


* prerequisites
1.java 17 - https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html 
2. gradle 7.6 - https://gradle.org/next-steps/?version=7.6&format=all
3. sql server - https://www.microsoft.com/en-us/sql-server/sql-server-downloads
4. you need to create DB with the query as the following:
-- Create the OnlineSponsoredAds database
CREATE DATABASE OnlineSponsoredAds;

USE OnlineSponsoredAds;

-- Create the product table
CREATE TABLE product (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    serial_number VARCHAR(255) NOT NULL
);

-- Create the campaign table
CREATE TABLE campaign (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    bid DECIMAL(10,2) NOT NULL
);

-- Create the campaign_product table to associate products with campaigns
CREATE TABLE campaign_product (
    campaign_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (campaign_id, product_id),
    FOREIGN KEY (campaign_id) REFERENCES campaign(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


  -- Insert 100 random products with titles "product1", "product2", etc.
INSERT INTO product (title, category, price, serial_number)
SELECT TOP 100 'product' + CAST(ROW_NUMBER() OVER (ORDER BY @@SPID) AS VARCHAR(10)),
       CASE FLOOR(RAND() * 5)
           WHEN 0 THEN 'Electronics'
           WHEN 1 THEN 'Clothing'
           WHEN 2 THEN 'Furniture'
           WHEN 3 THEN 'Food'
           ELSE 'Toys'
       END AS category,
       RAND() * 100 AS price,
       LEFT(CONVERT(NVARCHAR(36), NEWID()), 8) AS serial_number
FROM master..spt_values AS v1,
       master..spt_values AS v2;


when tha appliction is ready...
you can use Postman or cURL to send the Request
JSON Format:
{
  "name": "New Campaign",
  "startDate": "2022-05-01",
  "bid": 500.00,
  "productIds": [1, 2, 3]
}


Good Luck
