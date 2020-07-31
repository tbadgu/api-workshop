package com.badgu.apiworkshop.controller;

import com.atlassian.oai.validator.springmvc.InvalidRequestException;
import com.atlassian.oai.validator.springmvc.InvalidResponseException;
import com.badgu.apiworkshop.exception.TodoNotFoundException;
import com.badgu.apiworkshop.model.ErrorInfo;
import com.badgu.apiworkshop.model.Todo;
import com.badgu.apiworkshop.model.ValidationErrorInfo;
import com.badgu.apiworkshop.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get All Todo optionally by done flag",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Todo Item Found")
            })
    @Parameter(in = ParameterIn.QUERY,
            name = "done",
            description = "Done Flag",
            example = "true")
    public List<Todo> getallTodo(@RequestParam Optional<Boolean> done) {
        if (done.isEmpty())
            return this.todoService.getAllTodo();
        else
            return this.todoService.getTodoByDoneFlag(done.get());
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Todo By ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Todo Item Found"),
                    @ApiResponse(responseCode = "400", description = "Todo Item not found",
                            content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
                    @ApiResponse(responseCode = "422", description = "Request Validation Failed",
                            content = @Content(schema = @Schema(implementation = ValidationErrorInfo.class)))
            })
    @Parameter(in = ParameterIn.PATH,
            name = "id",
            description = "ID of the Todo Item to fetch",
            required = true,
            example = "1")
    public Todo getTodoById(@PathVariable @NotNull @Min(1) Long id) {
        return this.todoService.getTodoById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Delete Todo By ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Todo Item Deleted"),
                    @ApiResponse(responseCode = "400", description = "Todo Item not found",
                            content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
                    @ApiResponse(responseCode = "422", description = "Request Validation Failed",
                            content = @Content(schema = @Schema(implementation = ValidationErrorInfo.class)))
            })
    @Parameter(in = ParameterIn.PATH,
            name = "id",
            description = "ID of the Todo Item to delete",
            required = true,
            example = "1")
    public void deleteTodoById(@PathVariable @NotNull @Min(1) Long id) {
        this.todoService.deleteTodoById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Todo",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Todo Item Created"),
                    @ApiResponse(responseCode = "422", description = "Request Validation Failed",
                            content = @Content(schema = @Schema(implementation = ValidationErrorInfo.class)))
            })
    public Todo addTodo(@RequestBody @NotNull @Valid Todo todo) {
        return this.todoService.addTodo(todo);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo handleTodoNotFoundException(HttpServletRequest req, TodoNotFoundException ex) {
        return new ErrorInfo(
                req.getRequestURL().toString(),
                String.format("Todo with id %s not found", ex.getId())
        );
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorInfo handleInvalidRequestException(HttpServletRequest req, InvalidRequestException ex) {
        return new ValidationErrorInfo(
                req.getRequestURL().toString(),
                "Request Validation Failed",
                new ValidationErrorInfo.ValidationErrorReport(ex.getValidationReport().getMessages())
        );
    }

    @ExceptionHandler(InvalidResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ValidationErrorInfo handleInvalidRequestException(HttpServletRequest req, InvalidResponseException ex) {
        return new ValidationErrorInfo(
                req.getRequestURL().toString(),
                "Response Validation Failed",
                new ValidationErrorInfo.ValidationErrorReport(ex.getValidationReport().getMessages())
        );
    }

}
