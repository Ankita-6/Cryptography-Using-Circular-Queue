
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;



 class CircularQueue{
    
    public final static int MAX = 26;

    public static char[] circularQueue = new char[MAX];

    public static int front = -1;

    public static int rear = -1;

    // Inserting Alphabets into the queue
     
    public  void queueinsert(char item) 
    {
        if(front == 0 && rear == MAX - 1 || front == rear + 1) {
            System.out.print("\nQueue Overflow ");
            return;
        }
        
        if(front == -1) {
            front = 0;
            rear = 0;
        } 
        else if(rear == MAX - 1) {
            rear = 0;
        } 
        else {
            rear = rear + 1;
        }
        
        circularQueue[rear] = item;
    }

}
    
    
 class Encrypt extends CircularQueue{

 public  void encryption(int key, char[]message,int z) 
 {
      char encryptedLetter;
      char[] encryptedMessage[];
      int i = 0;
    
      front = key-1;
      if(front == 0) {
        rear = 25;
      }
      else {
        rear = key-2;
      }
      try
      {
          while(message[i]!='\0') {
            
             
            if(message[i]!=' ')
            {
                  int encryptedLetterAscii;
                  
                  int queueLetter =(int) circularQueue[front];
                  int letter1 = (int)message[i];
                  
                  encryptedLetterAscii = ((((queueLetter%65)+1) + ((letter1%65)+1) )%26);
                  if(encryptedLetterAscii == 0) {
                    encryptedLetterAscii = 90;
                  } 
                  else {
                    encryptedLetterAscii+= 64;
                  }
                  
                  encryptedLetter = (char) encryptedLetterAscii;
                       if(z==1)
                             System.out.print(encryptedLetter);
                       else 
                        {
                          try{
                                FileWriter fw = new FileWriter("encryptedfile.txt",true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(encryptedLetter);
                                bw.close();
                                fw.close();
                                
                              }
                         catch(IOException ex){
                         return;
                       }
                       }   
                  
                  front++;
            }
           
            
            else
            {
                     if(z==1)    
                           System.out.print(" ");
                     else 
                        {
                          try{
                                FileWriter fw = new FileWriter("encryptedfile.txt",true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(" ");
                                bw.close();
                                fw.close();
                                
                              }
                         catch(IOException ex){
                         return;
                       }
                       }   
            }
            i++;
        }
     }
    catch(ArrayIndexOutOfBoundsException aiobe)
        {
            return ;
        }

}
}



    public class Server1{
    public static void main(String[] args)
    throws FileNotFoundException, IOException
    {
        int ch;
        int choice;
        int key;
        int queueIndex = 0;
        char queueElement;
        char[] inputMessage1 = new char[300];
        char[] inputMessage2 = new char[300];
        char[] encryptedMessage;
        String str;
        Scanner in=new Scanner(System.in);
        
        Encrypt ob = new Encrypt();
        for(int queueIndex2 = 65; queueIndex2 <= 90; queueIndex2++) {
            queueElement = (char)queueIndex2;
            ob.queueinsert(queueElement);
        }
        boolean bo=true;
        System.out.println("----------------SERVER : READY TO ENCRYPT----------------");
    
        while(bo)
        {
            System.out.println("\n\n\nWhat would you like to encrypt?\nPress");
            System.out.println("1. To encrypt message from standard input");
            System.out.println("2. To encrypt text file");
            System.out.println("3. To encrypt a image");
            System.out.println("0. To Exit");
            int n=in.nextInt();
        
        switch(n){
        
        case 1: // text encryption
        
              System.out.println("Enter Secret key( between 1 to 26)  and Message to be encrypted");
              key=in.nextInt();
              in.nextLine();
              str=in.nextLine();
              inputMessage1=str.toCharArray();
              System.out.print("Encrypted Message : ");
              ob.encryption(key,inputMessage1,1);
              System.out.println();
              try{
                  Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                }
        break;
        
        case 2: // text file encryption
        
              System.out.println("Enter Secret key( between 1 to 26)");
              int key2=in.nextInt();
              
              String theString = "";
              FileReader fr=new FileReader("files.txt");
              BufferedReader br=new BufferedReader(fr);
              String str1 = "";
        
              while((str1 = br.readLine())!=null){
            
                 theString = theString + str1;
            
              }
              System.out.println("Reading the File!!");
              try{
                  Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                }
              System.out.println("File contents are : " +theString);
              System.out.println();
               try{
                  Thread.sleep(1000);
                }
                catch(InterruptedException ex)
                {
                }
              br.close();
              inputMessage2 = theString.toCharArray();
         
              File file = new File("C:\\Users\\Dell\\Desktop\\files\\encryptedfile.txt"); //initialize File object and passing path as argument  
              boolean result;  
              try   
              {  
                result = file.createNewFile();  //creates a new file  
                if(result)      // test if successfully created a new file  
                {  
                 System.out.println("File created to write encrypted message"+file.getCanonicalPath()); //returns the path string  
                }  
                else  
                {  
                System.out.println("File created to write encrypted message "+file.getCanonicalPath());  
                }  
              }   
             catch (IOException e)   
             {  
              e.printStackTrace();    //prints exception if any  
             }         
             System.out.println("\nWritting to file...");
             try{
                  Thread.sleep(1500);
                }
                catch(InterruptedException ex)
                {
                }
             ob.encryption(key2,inputMessage2,2);
             System.out.println("Encrypted File - encryptedfile.txt");
             System.out.println();
             try{
                  Thread.sleep(3000);
                }
                catch(InterruptedException ex)
                {
                }
             break;
        
        case 3: //image encryption
        
                Scanner sc = new Scanner(System.in);
                System.out.println("\nIMAGE ENCRYPTION!!");
                try{
                  Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                }
              
                // Here key is act as password to Encrypt and
                // Decrypt the Image
                System.out.print("Enter key for Encryption : ");
                int key1 = sc.nextInt();
                                    
                // Selecting a Image for operation
                FileInputStream fis = new FileInputStream(
                    "image.png");
                                    
                // Converting Image into byte array, create a
                // array of same size as Image size
                System.out.println("\nEncrypting image...");     
                try{
                  Thread.sleep(1500);
                }
                catch(InterruptedException ex)
                {
                }
                byte data[] = new byte[fis.available()];
                                    
                // Read the array
                fis.read(data);
                int i = 0;
                                    
                // Performing an XOR operation on each value of
                // byte array due to which every value of Image
                // will change.
                for (byte b : data) {
                    data[i] = (byte)(b ^ key1);
                    i++;
                }
                                    
                // Opening a file for writing purpose
                FileOutputStream fos = new FileOutputStream(
                    "image.png");
                                    
                // Writing new byte array value to image which
                // will Encrypt it.
                                    
                fos.write(data);
                                    
                // Closing file
                fos.close();
                fis.close();
                System.out.println("Encryption Done...");
                try{
                  Thread.sleep(2500);
                }
                catch(InterruptedException ex)
                {
                }
                
                break;
                
        default:
                
                 bo = false; 
                
    }
    
    }   
    }
}