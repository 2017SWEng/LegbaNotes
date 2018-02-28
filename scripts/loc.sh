echo "Lines of Code Analysis"
echo ""
echo ""
echo "Lines of code by extension"
sloc --format cli-table --keys total,source,comment,empty ../Notes/src/
echo ""
echo ""
echo "Lines of code by file"
sloc --details --format cli-table --keys total,source,comment,empty ../Notes/src/

