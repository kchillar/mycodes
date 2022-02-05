url=http://localhost:8080/graphql
echo ""
echo "Posting data from file: $1 to URL: $url"
echo ""
echo "Contents of the file $1 posted as Request:"
cat $1
echo ""
echo "Contents of Response:"
curl  -H "Content-Type: application/json"  --data "@$1" $url
#curl -v  -H "Content-Type: application/json"  --data "@$1" $url
