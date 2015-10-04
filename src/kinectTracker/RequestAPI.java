package kinectTracker;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestAPI {

    public boolean status;

    public RequestAPI() {
        this.status = true;
    }

    public RequestAPI(boolean init) {
        this.status = init;
    }
    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public void sendData(boolean status) throws Exception {
        String url = null;

        if (status == false) {
            url = "http://icoffee.sacomp.com.br/api.php?liga=1";
            System.out.println("True");
        } else {
            url = "http://icoffee.sacomp.com.br/api.php?liga=0";
            System.out.println("False");
        }
        if (url != null) {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        }
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
