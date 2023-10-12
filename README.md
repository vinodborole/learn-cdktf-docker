# CDKT Example 
 Execute cdkt app as an executable jar that deploys ngnix container locally and save its terraform state to 
 remote backend as amazon S3

## Build
```
    ./gradlew clean build
```

## Execution
### Prerequisites

Install 
1. Nodejs, NPM
```
    sudo apt install nodejs
    sudo apt install npm
```
1. cdktf cli
```
    npm install --global cdktf-cli@latest
```
1. Docker
```
    sudo apt install docker.io
```

Setup your environment with the following environment variables:

```
    export AWS_ACCESS_KEY_ID=AKIAJU2Q
    export AWS_ACCESS_SECRET_KEY=J2Q
    export AWS_REGION=ap-south-1
```

Create a directory outside of this project 
```
    mkdir cdktf-execution
    cd cdktf-execution
```

Copy the jar file 'learn-cdktf-docker-0.1-all.jar' from build/libs to cdktf-execution
Copy the cdktf.json file from root of this project to cdktf-execution

```
    cp ../build/libs/learn-cdktf-docker-0.1-all.jar .
    cp ../cdktf.json .
```

### Deploy

```
    cdktf deploy --auto-approve --app "java -jar learn-cdktf-docker-0.1-all.jar" 
    
    docker ps
```

### Deploy with variables

```
    cdktf deploy --auto-approve --app "java -jar learn-cdktf-docker-0.1-all.jar" --var='imageId=nginx:latest' --var='containerName=learncdktf' 
    
    docker ps
```

### Destroy
    
```
    cdktf destroy --auto-approve --app "java -jar learn-cdktf-docker-0.1-all.jar" 
```