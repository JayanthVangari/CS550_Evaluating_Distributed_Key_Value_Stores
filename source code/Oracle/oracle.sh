#!/bin/bash 

pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/config /home/ubuntu/config
pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/oracle.jar /home/ubuntu/oracle.jar
sleep 10
pssh -i -h hosts.txt -t 100000000000 -x "-i cassandra.pem" "java -jar /home/ubuntu/Oracle/oracle.jar jstore 16	" >/home/jay2106/Desktop/output.txt	
java -jar Result.jar output.txt > result.txt
