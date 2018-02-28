
mkdir -p test/

REPORT="test/report.txt"
DATA="test/data.csv"

echo "================" > $REPORT
echo "= Tests Report =" >> $REPORT
echo "================" >> $REPORT
echo "" >> $REPORT
echo "" >> $REPORT
echo "List of tests" >> $REPORT
echo "------------" >> $REPORT
TESTS=$( grep -ohP "classname=\"([\w.]+)\" name=\"([\w_]+)\""  ./../Notes/target/surefire-reports/*.xml | sed s/classname=\"//g | sed 's/\" name=\"/./g' | sed s/\"/\(\)/g )
echo "$TESTS" >> $REPORT

NUMTEST=$("$TESTS" | wc -l)
echo "" >> $REPORT
echo "" >> $REPORT
echo "Number of tests" >> $REPORT
echo "---------------" >> $REPORT
echo $NUMTEST >> $REPORT

NUMFILES=$(ls ./../Notes/src/test/java/com/legba/notes/**/**.java -l | wc -l)
echo "" >> $REPORT
echo "" >> $REPORT
echo "Number of test files" >> $REPORT
echo "--------------------" >> $REPORT
echo $NUMFILES >> $REPORT

echo "tests	files" > $DATA
echo "$NUMTEST	$NUMFILES" >> $DATA

echo "===================================="
echo "=                                  ="
echo "=         TESTING REPORT           ="
echo "=                                  ="
echo "===================================="
echo "[+]"
cat $REPORT
echo "[-]"

echo "[+]"
cat $DATA
echo "[-]"
