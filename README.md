# inzynierka

## before application start run mysql as docker
`sudo docker run --name pcmz-mysql -e MYSQL_ROOT_PASSWORD=pcmzroot -e MYSQL_DATABASE=InzDb -e MYSQL_USER=pcmz-user -e MYSQL_PASSWORD=pcmzpassword -p 3307:3306 -d mysql`

## How to run?
Download Keycloak Server from the site, go to /bin/standalone.bat and run (for Windows)
Create a database called: northwind (for example in Mysql Workbench), parameters database in the file: application.properties
Run the file: northwind-MySQL.sql
Run backend (run PracaInzynierskaApplication)
Download Angular from the site
Go to frontend directory and install dependencies: npm install
Execute the command (in the frontend directory) to start Angular: ng serve
Go on http://localhost:4200

## Keycloak Server
Configuration access data: root, admin
login data: sylvester, password