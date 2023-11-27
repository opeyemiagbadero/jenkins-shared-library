#!/usr/bin/env groovy
def call(String imageName) {
    echo "Building the docker image ..."
    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh """
                    docker build -t $imageName .
                    echo \${PASSWORD} | docker login -u \${USERNAME} --password-stdin
                    docker push $imageName
                """
    }
}