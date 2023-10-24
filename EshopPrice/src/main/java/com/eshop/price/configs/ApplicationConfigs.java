package com.eshop.price.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties("application")
public class ApplicationConfigs {

    @Value("${iva}")
    private int iva;

    public int getIva() {
        System.out.println(iva);
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }
}
