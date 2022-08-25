package org.example.functions.bookstore;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.example.functions.bookstore.model.Book;

/**
 * Azure Functions with HTTP Trigger.
 */
public class AddBook {
    /**
     * This function listens at endpoint "/api/addBook". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/addBook
     * 2. curl {your host}/api/addBook?name=HTTP%20Query
     */
    @FunctionName("addBook")
    public HttpResponseMessage get(
            @HttpTrigger(name = "postBook", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION, route="books/{partitionKey}/{rowKey}") HttpRequestMessage<Optional<Book>> request,
            @BindingName("partitionKey") String partitionKey,
            @BindingName("rowKey") String rowKey,
            @TableOutput(name="book", partitionKey="{partitionKey}", rowKey = "{rowKey}", tableName="Books", connection="AzureWebJobsStorage") OutputBinding<Book> book,
            final ExecutionContext context) {

        Book outBook = new Book();
        outBook.setPartitionKey(partitionKey);
        outBook.setRowKey(rowKey);
        outBook.setTitle(request.getBody().get().getTitle());
        outBook.setCategory(request.getBody().get().getCategory());
        outBook.setAvailable(request.getBody().get().isAvailable());

        book.setValue(outBook);

        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(outBook)
                .build();
    }
}
