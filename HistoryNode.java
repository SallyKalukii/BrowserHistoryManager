class HistoryNode{ //creates a new node 
    //attributes 
    String url;
    String timestamp;
    HistoryNode prev;
    HistoryNode next;

    //overloaded constructor to initialize the attributes
    public HistoryNode(String url, String timestamp){
        this.url = url;
        this.timestamp = timestamp;
        this.prev= null;
        this.next = null;
    }

    //getters for the attributes 
    public String getUrl(){
        return this.url;
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public HistoryNode getPrev(){
        return this.prev;
    }

    public HistoryNode getNext(){
        return this.next;
    }

}

