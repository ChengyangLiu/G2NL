#!/bin/bash
set -e

ROOT=`git rev-parse --show-toplevel`
SUBMODULE=$ROOT/submodule/SemWeb2NL
echo "root of project: "$ROOT

cd $SUBMODULE
sudo mvn clean install

cd $ROOT
sudo mvn clean install
