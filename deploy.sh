#!/bin/bash
# deploy.sh

# 아래 경로 및 명령을 실제 프로젝트에 맞게 수정하세요.
cd /home/ubuntu/SeoulCulturePort-0.0.1-SNAPSHOT.jar
git pull
mvn clean install
sudo systemctl restart seoul-culture-port.service