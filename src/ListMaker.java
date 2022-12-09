import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Path;
import java.io.*;
import javax.swing.*;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;



public class ListMaker {


    public static Path currentFile;
    public static boolean loadedFile = false;
    public static boolean newUnsavedFile = false;

    public static Scanner pipe = new Scanner(System.in);
    public static ArrayList<String> myArrayList = new ArrayList<>();

    public static void main(String[] args) {
        boolean done = false;

        do{
            String response = menu(pipe, "Hello, what are you trying to do? [A-add, D-delete, O-Open, C-Clear, V-View, S-Save, or Q-Quit] ", "[AaDdVvQqOoSsCc]",myArrayList);
            response = response.toUpperCase();
            switch (response) {
                case "A":
                    String add = SafeInput.getNonZeroLenString(pipe,"What are you adding?");
                    myArrayList.add(add);
                    newUnsavedFile = true;
                    break;

                case "V":
                    displayNumberedArray(myArrayList);
                    break;
                case "D":
                    displayNumberedArray(myArrayList);
                    int delete = SafeInput.getInt(pipe,"What are you deleting?") - 1;
                    myArrayList.remove(delete);
                    newUnsavedFile = true;
                    break;

                    case "Q":
                    if(newUnsavedFile){
                        done = SafeInput.getYNConfirm(pipe,"You haven't saved yet, you sure you want to quit?");
                        done = !done;
                    }else{
                        done = SafeInput.getYNConfirm(pipe,"Would you like to continue? [Y/N]");
                    }
                    break;
                case "O":
                    myArrayList = openFile();
                    break;
                case "C":
                    myArrayList.clear();
                    break;
                case "S":
                    saveFile();
                    break;
            }
        }while (!done);
    }
    private static String menu(Scanner pipe,String prompt,String regEx,ArrayList<String> myArrList){
        if(myArrList.size() != 0){
            for(String value: myArrList){
                System.out.printf("%s ",value);
            }
        }else{
            System.out.println("Whatever is added to the array will be displayed here.");
        }
        return SafeInput.getRegExString(pipe,prompt,regEx);
    }
    private static void displayNumberedArray(ArrayList<String> myArrList){
        int i = 1;
        System.out.println("");
        for(String value: myArrList){
            System.out.printf("%d(%s) ",i,value);
            i = i + 1;
        }
        System.out.println("");
    }
    private static ArrayList<String> openFile(){
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String line;
        String[] arrayBuffer;
        if(loadedFile && newUnsavedFile){
            System.out.println("Save your file before continuing");
        }else{
            try{
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);
                System.out.println("1");
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("2");
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();
                    currentFile = selectedFile.toPath();

                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    while(reader.ready()){
                        line = reader.readLine();
                        arrayBuffer = line.split(" ");
                        for(int i=0;i<arrayBuffer.length;i++){
                            myArrayList.add(arrayBuffer[i]);
                        }
                    }
                    loadedFile = true;
                    reader.close();
                }else{
                    System.out.println("You haven't selected a file yet.");
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return myArrayList;
    }
    private static void saveFile(){
        if(newUnsavedFile){
            if(loadedFile){
                File workingDirectory = new File(System.getProperty("user.dir"));

                try{
                    OutputStream out = new BufferedOutputStream(Files.newOutputStream(currentFile, CREATE));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                    for(String rec: myArrayList){
                        writer.write(rec,0,rec.length());
                        writer.newLine();
                    }
                    writer.close();
                    System.out.println("Data has been successfully written to "+currentFile);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                newUnsavedFile = false;
            }else{
                String newFileName = SafeInput.getNonZeroLenString(pipe,"What do you want to name this file?");
                File workingDirectory = new File(System.getProperty("user.dir"));
                Path file = Paths.get(workingDirectory.getPath()+"\\src\\"+newFileName+".txt");

                try{
                    OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                    for(String rec: myArrayList){
                        writer.write(rec,0,rec.length());
                        writer.newLine();
                    }
                    writer.close();
                    System.out.println("Data has been successfully written to "+file);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                newUnsavedFile = false;
            }
        }else{
            System.out.println("No changes have been made");
        }
    }
}