package pro.aicc.tuku.qwen.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {
    @Autowired
    private ChatClient chatClient;

    @GetMapping("/ai/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message){
        String systemMessage = """
                        1、You are a super AI assistant.
                        2、Do not return any prompts or explanations.
                        3、Output the user's intent in JSON format, with the JSON structure as follows:
                        {
                        "userIntent":""
                        }。
                        """;
        List<Message> messageList = new ArrayList<>();
        messageList.add(new SystemMessage(systemMessage));
        messageList.add(new UserMessage(message));
        Prompt prompt = new Prompt(messageList);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

}