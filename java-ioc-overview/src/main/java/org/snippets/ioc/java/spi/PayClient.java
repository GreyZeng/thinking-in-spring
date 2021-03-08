package org.snippets.ioc.java.spi;

import java.util.ServiceLoader;

public class PayClient {
    public static void main(String[] args) {
        ServiceLoader<PayService> serviceLoader = ServiceLoader.load(PayService.class);
        
        for (PayService ele : serviceLoader) {
            ele.pay();
        }
    }
}
