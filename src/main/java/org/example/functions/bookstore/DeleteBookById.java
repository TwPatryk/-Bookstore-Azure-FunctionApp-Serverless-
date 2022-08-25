package org.example.functions.bookstore;


import java.util.*;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.example.functions.bookstore.model.Book;
import org.w3c.dom.Entity;

/**
 * Azure Functions with HTTP Trigger.
 */
public class DeleteBookById {

    /**
     * This function listens at endpoint "/api/DeleteBookById". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/DeleteBookById
     * 2. curl {your host}/api/DeleteBookById?name=HTTP%20Query
     */
    @FunctionName("DeleteBookById")
    public List<Book> run(
            @HttpTrigger(name = "deleteBook", methods = {HttpMethod.DELETE}, authLevel = AuthorizationLevel.FUNCTION, route="book/{partitionKey}/{rowKey}") HttpRequestMessage<Optional<Book>> request,
            @BindingName("partitionKey") String partitionKey,
            @BindingName("rowKey") String rowKey,
            @TableInput(name="books", partitionKey = "{partitionKey}",tableName="Books", connection="AzureWebJobsStorage") List<Book> books,
            final ExecutionContext context) {

         final String connectionString =
                "DefaultEndpointsProtocol=https;AccountName=task2308storage;AccountKey=chsnCRBzWofRP9HWjU28dZDVofhjoIZCA01v1+XMaXUWAHkI7SAaCUJgYG2syHPYEIwPj2jqT0tH+AStUsUs5w==;EndpointSuffix=core.windows.net";

        TableClient tableClient = new TableClientBuilder()
                .connectionString(connectionString)
                .tableName("Books")
                .buildClient();

        Book book = new Book();
        book.setPartitionKey(partitionKey);
        book.setRowKey(rowKey);

        tableClient.deleteEntity(book.getPartitionKey(), book.getRowKey());

        return books;
    }





}
