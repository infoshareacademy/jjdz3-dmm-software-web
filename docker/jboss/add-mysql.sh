#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh

LAUNCH_JBOSS_IN_BACKGROUND=1
$JBOSS_HOME/bin/standalone.sh -b 0.0.0.0 &
until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    $JBOSS_CLI -c ":read-attribute(name=server-state)"
    sleep 1
done
echo "server started, create module"

$JBOSS_CLI -c << EOF
batch

module add --name=com.mysql --resources=/mysql-connector-java.jar --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)

data-source add \
        --name=datasources/isaDB \
        --connection-url="jdbc:mysql://sql:3306/dmm_finance_db?characterEncoding=utf8" \
        --driver-name="mysql" \
        --jndi-name="java:/datasources/isaDB" \
        --password="admin" \
        --user-name="isa_user"

run-batch
EOF

echo "done, shutdown serwer"
$JBOSS_CLI -c ":shutdown"
