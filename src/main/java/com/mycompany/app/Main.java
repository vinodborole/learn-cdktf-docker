package com.mycompany.app;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.S3Backend;
import com.hashicorp.cdktf.S3BackendConfig;

public class Main
{
    public static void main(String[] args) {
        final App app = new App();
        MainStack mainStack = new MainStack(app, "learn-cdktf-docker");
        new S3Backend(mainStack, S3BackendConfig.builder()
                .accessKey(System.getenv("AWS_ACCESS_KEY_ID"))
                .secretKey(System.getenv("AWS_ACCESS_SECRET_KEY"))
                .bucket("terraform-app")
                .key("learn-cdktf-docker/docker.tfstate")
                .region(System.getenv("AWS_REGION"))
                .build());
        app.synth();
    }
}
