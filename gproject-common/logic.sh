#!/bin/sh

if [ $# -ne 1 ]; then
   echo "Usage: $0 {start|stop|restart|status}"
   exit 1
fi

cd /home/competition
export JAVA_HOME=/ryzc/java
if [ ! -d "./logs" ]; then
  mkdir ./logs
fi

SERVERNAME=interstellar-logic
MAINCLASS=com.yaowan.interstellar.LogicServerStart
 
pid=0
getpid() {
   pid=`netstat -anp|grep 20001|awk '{printf $7}'|cut -d/ -f1`
}

start() {
	getpid

    RET=`expr match "$pid" "[0-9][0-9]*$"`
    if [[  ${RET} -gt 0 ]]; then
      echo "warn: $SERVERNAME already started! (pid=$pid)"
    else
		echo -e "Starting $SERVERNAME ...\n"

    if [ -f "./logs/$SERVERNAME.log" ]; then
      CURRENTTIME=$(date +%Y%m%d%H%M%S)
      mv "./logs/$SERVERNAME.log" ./logs/$SERVERNAME-$CURRENTTIME.log
    fi  
		nohup /ryzc/java/bin/java -cp "./$SERVERNAME:./$SERVERNAME.jar:./lib/*" $MAINCLASS >> ./logs/$SERVERNAME.log 2>&1 &
    sleep 5s
		tail -n 50 ./logs/$SERVERNAME.log
	fi	
}

stop() {
   getpid

  RET=`expr match "$pid" "[0-9][0-9]*$"`
    if [[  ${RET} -gt 0 ]]; then
	echo -n "Stopping $SERVERNAME ...(pid=$pid) "
      kill  $pid
      if [[ $? -eq 0 ]];then
         echo "[OK]"
      else
         echo "[Failed]"
      fi
   else
      echo "warn: $SERVERNAME is not running"
   fi
}

status() {
   getpid
 
   if [[ $pid -ne 0 ]];  then
      echo "$SERVERNAME is running! (pid=$pid)"
   else
      echo "$SERVERNAME is not running"
   fi
}

case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
	sleep 10s
     start
     ;;
   'status')
     status
     ;;
  *)
     echo "Usage: $0  {start|stop|restart|status}"
     exit 1
esac


