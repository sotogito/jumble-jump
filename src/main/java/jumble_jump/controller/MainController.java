package jumble_jump.controller;

public class MainController {

    public void main(){

    }
    /*
    import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONObject;

public class PapagoTranslator {

    private static final String CLIENT_ID = "YOUR_CLIENT_ID";  // 네이버 클라우드 플랫폼에서 발급받은 클라이언트 ID
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";  // 네이버 클라우드 플랫폼에서 발급받은 클라이언트 시크릿

    public static void main(String[] args) {
        String koreanText = "안녕하세요";
        String translatedText = translateToEnglish(koreanText);
        System.out.println("Translated Text: " + translatedText);
    }

    private static String translateToEnglish(String text) {
        String apiUrl = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translations";
        String translatedText = "";

        try {
            // URL 객체 생성
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
            conn.setDoOutput(true);

            // 요청 데이터 설정
            String postParams = "source=ko&target=en&text=" + URLEncoder.encode(text, "UTF-8");

            // 요청 보내기
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postParams.getBytes("UTF-8"));
                os.flush();
            }

            // 응답 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            // JSON 응답 파싱
            JSONObject jsonResponse = new JSONObject(response.toString());
            translatedText = jsonResponse.getJSONObject("message").getJSONObject("result").getString("translatedText");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return translatedText;
    }
}

     */
}
