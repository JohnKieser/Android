#!/bin/bash

echo "Checking for app_name translations..."

FAILED=0

# Find all strings.xml files
find . -type f -name "strings.xml" | while read -r file; do
  # Get the value inside the app_name tag
  app_name_value=$(grep '<string name="app_name">' "$file" | sed -E 's/.*<string name="app_name">(.*)<\/string>.*/\1/')

  # Compare to expected value
  if [[ "$app_name_value" != "Catima" ]]; then
    echo "$file contains app_name = \"$app_name_value\" — must be 'Catima'"
    FAILED=1
  else
    echo "$file is OK"
  fi
done

exit $FAILED
