DROP TABLE IF EXISTS prices;

CREATE TABLE prices (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    brand_id  NUMBER NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    priority NUMBER NOT NULL,
    price NUMBER NOT NULL,
    curr VARCHAR NOT NULL
);

INSERT INTO prices (brand_id, start_date, end_date,price_list,product_id,priority,price,curr) VALUES
  (1, parsedatetime('2020-06-14-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'),1,35455,0,35.50,'EUR'),
  (1, parsedatetime('2020-06-14-15.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-06-14-18.30.00', 'yyyy-MM-dd-hh.mm.ss'),2,35455,1,25.45,'EUR'),
  (1, parsedatetime('2020-06-15-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-06-15-11.00.00', 'yyyy-MM-dd-hh.mm.ss'),3,35455,1,30.50,'EUR'),
  (1, parsedatetime('2020-06-15-16.00.00', 'yyyy-MM-dd-hh.mm.ss'), parsedatetime('2020-12-31-23.59.59', 'yyyy-MM-dd-hh.mm.ss'),4,35455,1,38.95,'EUR');