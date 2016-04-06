DKV_ant folder contains the ant build file for DKV server and client.

there should be a "config" file in the directory where DHT_ant is exported to nodes in the cluster

the config file contains the ipadrresses and ports of the nodes at which the DKV servers run. BOTH the DKV client and server read this to know the neighboring servers

start the server at aws instances and then

execute the script "DHT.sh"

Result.jar is the jar file for Result.java that calculates the Result of experiment and outputs to result.txt *look into Result.java if necessary*

