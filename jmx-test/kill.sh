#!/usr/bin/bash -x
pid_file=$(dirname $0)/pid.txt
[[ -f $pid_file ]] && kill $(cat "${pid_file}") && rm -f "${pid_file}"
