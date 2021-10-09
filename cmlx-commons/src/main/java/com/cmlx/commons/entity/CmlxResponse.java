package com.cmlx.commons.entity;

import java.util.HashMap;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 17:06
 * @Desc ->
 **/
public class CmlxResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public CmlxResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public CmlxResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public CmlxResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}