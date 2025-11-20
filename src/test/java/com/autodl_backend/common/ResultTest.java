package com.autodl_backend.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    public void testSuccess() {
        Result<String> success = Result.success("Hello");
        assertEquals(Result.CODE_SUCCESS, success.getCode());
        assertEquals("success", success.getMsg());
        assertEquals("Hello", success.getData());
    }

    @Test
    public void testError() {
        Result<Void> error = Result.error("Something went wrong");
        assertEquals(Result.CODE_ERROR, error.getCode());
        assertEquals("Something went wrong", error.getMsg());
        assertNull(error.getData());
    }
}
