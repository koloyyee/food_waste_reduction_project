# Setting up the project

### Database

#### Steps

1. Go to MySQL Workbench create a new Database "FWRP"

2. create a new SQL File in MySQL Workbench

3. Copy the `schema.sql` content and paste in the MySQL SQL File and run.

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
