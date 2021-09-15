import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> allParams = new ArrayList<String>();



    private static Boolean checkIfParamExists (ArrayList<String> params, String param) {
        for (int i = 0; i<params.size(); i++) {
            if (params.get(i).equals(param))  {
                return true;
            }
        }
        return false;
    }

    private static void getallParams (File file) throws FileNotFoundException {

        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in);

        Boolean readingParamValue = false;
        String currentParam = "";

        try {
            int c;
            while ((c = reader.read()) != -1) {
                char chara = (char) c;
                if (chara == '=') {
                    readingParamValue = false;
                    if (!checkIfParamExists(allParams, currentParam)) {
                        allParams.add(currentParam);
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

        System.out.println("Number of params : " + allParams.size());
        for (int i = 0; i<allParams.size(); i++) {
            System.out.println("[" + i + "] " + allParams.get(i));
        }
        System.out.println();
    }

    /*private static void getallParamValues (File file, int index) {
        String param = allParams.get(index);

        try {
            int c;
            while ((c = reader.read()) != -1) {
                char chara = (char) c;


            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }*/


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/home/harshilchoudhary/Project_Files/Kibana_Test_Logs_Market_Catalog");
        Scanner sc= new Scanner(System.in);
        int input = 0;
        getallParams(file);
        while (true) {
            System.out.print("Enter index of Param for values (-1 to get list again, -2 to quit) : ");
            input = sc.nextInt();
            if (input == -1) {
                getallParams(file);
            } else if (input == -2) {
                break;
            } else {
                //getallParamValues(file, input);
            }
        }
    }
}
