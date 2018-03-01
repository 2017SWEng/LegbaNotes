#!/bin/bash

mkdir -p loc/

REPORT=loc/report.txt
DATA=loc/data.csv

echo "==========================" > $REPORT
echo "= Lines of Code Analysis =" >> $REPORT
echo "==========================" >> $REPORT
echo "" >> $REPORT
echo "" >> $REPORT
echo "Lines of code by extension" >> $REPORT
echo "--------------------------" >> $REPORT
./node_modules/sloc/bin/sloc --format cli-table --keys total,source,comment,empty ../Notes/src/ >> $REPORT
echo "" >> $REPORT
echo "" >> $REPORT
echo "Lines of code by file" >> $REPORT
echo "---------------------" >> $REPORT
./node_modules/sloc/bin/sloc --details --format cli-table --keys total,source,comment,empty ../Notes/src/ >> $REPORT
./node_modules/sloc/bin/sloc --format csv --keys total,source,comment,empty ../Notes/src/ > $DATA

echo "=============================="
echo "=                            ="
echo "=       Lines of Code        ="
echo "=                            ="
echo "=============================="

echo "[+] report.txt"
cat loc/report.txt
echo "[-]"
echo "[+] data.csv"
cat loc/data.csv
echo "[-]"
