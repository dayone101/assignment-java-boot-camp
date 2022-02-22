package bootcamp.java.assignment.cart;

import bootcamp.java.assignment.user.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartControllerAdvice {

    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userIdNotFound(UserIdNotFoundException e) {
        return "user id " + e.getMessage() + " not found";
    }

    @ExceptionHandler(PaymentMethodInvalidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String paymentMethodInvalid(PaymentMethodInvalidException e) {
        return e.getMessage() + " is not supported\n" +
                "please select from this option\n" +
                "1. wallet\n" +
                "2. credit card\n" +
                "3. cash on delivery";
    }

    @ExceptionHandler(BalanceInsufficientException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String balanceInsufficient(BalanceInsufficientException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CartEmptyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String cartEmpty(CartEmptyException e) {
        return "your cart is empty";
    }
}
