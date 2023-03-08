package com.example.sfs.util.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ProductRegisterStrategyFactory {
    private Map<ProductRegisterStrategy, ProductRegister> productRegisterStrategies;

    @Autowired
    public ProductRegisterStrategyFactory(Set<ProductRegister> productRegisterSet) {
        createStrategy(productRegisterSet);
    }

    public ProductRegister findStrategy(ProductRegisterStrategy productRegisterStrategy) {
        return productRegisterStrategies.get(productRegisterStrategy);
    }

    public void createStrategy(Set<ProductRegister> productRegisterSet) {
        productRegisterStrategies = new HashMap<ProductRegisterStrategy, ProductRegister>();
        productRegisterSet.forEach(
                productRegister -> productRegisterStrategies.put(productRegister.getProductRegisterStrategy(), productRegister)
        );
    }
}
