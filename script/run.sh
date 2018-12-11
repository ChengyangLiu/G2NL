#!/bin/bash
set -e

ROOT=`git rev-parse --show-toplevel`
cd $ROOT/target
java -jar ./g2nl-1.0.jar $1
