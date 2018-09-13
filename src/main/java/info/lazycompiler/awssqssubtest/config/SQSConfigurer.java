package info.lazycompiler.awssqssubtest.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Setter
@Getter
public class SQSConfigurer {

    private String url;
    private AmazonSQSAsync sqsAsyncClient;

    public SQSConfigurer(@Value("${cloud.aws.region.static}") String region, @Value("${cloud.aws.sqs.url}") final
                         String url) {
       this.sqsAsyncClient = AmazonSQSAsyncClientBuilder.standard().withRegion(region).build();
       this.url = url;
    }

}
