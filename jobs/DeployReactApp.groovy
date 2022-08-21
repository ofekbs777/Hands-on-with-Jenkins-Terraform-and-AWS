pipelineJob("Deploy-React-App"){
    description("Deploys a React web application to AWS")
    logRotator {
        daysToKeep(5)
        numToKeep(20)
    }
    concurrentBuild(allowConcurrentBuild = true)
    triggers {
        scm("* * * * *"){
            ignorePostCommitHooks(ignorePostCommitHooks = false)
        }
    }
    parameters {
      stringParam("UNIQUE_ANIMAL_IDENTIFIER", defaultValue = "changeme", description = "Your unique animal identifier for this playground!")
    }
    definition {
    cpsScm {
      scm {
        git {
          branch("master")
          remote {
            credentials("${GIT_USER}")
            url("${GIT_URL}")
          }
        }
      }
      scriptPath('Jenkinsfile')
    }
  }
}
pipelineJob("Destroy-React-App"){
    description("Destroys a React web application to AWS")
    logRotator {
        daysToKeep(5)
        numToKeep(20)
    }
    concurrentBuild(allowConcurrentBuild = false)
    parameters {
      stringParam("ofek", defaultValue = "changeme", description = "45684555")
    }
    definition {
    cpsScm {
      scm {
        git {
          branch("master")
          remote {
            credentials("${GIT_USER}")
            url("${GIT_URL}")
          }
        }
      }
      scriptPath('destroy.Jenkinsfile')
    }
  }
}