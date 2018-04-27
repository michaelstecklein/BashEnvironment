#!/bin/bash

# Installs the utils to /usr/bin symbolic links

sudo ln -s $PWD/encryption/encrypt.sh /usr/local/bin/encrypt
sudo ln -s $PWD/encryption/decrypt.sh /usr/local/bin/decrypt

sudo ln -s $PWD/ss-search/ss-search /usr/local/bin/ss-search
