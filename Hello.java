// Your First Program
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;  
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; 
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Hello {
    public static void main(String[] args) throws IOException, InterruptedException {
        
      


        try {
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    /*
        File dir = new File("./incorrect_subs/");
        if (!dir.exists()) {
            System.out.println("Directory does not exist.");
            return;
        }
        // Get all the files in the directory
        File[] files = dir.listFiles();

        // Loop through the files and print their names
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    */
    File folder = new File("./incorrect_subs/");
    File[] listOfFiles = folder.listFiles();
    System.out.println("LENGTH"+ listOfFiles.length);
    
    for (File file : listOfFiles) {
        if (file.isFile()) {
        System.out.println(file.getName());
        createScriptFile("./scripts/"+file.getName()+".bat");
        composeScript("./incorrect_subs/"+file.getName(),"./scripts/"+file.getName()+".bat");
        runScript(file.getName());
        }
    }
    

    

    


 


    }
  static public void writeLineIntoFile(String line, String file){
      try {
        FileWriter myWriter = new FileWriter(file, true);
        myWriter.write(line);
        //myWriter.write(System.getProperty( "line.separator" ));
        myWriter.close();
        //System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }

  static public void composeScript(String messageFile, String finalScript){
  
      try {
        File myObj = new File("curl_script_part_1.bat");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          //System.out.println(data);
          writeLineIntoFile(System.getProperty( "line.separator" ),finalScript);
          writeLineIntoFile(data,finalScript);
        
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

      String prompt = "a text recognition software recognized this text from an image: \\\""+""+"\" but there are some mistakes, correct those mistakes and answer with the correct text between quotes with no additional information. The language is french. ";
      //writeLineIntoFile(message,finalScript);
      writeLineIntoFile("a text recognition software recognized this text from an image: (",finalScript);
      try {
        File myObj = new File(messageFile);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          //System.out.println(data);
          //writeLineIntoFile(System.getProperty( "line.separator" ),finalScript);
          writeLineIntoFile(data+"\\n",finalScript);
        
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      writeLineIntoFile(") but there are some mistakes, correct those mistakes and answer with the correct text between parentheses with no additional information. The language is french. ",finalScript);

      try {
        File myObj = new File("curl_script_part_2.bat");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          //System.out.println(data);
          writeLineIntoFile(data,finalScript);
          writeLineIntoFile(System.getProperty( "line.separator" ),finalScript);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }
    
  static public void createScriptFile(String name){
      try {
        File myObj = new File(name);
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }
  static public void runScript(String scriptname)throws IOException, InterruptedException {
    String script_path = "scripts\\"+ scriptname +".bat";
    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", script_path);
    pb.redirectErrorStream(true);
    Process p = pb.start();

    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder outputBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {

            System.out.println(line);
            outputBuilder.append(line);
            //writeLineIntoFile(line+System.getProperty( "line.separator" ),"./responses/"+scriptname);
            
        }
        String output = outputBuilder.toString();
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(output);
        if (matcher.find()) {
            String json = matcher.group();
            System.out.println(json);
        } else {
            System.out.println("No JSON string found in output");
        }
        // Wait for the process to finish
        try {
            int exitCode = p.waitFor();
            System.out.println("Process exited with code " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
  }
    
}