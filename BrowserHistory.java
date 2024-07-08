import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BrowserHistory{
    //attributes using the HistoryNodes created (types) 
    private HistoryNode head;
    private HistoryNode tail; 
    int size = 0 ; 

    //a contructor to initialize the doubly linked list with the head and tail set to null 
    public BrowserHistory(){
        head = null;
        tail = null; 
    }
    //adding a new page at the end of the history 
    public void addPage(String url, String timestamp){
       //creating an instance of the newNode
        HistoryNode newNode = new HistoryNode(url, timestamp); 
        
        //adding new page when the list is empty 
        if (head == null){
            head = newNode;
            tail = newNode; 
        }
        else{ //adding onto a list with already existing elements (to the end of the list)
            tail.next = newNode; //connecting the next link field of the tail onto the new node 
            newNode.prev = tail; //connecting the new nodes's previous linkfield to the tail 
            tail= newNode; //making the new node the tail 
        }
        
        size++; //incrementing the size of the list 
    }

    //removing a page by its timestamp 
    public void removePage(String timestamp){
        
        HistoryNode temp = head; //creating a temporary variable for iteration 

        //removing at the beginning of the list 
        if (temp != null && temp.getTimestamp().equals(timestamp)){ //if the timestamps are similar 
            if  (temp == head){ //if it is the first node
                if (head.next != null){ //ensures the node is not null
                    head = head.next; //deletes the node that was initially the head 
                    head.prev = null; //and makes it officially the head by initializing the prev linkfield to null 
                }
                else{
                    head = null; 
                    tail= null;
                }
            }
            //decrement the size of the list by 1 
            size--;
            return; //to exit the method once removed 
        }

        while (temp != null){ //iterating through the list from the beginning till the end 
            if(temp.getTimestamp().equals(timestamp)){ //if the timestamps are similar 

                //if the new node is in the end of the list 
                if (temp == tail){
                    tail = tail.prev;
                    tail.next = null; 
                }

                //if the node to be deleted is in the middle of the list
                else if (temp.prev != null && temp.next != null){ //ensures that there is a previous and and a next node 
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev; 
                }
                size--;
                return; //to exit the chunk once the node is removed 
            }
            temp = temp.next; //moves to the next node 
        }
    
        
    }

    //Displaying the browsing history from the oldest to the newest 
    public void displayHistoryForward(){
        HistoryNode temp = head; //iteration starts from the head forward 
        while (temp != null){ //iterates through the list for display 
            System.out.println(temp.timestamp + ' ' + temp.url); 
            temp = temp.next; //iteration moves to the forward direction (updates)
        }
    }

    //Displaying the browsing history from the newest to the oldest
    public void displayHistoryBackward(){
        HistoryNode temp = tail; //iteration starts from the tail backwards 
        while (temp != null){ //traverses through the list 
            System.out.println(temp.timestamp + ' ' + temp.url); 
            temp = temp.prev; //iteration moves to the backward direction (updates)
        }
    }
    //saving history to a file 
    public void saveToFile(String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        HistoryNode temp = head; // Start from the head of the list
        while (temp != null) { // Traverse through the list
            // Write the timestamp and URL of each node to the file
            writer.write(temp.timestamp + "," + temp.url);
            writer.newLine(); // Move to the next line
            temp = temp.next; // Move to the next node
        }
    } catch (IOException e) {
        e.printStackTrace(); // Print the exception if an error occurs
    }


    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory();
        // Adding pages to the history
        browserHistory.addPage("www.example1.com", "10:00 AM");
        browserHistory.addPage("www.example2.com", "10:05 AM");
        browserHistory.addPage("www.example3.com", "10:10 AM");

        //displaying the entire browse history before deletion 
        System.out.println("History Forward before deletion of a particular history:");
        browserHistory.displayHistoryForward();

        // Saving history to a file
        browserHistory.saveToFile("history.txt");

        // Removing the page visited at 10:05 AM
        browserHistory.removePage("10:05 AM");

        // Displaying the browsing history in forward order
        System.out.println("History Forward after deletion of a particular history:");
        browserHistory.displayHistoryForward();

        // Displaying the browsing history in backward order
        System.out.println("History Backward after deletion of a particular history:");
        browserHistory.displayHistoryBackward();
    }
}


