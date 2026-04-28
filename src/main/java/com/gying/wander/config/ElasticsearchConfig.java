package com.gying.wander.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(
            @Value("${elasticsearch.host:127.0.0.1}") String host,
            @Value("${elasticsearch.port:9200}") int port,
            @Value("${elasticsearch.scheme:http}") String scheme) {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
    }
}
