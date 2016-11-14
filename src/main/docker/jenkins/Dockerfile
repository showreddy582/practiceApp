#custom jenkins image with docker installed
FROM jenkins:latest
MAINTAINER Appi Bh <bhiapp4@gmail.com>

# Suppress apt installation warnings
ENV DEBIAN_FRONTEND=noninteractive

# Change to root user
USER root

# Used to set the docker group ID
# Set to 497 by default, which is the group ID used by AWS Linux ECS Instance
ARG DOCKER_GID=497

# Create Docker Group with GID
# Set default value of 497 if DOCKER_GID set to blank string by Docker Compose
# This helps the docker client in jenkins container to communicate to the underlying docker host through docker socket
RUN groupadd -g ${DOCKER_GID:-497} docker

# Install base packages
RUN apt-get update -y && \
    apt-get install apt-transport-https curl python-dev python-setuptools gcc make libffi-dev libssl-dev -y && \
    easy_install pip

RUN usermod -aG docker jenkins && \
    usermod -aG users jenkins

RUN pip install ansible boto boto3

# Change to jenkins user
USER jenkins

# Add Jenkins plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/install-plugins.sh /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh $(cat /usr/share/jenkins/plugins.txt | tr '\n' ' ')