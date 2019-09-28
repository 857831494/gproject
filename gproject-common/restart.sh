#!/bin/sh
cd /home/cluster

./logic.sh stop
./match.sh stop
./battle.sh stop
./rank.sh stop
./status.sh stop
./cluster.sh stop

sleep 10s
./cluster.sh start

sleep 10s
./match.sh start
./status.sh start
./battle.sh start
./logic.sh start
./rank.sh start

sleep 10
tail -500 ./logs/interstellar-logic.log 


