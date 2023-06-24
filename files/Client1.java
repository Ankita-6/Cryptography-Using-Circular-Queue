import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

 class CircularQueue
{
    
    public final static int MAX = 26;

    public static char[] circularQueue = new char[MAX];

    public static int front = -1;

    public static int rear = -1;

    /**
     * Inserting Alphabets into the queue
     */
    public void queueinsert(char item) 
    {
        if(front == 0 && rear == MAX - 1 || front == rear + 1) {
            System.out.print("\nQueue Overflow ");
            return;
        }
        if(front == -1) {
            front = 0;
            rear = 0;
        } else if(rear == MAX - 1) {
            rear = 0;
        } else {
            rear = rear + 1;
        }
        circularQueue[rear] = item;
    }
}

class Decrypt extends CircularQueue{
    
    
    public void decryption(int key, char[]message,int z) {
  char decryptedLetter;
  char[] decryptedMessage[];
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
          while(message[i]!='\0')
          {
                if(message[i]!=' ')
                {
                  int decryptedLetterAscii;
              
                  int queueLetter =(int) circularQueue[front];
                  int letter1 = (int)message[i];
              
                  decryptedLetterAscii =  (((letter1%65)+1)-((queueLetter%65)+1) ) ;
                  if(decryptedLetterAscii < 0) {
                  decryptedLetterAscii +=26;
                  }
                  decryptedLetterAscii +=64;
                  decryptedLetter = (char) decryptedLetterAscii;
                       if(z==1)
                             System.out.print(decryptedLetter);
                       else 
                        {
                          try{
                                FileWriter fw = new FileWriter("decryptedfile.txt",true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.write(decryptedLetter);
                                bw.close();
                                fw.close();
                                
                              }
                         catch(IOException ex){
                         return;
                         }
                       }   
                      front++;
                }
               
                else{
                   if(z==1)    
                           System.out.print(" ");
                     else 
                        {
                          try{
                                FileWriter fw = new FileWriter("decryptedfile.txt",true);
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

public class Client1{
    
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
        char[] decryptedMessage;
        String str;
        Scanner in=new Scanner(System.in);
        Decrypt ob=new Decrypt();
        for(int queueIndex2 = 65; queueIndex2 <= 90; queueIndex2++) {
            queueElement = (char)queueIndex2;
            ob.queueinsert(queueElement);
        }
        
        boolean bo=true;
        
        System.out.println("----------------CLIENT : READY TO DECRYPT----------------");
    
        while(bo)
        {
        System.out.println("\n\n\nWhat would you like to decrypt?\nPress");
        System.out.println("1. To decrypt message from standard input");
        System.out.println("2. To decrypt text file");
        System.out.println("3. To decrypt a image");
        System.out.println("0. To Exit");
        int n=in.nextInt();
        
        switch(n){
        
        case 1:
    
                 System.out.println("Enter Secret key and message to decrypt");
                 key=in.nextInt();
                 in.nextLine();
                 str=in.nextLine();
                 inputMessage1=str.toCharArray();
                 System.out.print("Decrypted Message : ");
                 ob.decryption(key,inputMessage1,1);
                 System.out.println();
                 break;
        
        case 2:
                 System.out.println("Enter Secret key ");
		         int key2=in.nextInt();
                 String theString = "";
                 FileReader fr=new FileReader("encryptedfile.txt");
                 BufferedReader br=new BufferedReader(fr);
                 String str1 = "";
        
                 while((str1 = br.readLine())!=null){
            
                    theString = theString + str1;
                    
                }
                System.out.println("Reading the File!!");
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
                 
                 File file = new File("C:\\Users\\Dell\\Desktop\\files\\decryptedfile.txt"); //initialize File object and passing path as argument  
                 boolean result;  
                try   
                {  
                result = file.createNewFile();  //creates a new file  
                if(result)      // test if successfully created a new file  
                {  
                System.out.println("file created to write decrypted message "+file.getCanonicalPath()); //returns the path string  
                }  
                else  
                {  
                System.out.println("File created to write decrypted message "+file.getCanonicalPath());  
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
        		ob.decryption(key2,inputMessage2,2);
                System.out.println();
                System.out.println("Decrypted File - decryptedfile.txt");
                System.out.println();
                try{
                  Thread.sleep(1500);
                }
                catch(InterruptedException ex)
                {
                }
                break;
                
        case 3:        
                
                 Scanner sc = new Scanner(System.in);
                 System.out.println("\nIMAGE DECRYPTION!!");
                 try{
                  Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                } 
                 System.out.print("Enter a key for Decryption : ");
                 int key1 = sc.nextInt();
                   
                    // Selecting a Image for Decryption.
                       
                    FileInputStream fis = new FileInputStream(
                    "image.png");
                     
                    // Converting image into byte array,it will
                    // Create a array of same size as image.
                    System.out.println("\nDecrypting image...");
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
                       
                    // Performing an XOR operation
                    // on each value of
                    // byte array to Decrypt it.
                    for (byte b : data) {
                        data[i] = (byte)(b ^ key1);
                        i++;
                    }
                       
                    // Opening file for writting purpose
                    FileOutputStream fos = new FileOutputStream(
                        "image.png");
                       
                    // Writting Decrypted data on Image
                    fos.write(data);
                    fos.close();
                    fis.close();
                    System.out.println("Decryption Done...");
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