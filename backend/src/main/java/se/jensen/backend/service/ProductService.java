package se.jensen.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensen.backend.repository.ProductRepository;
import se.jensen.backend.model.Product;
import se.jensen.backend.model.ColorVariant;


import java.util.List;

@Service
public class ProductService
{
    @Autowired
    ProductRepository db;

    public ProductService(ProductRepository repository)
    {
        this.db = repository;
    }

    public List<Product> getProducts() {

        return db.selectAll();
    }

    public List<Product> getProductByName(String category) {

        return db.selectAllOfCategory(category);
    }

    public Object getProductById(int id) {

        return db.getEntireProduct(id);
    }
    public Product addProduct(Product product, String category)
    {
        return db.insertProductAndProps(product, category);
    }
    public int updateProductMeta(int id, Product prod) {



        return db.updateProductMeta(id, prod);
    }

    public ColorVariant addColorVariant(int id, ColorVariant co) {

        return db.addVariant(id, co);
    }

    public int restockSizeVariant(int id,String size, String color, int quantity) {

        return db.restockSize(id, size, color, quantity);
    }

    public int deleteProduct(int id) {

        if (id > getProducts().get(getProducts().size() -1).id)
            return -2;

        return db.deleteProduct(id);
    }

    public int deleteColorVariant(int id, String colorVariants) {

        return db.deleteVariant(id, colorVariants);
    }
}






