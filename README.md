# Food Waste Reduction Project
CST8288 Final Project

Documentation Link: Currently using OneDrive shared url, before submission will move `/doc` directory.

[High Level Desing Document](https://algonquinlivecom-my.sharepoint.com/:w:/g/personal/ko000029_algonquinlive_com/EVQ5xfhUNaFMoQyKD7muIwgBi2QLg9I28wtfvWoJ-joTlA?e=waj8BG)

[Class and Use Case Diagrams](https://algonquinlivecom-my.sharepoint.com/:u:/g/personal/ko000029_algonquinlive_com/EahOpQ2eXohHia3-2ofUVjMBMZLIqCfcU0Vd_6Zv1ZkM4w?e=8krcue)

[Place Holder]
## Overview
## How to Run on Local Machine
### Database

#### Steps

1. Go to MySQL Workbench create a new Database "FWRP"

2. create a new SQL File in MySQL Workbench

3. Copy the `src/main/resources/schema.sql` content and paste in the MySQL SQL File and run.

4. A new Database named FWRP and related tables will be created.


### Properties File

#### Steps
1. Create a `application.properties` in `src/main/resources`

```properties
user=<your_db_username>
pass=<your_db_password>
db=mysql
host=localhost
port=3306
dbname=FWRP
```
## Screen Shots
#### Login
#### Register
#### Retailer
#### Charitable Organization
#### Consumer

#### References