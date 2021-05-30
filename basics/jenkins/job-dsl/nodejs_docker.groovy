job('NodeJS Docker example') {
    scm {
        git('git://github.com/wardviaene/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
//    wrappers {
//       nodejs('nodejs-new') 
//    }
    steps {
        dockerBuildAndPublish {
            repositoryName('yoavj/jen')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('yoavj')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
