/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.dao.CompanyDAO;
import com.furniture.dao.CompanyproductDAO;
import com.furniture.dao.ProductDAO;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import com.furniture.entities.Currency;
import com.furniture.entities.Imageproduct;
import com.furniture.entities.Measurment;
import com.furniture.entities.Price;
import com.furniture.entities.Product;
import com.furniture.entities.Productspecification;
import com.furniture.entities.Specification;
import com.furniture.entities.Videoproduct;
import com.furniture.util.FurnitureUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class ViewProductBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Product product;
    private String selectedNodePath;
    private List<Productspecification> dimesionProductSpecifications = new ArrayList<Productspecification>(0);
    private List<Productspecification> productSpecifications = new ArrayList<Productspecification>(0);
    private List<Price> prices = new ArrayList<Price>(0);
    private DualListModel subProducts;
    private DualListModel products;
    private List<Imageproduct> images;
    private List<Videoproduct> videos;
    private Category selectedCategory;
    private TreeNode root;
    private TreeNode selectedNode;
    private Specification selectedDimensionSpecidifcation;
    private Double amount;
    private Double discount;
    private Double initialAmount;
    private Currency currency;
    private Date priceDate;
    private Measurment selectedMeasurment;
    private Price newPrice;

    @PostConstruct
    public void init() {
        System.out.println("INITIALIZING IN VIEWPRODUCT BEAN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");
        if (sessionBean.getParameter() != null && sessionBean.getParameter() instanceof Product) {

            product = (Product) sessionBean.getParameter();
            Company company = product.getFirstCompany();


            CompanyproductDAO dao1 = new CompanyproductDAO();
            List<Product> pr = dao1.getCompanyProducts(company, Boolean.TRUE);
            Collections.sort(pr, new Comparator<Product>() {
                public int compare(Product one, Product other) {
                    return one.getName().compareTo(other.getName());
                }
            });

            images = product.getImageproducts();
            videos = product.getVideoproducts();

            CompanyDAO dao = new CompanyDAO();
            List<Category> categories = dao.getCompanyRootCategories(company, Boolean.TRUE);
            root = FurnitureUtil.getCategoriesTree(categories);

            productSpecifications = product.getOtherProductSpecifications();
            dimesionProductSpecifications = product.getDimesnionProductSpecifications();
            prices = product.getPrices();

            products = new DualListModel<Product>(pr, product.getParentproducts());
            subProducts = new DualListModel<Product>(pr, product.getSubproducts());
        }
    }

    public void reset() {
    }

    public List<Imageproduct> getImages() {
        return images;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Specification getSelectedDimensionSpecidifcation() {
        return selectedDimensionSpecidifcation;
    }

    public void setSelectedDimensionSpecidifcation(Specification selectedDimensionSpecidifcation) {
        this.selectedDimensionSpecidifcation = selectedDimensionSpecidifcation;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public Measurment getSelectedMeasurment() {
        return selectedMeasurment;
    }

    public void setSelectedMeasurment(Measurment selectedMeasurment) {
        this.selectedMeasurment = selectedMeasurment;
    }

    public Price getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Price newPrice) {
        this.newPrice = newPrice;
    }

    public void setImages(List<Imageproduct> images) {
        this.images = images;
    }

    public List<Videoproduct> getVideos() {
        return videos;
    }

    public void setVideos(List<Videoproduct> videos) {
        this.videos = videos;
    }

    public List<Productspecification> getDimesionProductSpecifications() {
        return dimesionProductSpecifications;
    }

    public void setDimesionProductSpecifications(List<Productspecification> dimesionProductSpecifications) {
        this.dimesionProductSpecifications = dimesionProductSpecifications;
    }

    public List<Productspecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(List<Productspecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public DualListModel getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(DualListModel subProducts) {
        this.subProducts = subProducts;
    }

    public DualListModel getProducts() {
        return products;
    }

    public void setProducts(DualListModel products) {
        this.products = products;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSelectedNodePath() {

        ProductDAO dao = new ProductDAO();
        return dao.getCategoryPath(product.getCategories().get(0), null);

    }

    public void setSelectedNodePath(String selectedNodePath) {
        this.selectedNodePath = selectedNodePath;
    }
}
