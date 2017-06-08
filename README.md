DMMsoftware  Finance-app


#### Build & run

1 Install Docker on your OS.

2 Run MySQL in Docker container:

    $ sudo docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dmm_finance_db -e MYSQL_USER=isa_user -e MYSQL_PASSWORD=admin mysql

3 To build war and start application run Maven command from the project root:

    $ sudo mvn process-resources wildfly:run@startApp

To quit wildfly:
 
    $ CTRL+C

To clean maven last build:
  
    $ sudo mvn install clean


test pages:

servlet/jsp:
 
http://localhost:8080/financial-app/login   (Google+ auth)






