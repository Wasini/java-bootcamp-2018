#!/bin/sh
(cd logging-deps/ && mvn clean install) \
& (cd testing-deps/ && mvn clean install) \
&& cd my-app/ && mvn clean install