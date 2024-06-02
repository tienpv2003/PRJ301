
-- DROP DATABASE Fruit_Shop;
CREATE DATABASE FruitShop;
GO
USE FruitShop;
GO

--drop table users
-- Users Table
CREATE TABLE users (
  id INT IDENTITY(1,1) PRIMARY KEY,
  first_name NVARCHAR(100) NOT NULL,
  last_name NVARCHAR(100) NOT NULL,
  phone_number VARCHAR(15) UNIQUE NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  passwordHash VARCHAR(256) NOT NULL, --256 do thuật toán mã hóa sẽ tăng ki tu passwordhash lên
  is_admin BIT NOT NULL DEFAULT 0,
  registered_at DATETIME NOT NULL DEFAULT GETDATE(),
);
GO
--drop table categories
-- Categories Table
CREATE TABLE categories (
   id INT PRIMARY KEY IDENTITY(1,1),
   name VARCHAR(255) NOT NULL UNIQUE
);
GO

--drop table products
-- Products Table
CREATE TABLE products (
   id INT PRIMARY KEY IDENTITY(1,1),
   c_id INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   image VARCHAR(255),
   price FLOAT CHECK (price >= 0),
   weight FLOAT NOT NULL CHECK (weight > 0), -- Assuming weight is a positive float
   origin VARCHAR(255) NOT NULL, -- Assuming origin is a string
   quality VARCHAR(50), -- Assuming quality is a descriptive string, nullable if you prefer
   isPassed BIT NOT NULL, -- Assuming isPassed is a boolean indicating pass/fail status
   quantity INT NOT NULL,
   description VARCHAR(255),
   FOREIGN KEY (c_id) REFERENCES categories(id)
);
GO

--drop table orders	
CREATE TABLE orders (
   id INT PRIMARY KEY IDENTITY(1,1),
   u_id INT NOT NULL,
   order_date DATE NOT NULL DEFAULT GETDATE(),
   total_price FLOAT CHECK (total_price >= 0), 
   FOREIGN KEY (u_id) REFERENCES users(id)
);
GO

--drop table order_details
-- Order Details Table
CREATE TABLE order_details (
   id INT PRIMARY KEY IDENTITY(1,1),
   o_id INT NOT NULL,
   p_id INT NOT NULL,
   quantity INT NOT NULL CHECK (quantity > 0),
   price FLOAT NOT NULL CHECK (price >= 0),
   FOREIGN KEY (o_id) REFERENCES orders(id),
   FOREIGN KEY (p_id) REFERENCES products(id)
);
GO
--Insert product
insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(1, 'Apple','https://tieudung.kinhtedothi.vn/upload_images/images/2020/10/10/tao.jpg',1.5,1.3,'VietNam','sweet, crispy',0,20,'Apples may lower your chance of developing cancer, 
diabetes, and heart disease. Research says apples may also help you lose
weight while improving your gut and brain health')
insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(1, 'Orange','https://www.allrecipes.com/thmb/y_uvjwXWAuD6T0RxaS19jFvZyFU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-1205638014-2000-d0fbf9170f2d43eeb046f56eec65319c.jpg',2,2.2,'VietNam','sweet, crispy',0,30,'Oranges are a nutritional powerhouse, 
packed with vitamins and minerals. The most noteworthy of 
these is vitamin C, a water-soluble 
antioxidant that prevents cell damage')
insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(1, 'Blueberry','https://images-prod.healthline.com/hlcmsresource/images/AN_images/blueberries-1296x728-feature.jpg',5,2.2,'VietNam','sweet, crispy',0,50,'Blueberries are often 
called a  superfood. This small but mighty berry 
is loaded with nutrients')
insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(1, 'Banana','https://images.everydayhealth.com/images/diet-nutrition/all-about-bananas-nutrition-facts-health-benefits-recipes-and-more-rm-722x406.jpg',3,1.2,'ThaiLand','sweet,tasty',1,100,'Bananas are best known for containing potassium, which is a big 
player in heart health.')
insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(1, 'Mango','https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2022/01/mangoes_what_to_know_732x549_thumb-732x549.jpg',4,4.5,'ThaiLand','sweet,tasty',1,10,'Mangoes are rich in vitamins, minerals, 
and other beneficial plant compounds. These nutrients may help 
protect against cell damage, 
improve sexual health, support immunity, and more')
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Cherry', 'https://sunsay.vn/wp-content/uploads/2022/01/cherry-say-2.png', 5.0, 1, 'Local', 'sweet and juicy', 1, 12, 'Cherries are small, round fruits that come in various colors. They are packed with antioxidants, vitamins, and minerals, promoting heart health and reducing inflammation.');
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Grape', 'https://img.imageboss.me/fourwinds/width/425/dpr:2/shop/products/blackmonukka.jpg?v=1538780984', 3.5, 0.5, 'Local', 'crisp and flavorful', 1, 18, 'Grapes are small, juicy fruits that come in various colors. They are rich in antioxidants, promoting heart health and supporting the immune system.');
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Rambutan', 'https://vietnam.travel/sites/default/files/inline-images/fruits%20in%20vietnam-5.jpg', 6.0, 0.3, 'Vietnam', 'sweet and exotic', 1, 15, 'Rambutan is a tropical fruit with hairy skin and sweet, juicy flesh. It is rich in vitamins and minerals, providing a refreshing taste.');
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Kiwi', 'https://cdn.tgdd.vn/Files/2018/03/15/1074573/6-loi-ich-bat-ngo-cua-trai-kiwi-202110211628557582.jpg', 2.5, 0.8, 'New Zealand', 'tangy and vitamin-rich', 1, 20, 'Kiwi (Actinidia deliciosa) is a small, brown, fuzzy fruit with green flesh. It is a good source of vitamin C, K, and dietary fiber, promoting digestive health.');
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Peach', 'https://draxe.com/wp-content/uploads/2016/08/DrAxePeachFB.jpg', 4.0, 0.7, 'USA', 'sweet and fragrant', 1, 14, 'Peaches are soft, fuzzy fruits with a sweet and juicy taste. They are rich in vitamins A and C, supporting skin health and immune function.');
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (1, 'Watermelon', 'https://media1.nguoiduatin.vn/media/vu-thu-huong/2022/07/31/dua-hau.jpeg', 3.0, 5, 'Local', 'refreshing and hydrating', 1, 10, 'Watermelon is a juicy and hydrating fruit with a sweet taste. It is rich in vitamins A and C, and its high water content helps keep you hydrated.');

--insert vegettables

insert into products (c_id,name,image, price,weight,origin,quality, isPassed,quantity, description)
values(2, 'Cabbage','https://d3qz1qhhp9wxfa.cloudfront.net/growingproduce/wp-content/uploads/2023/07/w_Manessa_Cabbage_Rijk-Zwaan_gallery.jpg',2.5,5,'China','delicious and good for the body',0,15,'Cabbage (Brassica oleracea) is a cruciferous vegetable. 
It is a leafy green or purple biennial plant, grown as an annual vegetable crop for its dense-leaved heads. 
Very firm, small heads are used for canning')
-- Inserting Cauliflower
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (2, 'Cauliflower', 'https://jinjiangfoods.com/wp-content/uploads/2018/08/jinjiangfoods-shop-cavolfiore.jpg', 3.0, 3, 'Local', 'fresh and nutritious', 0, 20, 'Cauliflower (Brassica oleracea var. botrytis) is a cruciferous vegetable. It consists of a compact head of undeveloped white flower buds. Cauliflower is low in calories and high in nutrients.');

-- Inserting Carrots
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (2, 'Carrots', 'https://static.toiimg.com/photo/105672842.cms', 1.5, 2, 'Local', 'crisp and sweet', 0, 25, 'Carrots (Daucus carota) are root vegetables that come in various colors, including orange, purple, and yellow. They are rich in beta-carotene and promote eye health.');

-- Inserting Tomatoes
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (2, 'Tomatoes', 'https://cdn.tgdd.vn/Files/2017/10/30/1037058/9-cong-dung-va-han-che-cua-ca-chua-doi-voi-cuoc-song-hang-ngay-202103142026330054.jpg', 2.0, 1, 'Local', 'juicy and flavorful', 0, 30, 'Tomatoes (Solanum lycopersicum) are red, pulpy fruits commonly used in salads and cooking. They are rich in vitamins, minerals, and antioxidants, contributing to overall health.');

-- Inserting Lettuce
INSERT INTO products (c_id, name, image, price, weight, origin, quality, isPassed, quantity, description)
VALUES (2, 'Lettuce', 'https://media.newyorker.com/photos/5b6b08d3a676470b4ea9b91f/4:3/w_1920,h_1440,c_limit/Rosner-Lettuce.jpg', 1.0, 0.5, 'Local', 'crisp and refreshing', 0, 40, 'Lettuce (Lactuca sativa) is a leafy green vegetable often used as a base for salads. It is low in calories and a good source of vitamins and minerals.');

--check
select*from products
select * from categories
update users set is_admin = 1 where id = 1;
insert into categories values ('Tubers')
select * from order_details
select * from orders