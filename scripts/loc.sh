echo "Lines of Code Analysis"
echo ""
echo ""
echo "Lines of code by extension"
./node_modules/sloc/bin/sloc --format cli-table --keys total,source,comment,empty ../Notes/src/
echo ""
echo ""
echo "Lines of code by file"
./node_modules/sloc/bin/sloc --details --format cli-table --keys total,source,comment,empty ../Notes/src/

