#!/bin/bash
kill $(ps -ef  | grep $(ls target| grep \\.jar$) | awk '{print $2}')
