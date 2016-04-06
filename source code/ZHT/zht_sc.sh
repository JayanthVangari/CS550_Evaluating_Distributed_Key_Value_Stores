pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/zht_execserver.sh /home/ubuntu/ZHT/src/zht_execserver.sh
pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/zht_exec.sh /home/ubuntu/ZHT/src/zht_exec.sh
pscp -h hosts.txt -x "-oStrictHostKeyChecking=no -i cassandra.pem" -r /home/jay2106/neighbor.conf /home/ubuntu/ZHT/src/neighbor.conf
sleep 5
pssh -i -h hosts.txt -t 100000000000 -x "-oStrictHostKeyChecking=no -i cassandra.pem" /home/ubuntu/./zht_execserver.sh 
sleep 10
pssh -i -h hosts.txt -t 100000000000 -x "-oStrictHostKeyChecking=no -i cassandra.pem" /home/ubuntu/./zht_exec.sh >/home/jay2106/output.txt

java -jar zht_result.jar output.txt > /home/jay2106/result.txt
