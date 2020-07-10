appName=`ls|grep .jar$`
if [ -z $appName ]
then
    echo "Please check that this script and your jar-package is in the same directory!"
    exit 1
fi

killForceFlag=$2

function usage()
{
    echo "Usage: $0 {start|stop|restart|status|stop -f}"
    echo "Example: $0 start"
    exit 1
}

function start()
{
    count=`ps -ef |grep java|grep $appName|wc -l`
    if [ $count != 0 ];then
        echo "Maybe $appName is running, please check it..."
    else
        echo "The $appName is starting..."
        nohup java -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms2048M -Xmx2096M -jar $appName -Dloader.path=resources --server.port=8181 --spring.application.name=app-system >./logs/app-log.out &
        tailf ./logs/app-log.out
    fi
}

function stop()
{
    appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
    if [ -z $appId ]
    then
        echo "Maybe $appName not running, please check it..."
    else
        echo -n "The $appName is stopping..."
        if [ "$killForceFlag" == "-f" ]
        then 
            echo "by force"
            kill -9 $appId
        else
            echo
            kill $appId
        fi
    fi
}

function status()
{
    appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
    if [ -z $appId ] 
    then
        echo -e "\033[31m Not running \033[0m" 
    else
        echo -e "\033[32m Running [$appId] \033[0m" 
    fi
}

function restart()
{
    stop
    for i in {3..1}
    do
        echo -n "$i "
        sleep 1
    done
    echo 0
    start
}

function is_exist(){
  pid=`ps -ef|grep $appName|grep -v grep|awk '{print $2}' `
  #如果不存在返回1，存在返回0     
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}
 
case $1 in
    start)
    start;;

    stop)
    stop;;
    
    restart)
    restart;;
    
    status)
    status;;
    
    *)
    usage;;
esac

 