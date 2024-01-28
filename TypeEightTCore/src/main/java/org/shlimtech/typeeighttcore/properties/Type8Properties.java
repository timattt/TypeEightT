package org.shlimtech.typeeighttcore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "type8")
@Data
public class Type8Properties {

    private List<String> groups;

}
