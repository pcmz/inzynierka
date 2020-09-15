# study project

## How to run?
Download Keycloak Server from the site, go to /bin/standalone.bat and run (for Windows). Then import realm from `keycloak-exported-realm.json` file.

Create a database called: northwind (for example in Mysql Workbench), parameters database in the file: application.properties

Run backend (run PracaInzynierskaApplication)

Download Angular from the site

Go to frontend directory and install dependencies: npm install

Execute the command (in the frontend directory) to start Angular: ng serve

Go on http://localhost:4200

## Keycloak Server
#### Admin: 
website: http://localhost:8080

login: root

password: admin

#### User:
website: http://localhost:4200

login: test@example.com

password: 123