package org.example.functions.bookstore;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.example.functions.bookstore.model.Book;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetBookById {
    /**
     * This function listens at endpoint "/api/getBookById". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/getBookById
     * 2. curl {your host}/api/getBookById?name=HTTP%20Query
     */
    @FunctionName("getBookById")
    public List<Book> run(
            @HttpTrigger(name = "getBook", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.FUNCTION, route="book/{partitionKey}") HttpRequestMessage<Optional<Book>> request,
            //@BindingName("partitionKey") String partitionKey,
            @TableInput(name="books", partitionKey = "{partitionKey}", tableName="Books", connection="AzureWebJobsStorage") List<Book> books,
            final ExecutionContext context) {


        return books;


    }
}
