package umc.study.apiPayload.exception.handler;


import umc.study.apiPayload.code.status.ErrorStatus;

public class FoodCategoryHandler extends RuntimeException {

    public FoodCategoryHandler(String message) {
        super(message);
    }

    public FoodCategoryHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodCategoryHandler(ErrorStatus errorStatus) {
    }
}