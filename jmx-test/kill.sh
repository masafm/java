#!/usr/bin/env zsh
[[ -f $(dirname $0)/pid.txt ]] && kill $(cat "$(dirname $0)/pid.txt") && rm -f "$(dirname $0)/pid.txt"
