package pro.vhbchieu.sStore.config.constant;

import lombok.Getter;

@Getter
public enum ErrorContent {
    EMAIL_ALREADY_EXIST("Email already exist"),
    EMAIL_NOT_EXIST("Email not exist"),
    ACCOUNT_NOT_EXIST("Account not exist"),
    ACCOUNT_INACTIVE("Account inactive"),
    ACCOUNT_STATUS_INVALID("Account status invalid"),
    AUTHENTICATION_FAILED("Authentication failed"),
    TOKEN_INVALID("Token invalid"),
    LOGIN_WITH_FRESH_TOKEN("Cannot login with fresh token"),
    RE_PASSWORD_NOT_MATCH("Re-password not match"),
    PASSWORD_NOT_MATCH("Password not match"),
    SLUG_ALREADY_EXIST("Slug already exist"),
    ROLE_NOT_EXIST("Role not exist"),
    CATEGORY_NOT_EXIST("Category not exist"),
    BRAND_NOT_EXIST("Brand not exist"),
    PRODUCT_NOT_EXIST("Product not exist"),
    VARIANT_SKU_ALREADY_EXIST("Variant SKU already exist"),
    PRODUCT_VARIANT_NOT_EXIST("Product variant not exist"),
    
    // Product Image related errors
    PRODUCT_IMAGE_INVALID("Product image data is invalid"),
    PRODUCT_IMAGE_NOT_FOUND("Product image not found"),
    PRODUCT_IMAGE_ID_REQUIRED("Product image ID is required"),
    PRODUCT_VARIANT_ID_REQUIRED("Product variant ID is required"),

    // Order
    CART_NOT_FOUND("Cart not found"),
    ORDER_NOT_FOUND("Order not found"),
    STOCK_NOT_ENOUGH("Stock not enough"),

    // File
    UPLOAD_FILE_FAILED("Upload file failed"),
    GET_FILE_FAILED("Get file failed"),
    EMPTY_FILE("Empty file"),
    FILE_FORMAT_NOT_SUPPORT("File format not support"),
    SAVED_FILE_FAILED("Saved file failed"),
    FILE_NOT_FOUND("File not found"),
    DELETE_FILE_FAILED("Delete file failed"),
    ;
    private final String content;

    ErrorContent(String content) {
        this.content = content;
    }
}
