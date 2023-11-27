#!/usr/bin/env groovy
def call() {
    echo "Building the docker image ..."
    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh """
                    docker build -t opeyemiagbadero/demo-app:jma-2.0 .
                    echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                    docker push opeyemiagbadero/demo-app:jma-2.0
                """
    }
}