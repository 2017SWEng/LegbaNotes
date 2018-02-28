#!/bin/bash

REPORT=loc/report.txt
DATA=loc/data.csv
echo "Lines of Code Analysis" > $REPORT
echo "" >> $REPORT
echo "" >> $REPORT
echo "Lines of code by extension" >> $REPORT
./node_modules/sloc/bin/sloc --format cli-table --keys total,source,comment,empty ../Notes/src/ >> $REPORT
echo "" >> $REPORT
echo "" >> $REPORT
echo "Lines of code by file" >> $REPORT
./node_modules/sloc/bin/sloc --details --format cli-table --keys total,source,comment,empty ../Notes/src/ >> $REPORT
./node_modules/sloc/bin/sloc --format csv --keys total,source,comment,empty ../Notes/src/ > $DATA

echo "[+] report.txt"
cat loc/report.txt
echo "[-]"
echo "[+] data.csv"
cat loc/data.csv
echo "[-]"
