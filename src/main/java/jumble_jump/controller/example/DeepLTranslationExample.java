package jumble_jump.controller.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DeepLTranslationExample {
    private static final String API_KEY = "1f3905db-c5c1-4cc1-8681-1229b5f5c555:fx"; // DeepL API Key
    private static final String ENDPOINT = "https://api-free.deepl.com/v2/translate";

    public static void main(String[] args) {
        try {
            String translatedText = translate("안녕하세요 저는 수키피에요", "EN");
            System.out.println("Translated text: " + translatedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String translate(String text, String targetLang) throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        String requestBodyContent = "auth_key=" + API_KEY + "&text=" + encodedText + "&target_lang=" + targetLang;

        RequestBody body = RequestBody.create(requestBodyContent, mediaType);
        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new Exception("Unexpected code " + response);

            String responseData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
            String translated = jsonObject.getAsJsonArray("translations").get(0).getAsJsonObject().get("text").getAsString();
            return translated;
        }
    }

}
