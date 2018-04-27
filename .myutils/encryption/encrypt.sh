#!/bin/bash

if [ $# -eq 0 ]; then
		echo "Usage: ./encrypt <file>"
		exit 1
fi

FILE=$1

openssl aes-256-cbc -a -salt -in "$FILE" -out $FILE.enc

rm -P $FILE
if [ -f $FILE ]; then
	echo "ERROR: $FILE was not deleted"
	echo "Delete it securely with:  rm -P $FILE"
	exit 1
fi

echo "Sucessfully encrypted"
