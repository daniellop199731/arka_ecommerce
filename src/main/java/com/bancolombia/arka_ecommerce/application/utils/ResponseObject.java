package com.bancolombia.arka_ecommerce.application.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
    
    private boolean isSuccessful;
    private Object message;
    private T obj;

    public void setAsSuccessful(String msg, T obj){
        setSuccessful(true);
        setMessage(msg);
        setObj(obj);
    }

    public void setAsNotSuccessful(String msg){
        setSuccessful(false);
        setMessage(msg);
        setObj(null);
    }    

    public ResponseObject<?> migration (ResponseObject<?> rObjDestination) {
        rObjDestination.setSuccessful(this.isSuccessful());
        rObjDestination.setMessage(this.getMessage());
        return rObjDestination;
    }

    public String getMessageAsString() {
        return message != null ? message.toString() : null;
    }
}
