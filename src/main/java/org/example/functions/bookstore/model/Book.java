package org.example.functions.bookstore.model;

public class Book {

    private String PartitionKey;
    private String RowKey;
    private String title;
    private String category;
    private boolean isAvailable;

    @Override
    public String toString() {
        return "Book{" +
                "PartitionKey='" + PartitionKey + '\'' +
                ", RowKey='" + RowKey + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getPartitionKey() {
        return PartitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        PartitionKey = partitionKey;
    }

    public String getRowKey() {
        return RowKey;
    }

    public void setRowKey(String rowKey) {
        RowKey = rowKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
