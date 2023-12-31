#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    def script
    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "Building the docker image ..."
        script.sh "docker build -t $imageName ."

    }

    def dockerLogin() {
        script.echo "Building the docker image ..."
        script.withCredentials([script.usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')]) {
            script.sh """
                    echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                    
                """
        }

    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName"

    }

}