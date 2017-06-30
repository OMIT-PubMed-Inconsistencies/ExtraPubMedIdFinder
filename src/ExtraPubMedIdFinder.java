import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExtraPubMedIdFinder {

    ArrayList<String> ids=new ArrayList<String>();
    HashSet<String> idSet=new HashSet<String>();
   // OMITconnector oc=OMITconnector.getInstance();
  //  HashMap<String,Node> humanGazetteerList=oc.getTreeRootedAt("&obo;NCRO_0000810").getGazetteerList(); //human_miRNA ( "&obo;NCRO_0000810")
   // HashMap<String,Node> mRNAGazetteerList=oc.getTreeRootedAt("&obo;NCRO_0000025").getGazetteerList(); //miRNA_target_gene "&obo;NCRO_0000025"

    public static void main(String[] args) {
        ExtraPubMedIdFinder ex=new ExtraPubMedIdFinder();
        ex.readOld();
        ex.readNew();
        ex.writeNew();
    }


    private void writeNew(){
        int fileCounter=0;
        int idCounter=0;

        while(ids.size()>idCounter) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("../output/00_PubMedIds/" + fileCounter + ".txt", "UTF-8");
                for (int i=0;(idCounter < ids.size())&&(i<1000); idCounter++,i++) {
                    writer.println(ids.get(idCounter));
                }
                writer.close();
                fileCounter++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    public void readNew(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("../pubmed-list_All_MIR.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                SafeAddId(line);
            }
        }
        catch (Exception e) {

        }

    }

    public void readOld(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("../pubmed-list.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;


            while ((line = bufferedReader.readLine()) != null) {
                SafeAddId(line);
            }
        }
        catch (Exception e) {

        }
        ids=new ArrayList<String>(); //empty the IDs
    }

    private void SafeAddId(String id){
        if(!idSet.contains(id)) {
            ids.add(id);
            idSet.add(id);
        }
    }

}
