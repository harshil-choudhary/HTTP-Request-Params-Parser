import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Testing {

    public static Map<String, String> decodeQueryString(String query) {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            for (String param : query.split("&|\\?")) {
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                if (!key.isEmpty() && !key.contains("September")) {
                    if (value.contains("\n")) {
                        String temp = value.substring(0, value.indexOf("\n"));
                        value = temp;
                    }
                    if (params.containsKey(key)) {
                        String temp = params.get(key);
                        params.put(key, temp + ", " + value);
                        System.out.println(key + " " + temp + ", " + value);
                    } else {
                        params.put(key, value);
                        System.out.println(key + " " + value);
                    }
                }
            }
            return params;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e); // Cannot happen with UTF-8 encoding.
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/home/harshilchoudhary/Project_Files/Kibana_Test_Logs_Market_Catalog");

        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in);

        String logToString = "";

        try {
            int c;
            while ((c = reader.read()) != -1) {
                logToString += ((char)c);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Map<String, String> allKeyValuePairs = decodeQueryString(logToString);


        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter("somefile.csv")) {
            for (Map.Entry<String, String> entry : allKeyValuePairs.entrySet()) {
                writer.append(entry.getKey())
                        .append(',')
                        .append(entry.getValue())
                        .append(eol);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }


    }
}
