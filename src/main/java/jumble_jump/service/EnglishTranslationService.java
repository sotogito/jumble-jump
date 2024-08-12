package jumble_jump.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jumble_jump.repository.TranslationEntryRepository;
import okhttp3.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EnglishTranslationService {
    private final TranslationEntryRepository translationEntryRepository;

    private final String API_KEY = "1f3905db-c5c1-4cc1-8681-1229b5f5c555:fx"; // DeepL API Key
    private final String END_POINT = "https://api-free.deepl.com/v2/translate";
    private final String translateLanguage = "EN";

    public EnglishTranslationService(TranslationEntryRepository translationEntryRepository) {
        this.translationEntryRepository = translationEntryRepository;
    }

    public void translate(String korean) throws Exception {
        setKorean(korean);
        String english = translateToEnglish(korean);
        translationEntryRepository.setEnglish(english);
    }

    public String translateToEnglish(String korean) throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.get("application/x-www-form-urlencoded");
        String encodedText = URLEncoder.encode(korean, StandardCharsets.UTF_8.toString());
        String requestBodyContent = "auth_key=" + API_KEY + "&text=" + encodedText + "&target_lang=" + translateLanguage;

        RequestBody body = RequestBody.create(requestBodyContent, mediaType);
        Request request = new Request.Builder()
                .url(END_POINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IllegalArgumentException("Unexpected code " + response);

            String responseData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
            String translated = jsonObject.getAsJsonArray("translations").get(0).getAsJsonObject().get("text").getAsString();
            return translated;
        }
    }

    public void setKorean(String korean){
        translationEntryRepository.setKorean(korean);
    }

}
