
pssh -i -h hosts.txt -t 100000000000 -x "-oStrictHostKeyChecking=no -i new.pem" /home/ubuntu/DHT/./scrip.sh >/home/jay2106/output.txt
java -jar Result.jar output.txt > result.txt


