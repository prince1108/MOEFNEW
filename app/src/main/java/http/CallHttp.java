package http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ravish on 07-03-2017.
 */

public class CallHttp {
    public static String TAG = "Logger===";
    public String httpPostRequest(HashMap<String,String> hashMap) {
        NSConfig nsConfig= NSConfig.getInstance();
        String POST_PARAMS = createQueryStringForParameters(hashMap);
        String mResponse = null;
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(nsConfig.BASE_URL);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            // For POST only - BEGIN
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END
            int responseCode = con.getResponseCode();
                Log.i(TAG, "POST Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                Log.i(TAG, response.toString());
                mResponse = response.toString();
            } else {
                mResponse = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mResponse;
    }

    public String httpGetRequest(String url) {
        NSConfig nsConfig= NSConfig.getInstance();
        String POST_PARAMS = "";
        String mResponse = null;
        URL obj = null;
        HttpURLConnection con = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            // For POST only - BEGIN
            //con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            // For POST only - END
            int responseCode = con.getResponseCode();
            Log.i(TAG, "POST Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                Log.i(TAG, response.toString());
                mResponse = response.toString();
            } else {
                mResponse = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mResponse;
    }

    public String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(3000);
            c.setReadTimeout(3000);
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    public String createQueryStringForParameters(Map<String, String> parameters) {
         final char PARAMETER_DELIMITER = '&';
         final char PARAMETER_EQUALS_CHAR = '=';
        StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;
            for (String parameterName : parameters.keySet()) {
                if (!firstParameter) {
                    parametersAsQueryString.append(PARAMETER_DELIMITER);
                }
                parametersAsQueryString.append(parameterName)
                        .append(PARAMETER_EQUALS_CHAR)
                        .append(URLEncoder.encode(
                                parameters.get(parameterName)));
                firstParameter = false;
            }
        }
        return parametersAsQueryString.toString();
    }
}
