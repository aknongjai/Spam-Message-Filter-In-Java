import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

class data{
    public static void merge(File input1,File input2,File output)throws Exception
    {
        PrintWriter pw = new PrintWriter(output);//copy the words from a file and writes it to another
//        File f=new File("D:\\File Read into words\\src\\file1.txt");
//        FileReader fr=new FileReader(f);
        BufferedReader br = new BufferedReader(new FileReader(input1));//identify the words
        String line = br.readLine();//Catch the word to the String variable 'line'
//        loop to copy each line of
//        file1.txt to  file3.txt
        while (line != null) {
            pw.println(line);//write the String 'line' to file associated with 'pw'
            line = br.readLine();//reads the next word,it doesn't keep on repeating
        }
        br = new BufferedReader(new FileReader(input2));//
        line = br.readLine();
        while (line != null) {
            pw.println(line);//writes the String 'line' to the end of the doc.
            line = br.readLine();//reads the next word.
        }
        pw.flush();//its like saving the file
        br.close();//closing BufferedReader.
        pw.close();
        System.out.println("Merged the two files");
//        //            File file = new File("D:\\File Read into words\\file3.txt");// double backslash is to avoid compiler interpret as words for example \test as \t (ie. as a escape sequence)
//        BufferedReader Br = new BufferedReader(new FileReader(output));
//        ArrayList<String> st = new ArrayList<String>();
//        String s = Br.readLine();
//        while (s != null) {
//            // System.out.println(s);
//            st.add(s);
//            s = Br.readLine();
//        }
//        for (int i = 0; i < st.size(); i++) {
//            System.out.println(st.get(i));
//        }

    }
    public static void duplicate(File file,File out)throws Exception{
        // PrintWriter object for output.txt
        PrintWriter pw = new PrintWriter(out);

        // BufferedReader object for input.txt
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        String line1 = br1.readLine();
        while(line1 != null)
        {
            boolean flag = false;

            // BufferedReader object for output.txt
            BufferedReader br2 = new BufferedReader(new FileReader(out));

            String line2 = br2.readLine();

            // loop for each line of output.txt
            while(line2 != null)
            {

                if(line1.equals(line2))
                {
                    flag = true;
                    break;
                }

                line2 = br2.readLine();

            }

            // if flag = false
            // write line of input.txt to output.txt
            if(!flag){
                pw.println(line1);

                // flushing is important here
                pw.flush();
            }

            line1 = br1.readLine();

        }
        // closing resources
        br1.close();
        pw.close();
        System.out.println("No more Duplicates");
    }
//    public static void print(File f)throws Exception{
//        BufferedReader Br = new BufferedReader(new FileReader(f));
//        ArrayList<String> st=new ArrayList<String>();
//        String s=Br.readLine();
//        while(s!= null){
//            // System.out.println(s);
//            st.add(s);
//            s=Br.readLine();
//        }
//        for(int i=0;i<st.size();i++){
//            System.out.println(st.get(i));
//        }
//        System.out.println("word Count is "+st.size());
//    }
}


public class SpamFiltering {
    private static data tab=new data();
    private static ArrayList<String> testArray=new ArrayList<String>();
    private static ArrayList<String> spamArray=new ArrayList<String>();
    private static ArrayList<String> nonSpamArray=new ArrayList<String>();
    private static ArrayList<String> vocaArray=new ArrayList<String>();

    private static double[] inSpam=new double[100];//likelihoods
    private static double[] inNonSpam=new double[100];//likelihoods
    private static double[] wordCount1=new double[100];//spam
    private static double[] wordCount2=new double[100];//nonSpam
    private static void moveToArray(File f,ArrayList<String> st)throws Exception{
        BufferedReader Br = new BufferedReader(new FileReader(f));
        String s=Br.readLine();
        while(s!= null){
            // System.out.println(s);
            st.add(s);
            s=Br.readLine();
        }
//        for(int i=0;i<st.size();i++){
//            System.out.println(st.get(i));
//        }
//        System.out.println("word Count is "+st.size());
    }
    private static void spamCollect()throws Exception{
        tab.merge(new File("D:\\Spam Filtering\\s1.txt"),new File("D:\\Spam Filtering\\s2.txt"),new File("D:\\Spam Filtering\\temp1.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp1.txt"),new File("D:\\Spam Filtering\\s3.txt"),new File("D:\\Spam Filtering\\temp2.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp2.txt"),new File("D:\\Spam Filtering\\s4.txt"),new File("D:\\Spam Filtering\\temp1.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp1.txt"),new File("D:\\Spam Filtering\\s5.txt"),new File("D:\\Spam Filtering\\spam.txt"));
        moveToArray(new File("D:\\Spam Filtering\\spam.txt"),spamArray);
    }
    private static void nonSpamCollect()throws Exception{
        tab.merge(new File("D:\\Spam Filtering\\ns1.txt"),new File("D:\\Spam Filtering\\ns2.txt"),new File("D:\\Spam Filtering\\temp1.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp1.txt"),new File("D:\\Spam Filtering\\ns3.txt"),new File("D:\\Spam Filtering\\temp2.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp2.txt"),new File("D:\\Spam Filtering\\ns4.txt"),new File("D:\\Spam Filtering\\temp1.txt"));
        tab.merge(new File("D:\\Spam Filtering\\temp1.txt"),new File("D:\\Spam Filtering\\ns5.txt"),new File("D:\\Spam Filtering\\nonSpam.txt"));
        moveToArray(new File("D:\\Spam Filtering\\nonSpam.txt"),nonSpamArray);
    }
    private static void vocabulary()throws Exception{
        tab.merge(new File("D:\\Spam Filtering\\spam.txt"),new File("D:\\Spam Filtering\\nonSpam.txt"),new File("D:\\Spam Filtering\\temp1.txt"));
        tab.duplicate(new File("D:\\Spam Filtering\\temp1.txt"),new File("D:\\Spam Filtering\\vocabulary.txt"));
        moveToArray(new File("D:\\Spam Filtering\\vocabulary.txt"),vocaArray);
    }
    private static void testFile()throws Exception{
        moveToArray(new File("D:\\Spam Filtering\\testFile.txt"),testArray);
//        for(int i=0;i<testArray.size();i++){
//            System.out.println("is "+testArray.get(i));
//        }
    }
    private static double count1=0.0;
    private static double count2=0.0;
    private static void compare(){
        for(int i=0;i<testArray.size();i++){
           // String esum=testArray.get(i);
            for(int j=0;j<spamArray.size();j++){
                if ((testArray.get(i)).equals(spamArray.get(j))){
                    count1++;
                }
            }
            wordCount1[i]=count1;//esum is present count times in spam
            count1=0;
            for(int j=0;j<nonSpamArray.size();j++){
                if ((testArray.get(i)).equals(nonSpamArray.get(j))){
                    count2++;
                }
            }
            wordCount2[i]=count2;//esum is present count times in nonspam
            count2=0;
        }
//        for(int i=0;i<wordCount1.length;i++){
//            System.out.println("spam is "+wordCount1[i]);
//        }
//        for(int i=0;i<wordCount2.length;i++){
//            System.out.println("NonSpam is "+wordCount2[i]);
//        }
    }
    private static void probabilities(){
        for(int i=0;i<testArray.size();i++){
             inSpam[i]=((wordCount1[i]+(double)1)/((double)spamArray.size()+(double)vocaArray.size()));
            inNonSpam[i]=((wordCount2[i]+(double)1)/((double)nonSpamArray.size()+(double)vocaArray.size()));
        }
    }
    private static double[] posArray=new double[100];
    private static void posterior(){
        double sPrior=0.5;//prior of spam
        double nsPrior=0.5;//prior of non spam
        double t1=1;//for spam
        double t2=1;//for nonSpam
        for(int i=0;i<testArray.size();i++){
//            System.out.println("in spam "+inSpam[i]);
            t1=(t1*inSpam[i]);
        }
        for(int i=0;i<testArray.size();i++) {
//            System.out.println("in non spam "+inNonSpam[i]);
            t2=(t2*inNonSpam[i]);
        }
        double beingSpam;
        beingSpam=(sPrior*t1);//spam
        double beingNonSpam;
        beingNonSpam=(nsPrior*t2);//nonSpam
        if(beingSpam>beingNonSpam){
            System.out.println("the E-mail is spam as "+beingSpam+" is greater than "+beingNonSpam);
        }
        else {
            System.out.println("the E-mail is nonSpam as "+beingNonSpam+" is greater than "+beingSpam);
        }
    }
    public static void main(String [] args)throws Exception{
        spamCollect();
        nonSpamCollect();
        vocabulary();
        testFile();
        System.out.println(spamArray.size());
        System.out.println(nonSpamArray.size());
        System.out.println(vocaArray.size());
        compare();
        probabilities();
        posterior();
//        double[] a=new double[500];
//        a[1]=5.7863882863;
//        System.out.println(a[1]);

    }



}
/*All Rights Reserved
Nongjaimayum Annas khan
Husband of Ph Tabasum Sahani
*/
