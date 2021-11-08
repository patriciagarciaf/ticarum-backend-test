package com.example.demo.domain.productDomain;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;
import java.util.UUID;

public interface ProductWriteRepository extends FindById<Product, UUID>, ExistsByField {

    public void add(Product product);
    public void update(Product product);
    public void delete(Product product);
}
