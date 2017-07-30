DMMsoftware  Finance-app


#### Build & run

1 Install Docker on your server. 

    
2 Run MySQL in Docker container:

    $ sudo docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=dmm_finance_db -e MYSQL_USER=isa_user -e MYSQL_PASSWORD=admin mysql

3 To build war and start application run Maven command from the project root:

    $ sudo mvn process-resources wildfly:run@startApp

To quit wildfly:
 
    $ CTRL+C

To clean maven last build:
  
    $ sudo mvn install clean

-------------------------------------------------------------------------------------
welcome page:
 
http://dmmsoft:8080/financial-app/login.jsp   (Google+ auth)

------------------------------------------------------------------------------------

If application runs on explicit LAN IP, add this line to your /etc/hosts file:
                               
"<host_ip> dmmsoft"

Note!
host_ip - is application host IP, for example "192.168.1.104" 

Note!
"dmmsoft" is host domain instead of "localhost". It is set to:
1. avoid Google+ authentication issues
2. to run several instances of application in LAN, with explicitly different IP 
(such setup allows application instances to communicate with each other) 

Note! 
Application default timezone is set to UTC.
MySQL database timezone should also be set to UTC.
Keep timezones synchronized to avoid LocalDate issues.

------------------------------------------------------------------------------------
Note!
Set proper <user_name> in Configuration.json (file inside jar dependency)

------------------------------------------------------------------------------------
Note!
Default application directories:

/home/<user_name>/dmmfinance/bossa/currencies/

/home/<user_name>/dmmfinance/bossa/funds/
 
/home/<user_name>/dmmfinance/bossa/backup/currencies/  (zip files location)

/home/<user_name>/dmmfinance/bossa/backup/funds/       (zip files location)

------------------------------------------------------------------------------------
Note! 
Add smtpconfig.json file to location: "/home/<user_name>/dmmfinance/smtpconfig.json"
Add webconfiguration.json file to location: "/home/<user_name>/dmmfinance/webconfiguration.json"

------------------------------------------------------------------------------------

------smtpconfig.json file example content:------------------------------------------

{
  "email":"xxxxx@gmail.com",
  
  "login":"xxxxx@gmail.com",
  
  "password":"xxxxxxxx",
  
  "smtpHost":"smtp.gmail.com",
  
  "smtpPort":"465",
  
  "targetEmail":"xxxxxx@xxxx.com"
}

------------------------------------------------------------------------------------


------webconfiguration.json file example content:-----------------------------------

{
  "slave":false,
  "masterModeAPIServiceTargetURI":"http://localhost:8080/financial-app/api",
  "slaveModeAPIServiceTargetURI":"http://192.168.1.104:8080/financial-app/api",
  "defaultAdminAccountLogin":"defaultadmin@gmail.com"
}

------------------------------------------------------------------------------------

