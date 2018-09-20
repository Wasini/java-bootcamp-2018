#!/bin/sh

args=""

usage()
{
    echo "./install.sh"
    echo "\t -t Run tests after building"
    echo ""
}

while [ "$1" != "" ]; do
    PARAM=`echo $1 | awk -F= '{print $1}'`
    VALUE=`echo $1 | awk -F= '{print $2}'`
    case $PARAM in
        -t)
            args="$args -DskipTests=false" 
            ;;
        *)
            echo "ERROR: unknown parameter \"$PARAM\""
            usage
            exit 1
            ;;
    esac
    shift
done

mvn clean install $args