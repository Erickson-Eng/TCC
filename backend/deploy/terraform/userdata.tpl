#!/bin/bash

# Install Docker
sudo apt-get update -y
sudo apt-get install -y \
apt-transport-https \
ca-certificates \
curl \
gnupg-agent \
software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update -y
sudo apt-get install docker-ce docker-ce-cli containerd.io -y
sudo usermod -aG docker ubuntu
sudo systemctl enable docker.service
sudo systemctl enable containerd.service

# Install Docker Compose
sudo apt install docker-compose -y

# Create project folder
mkdir ~/project
cd ~/project

# Clone your project from Git repository
git clone https://github.com/Erickson-Eng/TCC.git
cd TCC/backend
git checkout main
git pull
cd deploy/docker

# Run your Docker Compose file
docker-compose -f docker-compose-prod.yml --env-file prod.env up -d
