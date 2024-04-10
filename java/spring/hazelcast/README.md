# hazelcast with Spring

- demo apps that showcase how to create a Hazelcast cluster member and a client

- start one or more cluster members
	- by default Hazelcast uses auto-discovery, so the nodes will discover each other and will form a cluster

- start one or more clients
	- you can observe the behavior or Hazelcast by writing messages using one client and reading them on the other one
		```bash
		# write a message in Hazelcast
		curl -X POST -H "Content-Type: application/json" -d '{"message": "test"}' localhost:port/main		

		# read messages from Hazelcast
		curl localhost:port/main
		```
