@echo off

set SRC_DIR="../proto"

setlocal enabledelayedexpansion

protoc -I=%SRC_DIR%/protofile --java_out=../src/main/java %SRC_DIR%/protofile/*.proto
echo "finished"
pause > nul