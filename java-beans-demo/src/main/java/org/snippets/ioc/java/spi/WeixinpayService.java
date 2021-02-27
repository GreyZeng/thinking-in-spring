package org.snippets.ioc.java.spi;

public class WeixinpayService implements PayService{
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
