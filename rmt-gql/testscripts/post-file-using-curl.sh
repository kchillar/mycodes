echo ""
echo ""
url=http://localhost:8080/graphql
echo "Posting data from file: $1 to URL: $url"
echo ""
curl  -H "Content-Type: application/json"  --data "@$1" $url
#curl -v  -H "Content-Type: application/json"  --data "@$1" $url
