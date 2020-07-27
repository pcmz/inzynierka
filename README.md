# inzynierka

## How to run?
Download Keycloak Server from the site, go to /bin/standalone.bat (for Windows) or /bin/standalone.sh (for Unix based systems) and run.
Or execute a command

`sudo docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak`

Create a database called: northwind (for example in Mysql Workbench), parameters for database in the file: application.properties
Or execute a command

`sudo docker run --name pcmz-mysql2 -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=northwind -p 3306:3306 -d mysql`

Run the file: northwind-MySQL.sql

Run backend (run PracaInzynierskaApplication)

Download Angular from the site

Go to frontend directory and install dependencies: npm install

Execute the command (in the frontend directory) to start Angular: ng serve

Go on http://localhost:4200

#### Configure keycloak:
##### login as: admin/admin
##### add realm `E-Concrete Precasts`
##### In Login tab enable everything and select Require SSL to external requests
##### Click on Configure -> Clients and click `create` button
##### paste `e-Concrete precasts` as Client ID and save
##### Select angular-app from clients list and modify the following values in the Settings tab then save the page
Valid Redirect URIs: http://localhost:4200/*

BaseU URL: http://localhost:4200

Web Origins: *
##### Go to configure → Roles → Add Role and give RoleName as `Admin` 
##### Go to Manage → Users → Add User and fill fields and save
##### Select the created user from the Users list and navigate to the Credentials tab. Set the password.
##### Click on Role Mappings and bring Admin role from Available Roles to Assigned Roles


## Keycloak Server
Configuration access data: root, admin

login data: sylvester, password