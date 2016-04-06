there should be a "config" file in the directory where cassandra_eval.jar is exported to nodes in the cluster ( this is handled in the cassandra.sh script).

the config file should contain the seed address, to which client driver connects when it is executed.

GettingStarted.java contains the source code for java client driver of cassandra.

run the script "Cassandra.sh" to start experiment.

Result.jar is the jar file for Result.java that calculates the Result of experiment and outputs to result.txt *look into Result.java if necessary*
