#!/bin/bash
pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/cassandra_eval.jar /home/ubuntu/cassandra_eval.jar
pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/config /home/ubuntu/config
sleep 10
pssh -i -h hosts.txt -t 100000000000 -x "-i cassandra.pem" sudo service cassandra stop
pssh -i -h hosts.txt -t 100000000000 -x "-i cassandra.pem" sudo rm -rf /var/lib/cassandra/data/system/*
pssh -i -h hosts.txt -t 100000000000 -x "-i cassandra.pem" sudo service cassandra start
sleep 60
pssh -i -h hosts.txt -t 100000000000 -x "-i cassandra.pem" "java -jar cassandra_eval.jar" >/home/jay2106/Desktop/output.txt
java -jar Result.jar output.txt > result.txt
