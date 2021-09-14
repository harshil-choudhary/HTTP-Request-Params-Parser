import java.io.*;
import java.util.ArrayList;

public class Main {
    private static Boolean checkIfParamExists (ArrayList<String> params, String param) {
        for (int i = 0; i<params.size(); i++) {
            if (params.get(i).equals(param))  {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/home/harshilchoudhary/Project_Files/Kibana_Test_Logs");
        ArrayList<String> params = new ArrayList<String>();
        //ArrayList<ArrayList<String>> allParamValues = new ArrayList<ArrayList<String>>();
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in);
        Boolean readingParamValue = false;
        String currentParam = "";
        //String currentParamValue = "";

        try {
            int c;
            while ((c = reader.read()) != -1) {
                char chara = (char) c;
                if (chara == '=') {
                    readingParamValue = false;
                    if (!checkIfParamExists(params, currentParam)) {
                        params.add(currentParam);
                    }
                }
                if (readingParamValue) {
                    currentParam += chara;
                }
                if ((chara == '?') || (chara == '&')) {
                    readingParamValue = true;
                    currentParam = "";
                }

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.out.println("Number of params : " + params.size());
        for (int i = 0; i<params.size(); i++) {
            System.out.println(params.get(i));
        }
    }
}
