import java.io.*;
import java.util.Random;


/**
 * This program will converse with the user
 * and ask the user a series of questions.
 * Then the conversation will be printed out after
 * the series of questions + statements are over
 *
 *  @author Monique Blake
 *  @version 27 January 2011
 */


class Conversation {
  
  /*
   * Prints the conversation between the user and the computer 
   * 
   * @param conversation      conversation array between the user and computer
   * @param response          number of interaction rounds provided by the user
   */
  
  static void printConversation(String[] conversation, int response) {
           for (int i = 0; i<conversation.length; i++) {
               System.out.println(conversation[i]);
           }
  }


  public static void main(String[] args)  throws IOException{
  
    String[] cannedlist = {"How are you?", "What is your name?", "What did you do today?"
    , "Do you like candy?", "I am bored, tell me something funny",
    "Do you like cats?", "Do you have any pets?", "Do you have any siblings?", 
    "When is your birthday?", "What will you do tomorrow?" };

    int num_canned = cannedlist.length;    
    
    //Create object to handle input
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    // Get number of rounds from user
    System.out.print("How many rounds of conversation?");
    int response = Integer.parseInt(stdin.readLine());
    
    
    // Create a new array to hold the conversation between the user and computer
    String[] conversation = new String[2*(response)+1];
    
    Random r = new Random();
    int number = r.nextInt(num_canned);
    
    //Computer take any response from array, prints it, adds to conversation
    String computer = cannedlist[number];
    System.out.println(computer);
    
    //First like by computer is appended to conversation
    conversation[0] = computer;
    
    
    // perform specified number of interaction rounds
    for (int i = 0; i< response-1; i++) {
       String user = stdin.readLine();
       
       // store user interaction in conversation
       conversation[(2*i)+1] = user;
       
       // search for mirror words and construct response
       String[] userwords = user.split(" ");
       computer = "";
       
       String new_word;
       boolean mirror_words_found = false;
       
       // Find mirrored words, if they exist replace them
       
       for (int j = 0; j< userwords.length; j++) {
         if (userwords[j].equals("I")) {
           new_word = "you";
           mirror_words_found = true; 
         }
         
         else if (userwords[j].equals("you")) {
             new_word = "I";
             mirror_words_found = true;           
         }
         else if (userwords[j].equals("am")) {
             new_word = "are";
             mirror_words_found = true;           
         }
         else if (userwords[j].equals("are")) {
             new_word = "am";
             mirror_words_found = true;           
         }
         else if (userwords[j].equals("me")) {
             new_word = "you";
             mirror_words_found = true;           
         }
         else if (userwords[j].equals("my")) {
             new_word = "your";
             mirror_words_found = true;
         }
         
         else if (userwords[j].equals("you're")) {
             new_word = "I'm";
             mirror_words_found = true;           
         }         
         else if (userwords[j].equals("I'm")) {
             new_word = "you're";
             mirror_words_found = true;           
         } 
         
         else {
               new_word = userwords[j];
         }
         
         // computer replaces words and asks about it
         computer = computer + new_word + " ";
         
       }
       
       // replace punctuation
       computer = computer.replace(".", "?");
       computer = computer.replace( "!", "?");       
       
       //Use canned response if there are no mirror words
       if (!mirror_words_found) {
         number = r.nextInt(num_canned);
         computer = cannedlist[number];
       }
       
       // use whatever response we came up with
       System.out.println(computer);
       
      // store computer interaction in conversation
       conversation[(2*i)+2] = computer;


    }    

    // one last user response then gets appended to conversation
    String user = stdin.readLine();
    conversation[conversation.length-2] = user;
    
    // end the conversation
    computer = "Goodbye!";
    System.out.println(computer);
    
    // computer's goodbye appended to last index
    conversation[conversation.length-1] = computer;
    
    System.out.println();
    System.out.println();
    
    printConversation(conversation, response);
}

}
