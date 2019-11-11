package serialise;

import org.json.JSONArray;
import org.json.JSONObject;

public class jsonParse {
    public static void main(String[] args){
        parse(" ");
    }

    public static void parse(String inputJson) {
        JSONObject obj = new JSONObject("{\"pageInfo\": {\"pageName\": \"abc\", \"pagePic\": \"http://example.com/content.jpg\"}, \"posts\": [{\"post_id\": \"123456789012_123456789012\", \"actor_id\": \"1234567890\", \"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\", \"nameOfPersonWhoPosted\": \"Jane Doe\", \"message\": \"Sounds cool. Can't wait to see it!\", \"likesCount\": \"2\", \"comments\": [], \"timeOfPost\": \"1234567890\"}]}");
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }
    }
}
