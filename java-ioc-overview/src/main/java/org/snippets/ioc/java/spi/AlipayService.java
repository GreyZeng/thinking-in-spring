package org.snippets.ioc.java.spi;

public class AlipayService implements PayService{
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
