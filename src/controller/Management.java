package controller;

import view.Menu;
import view.Validation;
import okhttp3.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Management extends Menu {

    public Management() {
        super("Learning AI Management", new String[]{"Add new learning", "Exit"});
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                addLearning();
                break;
            case 2:
                System.out.println("Exiting...");
                stop();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void addLearning() {
        String subject = Validation.getString("Enter subject: ");
        String topic = Validation.getString("Enter topic: ");
        String gptResponse = callGPTAPI(subject, topic);

        if (gptResponse != null) {
            writeFile(subject, topic, gptResponse);
            System.out.println("GPT Response:\n" + gptResponse);
        } else {
            System.err.println("Failed to get response from GPT API.");
        }
    }

    public String callGPTAPI(String subject, String topic) {  // Đã sửa thành public
        String apiKey = "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";
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String requestBody = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\": \"You are a helpful assistant, you get the content and subject then give me the detail about the subject, a fully detail, i dont care how long is it, just keep a main idea and important information and pls Arrange information so that it is easy to read and understan, pls respond by the language that send to you\"},\n" +
                "    {\"role\": \"user\", \"content\": \"I want to learn about " + topic + " in " + subject + ".\"}\n" +
                "  ],\n" +
                "  \"max_tokens\": 4096\n" +
                "}";

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(RequestBody.create(requestBody, mediaType))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return extractGPTMessage(responseBody);
            } else {
                System.err.println("Error from GPT API: " + response.message());
                System.err.println("Response code: " + response.code());
                System.err.println("Response body: " + (response.body() != null ? response.body().string() : "No body"));
            }
        } catch (IOException e) {
            System.err.println("Error calling GPT API: " + e.getMessage());
        }
        return null;
    }

    private String extractGPTMessage(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            if (choicesArray.length() > 0) {
                JSONObject messageObject = choicesArray.getJSONObject(0).getJSONObject("message");
                return messageObject.getString("content").trim();
            }
        } catch (Exception e) {
            System.err.println("Failed to parse GPT response: " + e.getMessage());
        }
        return null;
    }

    private void writeFile(String subject, String topic, String content) {
        String fileName = subject + "_" + topic + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Response saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Management management = new Management();
        management.run();
    }
}
