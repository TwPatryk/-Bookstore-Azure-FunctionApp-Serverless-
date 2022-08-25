package org.example.functions.bookstore;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.example.functions.bookstore.model.Book;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetAllBooks {
    /**
     * This function listens at endpoint "/api/getAllBooks". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/getAllBooks
     * 2. curl {your host}/api/getAllBooks?name=HTTP%20Query
     */
    @FunctionName("getAllBooks")
    public List<Book> run(
            @HttpTrigger(name = "getBooks", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.FUNCTION, route="books") HttpRequestMessage<Optional<Book>> request,
            @TableInput(name="books", tableName="Books", connection="AzureWebJobsStorage") List<Book>books,
            final ExecutionContext context) {


        return books;
    }
    }
