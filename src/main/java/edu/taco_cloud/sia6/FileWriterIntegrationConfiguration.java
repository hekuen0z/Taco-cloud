package edu.taco_cloud.sia6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class FileWriterIntegrationConfiguration {

    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlow
                .from(MessageChannels.direct("textInChannel"))
                .<String, String>transform(String::toUpperCase)
                .handle(Files
                        .outboundAdapter(new File("/tnp/sia6/files"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();
    }
}
