package com.lisi.esdemo.esconfig;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EsClientConfig {

    @Bean("esHigh")
    public RestHighLevelClient getEsClient(){
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
            new HttpHost("127.0.0.1", 9200, "http")));
        return client;
    }


}
