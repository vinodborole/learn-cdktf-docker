package com.mycompany.app;

import com.hashicorp.cdktf.TerraformVariable;
import com.hashicorp.cdktf.TerraformVariableConfig;
import software.constructs.Construct;

import java.util.Arrays;

import com.hashicorp.cdktf.TerraformStack;

import com.hashicorp.cdktf.providers.docker.provider.DockerProvider;
import com.hashicorp.cdktf.providers.docker.image.Image;
import com.hashicorp.cdktf.providers.docker.container.Container;
import com.hashicorp.cdktf.providers.docker.container.ContainerPorts;

public class MainStack extends TerraformStack
{
    public MainStack(final Construct scope, final String id) {
        super(scope, id);
        TerraformVariable nginxImage = new TerraformVariable(this, "imageId", TerraformVariableConfig.builder()
                .type("string")
                .defaultValue("nginx:latest")
                .description("nginx latest image")
                .build()
        );

        TerraformVariable containerName = new TerraformVariable(this, "containerName", TerraformVariableConfig.builder()
                .type("string")
                .defaultValue("tutorial")
                .description("container name")
                .build()
        );

        DockerProvider.Builder.create(this, "docker")
                .build();

        Image image = Image.Builder.create(this, "nginxImage")
                .name(nginxImage.getStringValue())
                .keepLocally(false)
                .build();

        Container.Builder.create(this, "nginxContainer")
                .image(image.getName())
                .name(containerName.getStringValue())
                .ports(Arrays.asList(ContainerPorts.builder().internal(80).external(8000).build()))
                .build();
    }
}
