#!/bin/bash

if [ $# -eq 0 ]; then
		echo "Usage: ./decrypt <file>"
		exit 1
fi

FILE=$1
EXT=${FILE##*.}
FILEROOT=${FILE%.*}

if [ "$EXT" != "enc" ]; then
	echo "ERROR: Can only decrypt files with .enc extension"
	exit 1
fi

openssl aes-256-cbc -d -a -in $FILE -out $FILEROOT

echo "Sucessfully decrypted"
