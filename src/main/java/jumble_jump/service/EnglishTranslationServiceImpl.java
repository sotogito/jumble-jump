package jumble_jump.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jumble_jump.repository.TranslationEntryRepository;
import okhttp3.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EnglishTranslationServiceImpl implements EnglishTranslationService{

    private static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String API_KEY = "1f3905db-c5c1-4cc1-8681-1229b5f5c555:fx"; // DeepL API Key
    private static final String END_POINT = "https://api-free.deepl.com/v2/translate";
    private static final String translateLanguage = "EN";

    private final TranslationEntryRepository translationEntryRepository;

    public EnglishTranslationServiceImpl(TranslationEntryRepository translationEntryRepository) {
        this.translationEntryRepository = translationEntryRepository;
    }

    @Override
    public String translate(String korean) throws Exception {
        String english = translateToEnglish(korean);

        translationEntryRepository.setKorean(korean);
        translationEntryRepository.setEnglish(english);
        return english;
    }

    private String translateToEnglish(String korean) throws Exception {
        OkHttpClient client = createHttpClient();
        Request request = buildTranslationRequest(korean);

        try (Response response = executeRequest(client, request)) {
            return parseTranslation(response);
        }
    }

    private OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

    private Request buildTranslationRequest(String korean) throws Exception {
        MediaType mediaType = MediaType.get(FORM_URL_ENCODED);
        String encodedText = URLEncoder.encode(korean, StandardCharsets.UTF_8);
        String requestBodyContent = "auth_key=" + API_KEY + "&text=" + encodedText + "&target_lang=" + translateLanguage;

        RequestBody body = RequestBody.create(requestBodyContent, mediaType);
        return new Request.Builder()
                .url(END_POINT)
                .post(body)
                .build();
    }

    private Response executeRequest(OkHttpClient client, Request request) throws IOException {
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IllegalArgumentException("Unexpected code " + response);
        }
        return response;
    }

    private String parseTranslation(Response response) throws Exception {
        assert response.body() != null;
        String responseData = response.body().string();
        JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
        return jsonObject.getAsJsonArray("translations").get(0).getAsJsonObject().get("text").getAsString();
    }


}
