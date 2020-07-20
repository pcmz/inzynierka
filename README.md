# inzynierka

## before application start run mysql as docker
`sudo docker run --name pcmz-mysql2 -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=northwind -p 3306:3306 -d mysql`

## How to run?
Download Keycloak Server from the site, go to /bin/standalone.bat (for Windows) or /bin/standalone.sh (for Unix based systems) and run
Create a database called: northwind (for example in Mysql Workbench), parameters for database in the file: application.properties
Run the file: northwind-MySQL.sql
Run backend (run PracaInzynierskaApplication)
Download Angular from the site
Go to frontend directory and install dependencies: npm install
Execute the command (in the frontend directory) to start Angular: ng serve
Go on http://localhost:4200

## Keycloak Server
Configuration access data: root, admin
login data: sylvester, password
