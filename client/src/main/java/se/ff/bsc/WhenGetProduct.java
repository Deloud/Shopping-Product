package se.ff.bsc;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class WhenGetProduct
{
    int port=8088;

    WhenGetProduct() {
        // Will use default port.
        System.out.println("Default port "+port);
    }

    WhenGetProduct(int port) {
        this.port=port;
        System.out.println("Custom port "+port);
    }

    public static void main( String[] args ) {
        Integer eta = new WhenGetProduct().checkUser("1");
        System.out.println("eta="+eta);
    }

    public Integer checkUser(String id) {
        try {
            String url=String.format("Http://localhost:%d/%s",port, id);
            System.out.println("using url: " + url);
            HttpResponse r = Request.Get(url).execute().returnResponse(); // 이거없으면 에러
            String json = EntityUtils.toString(r.getEntity());
            System.out.println("json="+json);
            JSONObject jsonObject = new JSONObject(json);
            // String eta = jsonObject.get("eta").toString();
            // return new Integer(eta);
            Integer a = 3;
            return a;

        }
        catch (Exception e) {
            System.out.println("Unable to get eta, e="+e);
            return null;
        }
    }
}
