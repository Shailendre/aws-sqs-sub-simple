package info.lazycompiler.awssqssubtest.subscriber;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.lazycompiler.awssqssubtest.config.SQSConfigurer;
import info.lazycompiler.awssqssubtest.model.MessageObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Subscriber implements Runnable {

    private SQSConfigurer sqsConfigurer;
    private ObjectMapper mapper = new ObjectMapper();

    public Subscriber(final SQSConfigurer sqsConfigurer){
        this.sqsConfigurer = sqsConfigurer;
    }

    @Override
    public void run() {

        ReceiveMessageResult messageResult = sqsConfigurer.getSqsAsyncClient()
                .receiveMessage(sqsConfigurer.getUrl());

        if (messageResult != null) {

            List<Message> messages = messageResult.getMessages();

            if (messages != null && !messages.isEmpty()){

                String customMessageBody = messages.get(0).getBody();

                try {
                    MessageObject customMessageObject = mapper.readValue(customMessageBody, MessageObject.class);
                    log.info("Message body: {}", customMessageObject.getSubject());
                } catch (IOException e) {
                    log.error("Cannot read from sqs message model.");
                }


            }

            /*
            // Delete the message from the sqs
            //
            String messageHandle = messageResult.getMessages().get(0).getReceiptHandle();
            DeleteMessageResult result = sqsConfigurer.getSqsAsyncClient().deleteMessage(sqsConfigurer.getUrl(), messageHandle);
            log.info("Deleted message with handle: {}", messageResult);*/

        }
    }
}
