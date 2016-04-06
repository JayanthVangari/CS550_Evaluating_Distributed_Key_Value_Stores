"neighbor.conf" containing the ipaddresses and default port "50000" of all the nodes in the cluster should be exported to nodes(done int he zht_sc.sh script).

the scripts zht_exec.sh zht_execserver.sh should be in the "ZHT/src/" folder on the remote aws instance.

the script zht_sc.sh is executed on the local host machine that starts the zhtserver and then executes the client benchmark "./zht_ben" script.

zht_result.jar is jar for Zht_Result.java that calculates the results and outputs to result.txt file.
