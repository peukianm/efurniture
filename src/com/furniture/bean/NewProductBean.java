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
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Currency;
import com.furniture.entities.Imageproduct;
import com.furniture.entities.Item;
import com.furniture.entities.Measurment;
import com.furniture.entities.Price;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import com.furniture.entities.Productspecification;
import com.furniture.entities.Productvalue;
import com.furniture.entities.Specification;
import com.furniture.entities.Specificationcategory;
import com.furniture.entities.Specificationvalue;
import com.furniture.entities.Users;
import com.furniture.entities.Videoproduct;
import com.furniture.util.FacesUtils;
import com.furniture.util.FurnitureUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class NewProductBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Product newProduct = new Product();
    private Company selectedCompany;
    private Category selectedCategory;
    private TreeNode root;
    private TreeNode selectedNode;
    private String selectedNodePath;
    
    private Specification selectedDimensionSpecidifcation;
    private List<Productspecification> dimesionProductSpecifications = new ArrayList<Productspecification>(0);
    private Measurment selectedMeasurment;
    private Price newPrice;
    private List<Price> prices = new ArrayList<Price>(0);
    private Double amount;
    private Double discount;
    private Double initialAmount;
    private Currency currency;
    private Date priceDate;
    
    private DualListModel<Product> products;
    private DualListModel<Product> subProducts;
    private DualListModel<Company> scopeCompanies;
    
    //private List<Company> selectedCompanies = new ArrayList<Company>(0);
    //private List<Productline> productLines = new ArrayList<Productline>(0);
    //private List<Productline> selectedProductLines = new ArrayList<Productline>(0);
    
    
    private List<Imageproduct> images = new ArrayList<Imageproduct>(0);
    private Imageproduct newImage = new Imageproduct();
    private Imageproduct selectedImage = new Imageproduct();
    private List<Videoproduct> videos = new ArrayList<Videoproduct>(0);
    private Videoproduct newVideo = new Videoproduct();
    private Videoproduct selectedVideo = new Videoproduct();
    private List<Specification> specifications = new ArrayList<Specification>(0);
    private List<Specification> selectedSpecification = new ArrayList<Specification>(0);
    private List<Specificationcategory> selectedSpecificationCat = new ArrayList<Specificationcategory>(0);
    private Specification specification = new Specification();
    //Value attributes  
    private List<Specificationvalue> svalues = new ArrayList<Specificationvalue>(0);
    private List<Specificationvalue> selectedSvalues = new ArrayList<Specificationvalue>(0);
    private Specificationvalue selectedSvalue;
    private String svalue = new String();
    private List<Productspecification> productSpecifications = new ArrayList<Productspecification>(0);
    private List<Productvalue> productValues = new ArrayList<Productvalue>(0);
    private Productspecification productSpecification;
    //private List<Product> parentProductList = new ArrayList<Product>(0);
    private List<Item> items = new ArrayList<Item>(0);
    private Item selectedItem;
    
    private String rows = "5";

    @PostConstruct
    public void init() {
        reset();
        System.out.println("ARXIKO INITIALIZATION NEWPRODUCT BEAN!!!!!!!!!!!!!!!");
        Users user = sessionBean.getUsers();

        Company company = user.getCompany();
        newProduct.setCreateddate(new Date());
        if (company != null) {
            selectedCompany = company;
            Companyproduct companyProduct = new Companyproduct();
            companyProduct.setCompany(company);
            newProduct.getCompanyproducts().add(companyProduct);
            //newProduct.setSubproduct(BigDecimal.ZERO);
            CompanyDAO dao = new CompanyDAO();
            List<Category> categories = dao.getCompanyRootCategories(company, Boolean.TRUE);
            root = FurnitureUtil.getCategoriesTree(categories);

            CompanyproductDAO dao1 = new CompanyproductDAO();
            List<Product> pr = dao1.getCompanyProducts(company, Boolean.TRUE);
            Collections.sort(pr, new Comparator<Product>() {
                public int compare(Product one, Product other) {
                    return one.getName().compareTo(other.getName());
                }
            });
            products = new DualListModel<Product>(pr, new ArrayList<Product>());
            subProducts = new DualListModel<Product>(pr, new ArrayList<Product>());
            
            ApplicationBean appBean = (ApplicationBean)FacesUtils.getManagedBean("applicationBean");                        
            List<Company> companies = new ArrayList<Company>(0);
            for (int i = 0; i < appBean.getCompanies().size(); i++) {
                Company comp = appBean.getCompanies().get(i);
                if (!comp.equals(company)) {
                    companies.add(comp);
                }                
            }
            scopeCompanies = new DualListModel<Company>(companies,new ArrayList<Company>());                      
        }
    }

    public String getSelectedNodePath() {
        if (selectedNode!=null) {
            ProductDAO dao = new ProductDAO();
            return dao.getCategoryPath((Category)selectedNode.getData(), null);            
        } else {
            return "";
        }        
    }

    public void setSelectedNodePath(String selectedNodePath) {
        this.selectedNodePath = selectedNodePath;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    
    
    public DualListModel<Company> getScopeCompanies() {
        return scopeCompanies;
    }

    public void setScopeCompanies(DualListModel<Company> scopeCompanies) {
        this.scopeCompanies = scopeCompanies;
    }
    
    
    public Imageproduct getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Imageproduct selectedImage) {
        this.selectedImage = selectedImage;
    }

    public Videoproduct getSelectedVideo() {
        return selectedVideo;
    }

    public void setSelectedVideo(Videoproduct selectedVideo) {
        this.selectedVideo = selectedVideo;
    }

    
    public DualListModel<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(DualListModel<Product> subProducts) {
        this.subProducts = subProducts;
    }
    
    
    public DualListModel<Product> getProducts() {
        return products;
    }

    public void setProducts(DualListModel<Product> products) {
        this.products = products;
    }

        
    public Measurment getSelectedMeasurment() {
        return selectedMeasurment;
    }

    public void setSelectedMeasurment(Measurment selectedMeasurment) {
        this.selectedMeasurment = selectedMeasurment;
    }

    public List<Productspecification> getDimesionProductSpecifications() {
        return dimesionProductSpecifications;
    }

    public void setDimesionProductSpecifications(List<Productspecification> dimesionProductSpecifications) {
        this.dimesionProductSpecifications = dimesionProductSpecifications;
    }

    public Specification getSelectedDimensionSpecidifcation() {
        return selectedDimensionSpecidifcation;
    }

    public void setSelectedDimensionSpecidifcation(Specification selectedDimensionSpecidifcation) {
        this.selectedDimensionSpecidifcation = selectedDimensionSpecidifcation;
    }

    public Price getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Price newPrice) {
        this.newPrice = newPrice;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
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

    //PreDestroy
    public void reset() {
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

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<Imageproduct> getImages() {
        return images;
    }

    public void setImages(List<Imageproduct> images) {
        this.images = images;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

//    public List<Companycatalogue> getCatalogues() {
//        return catalogues;
//    }
//
//    public void setCatalogues(List<Companycatalogue> catalogues) {
//        this.catalogues = catalogues;
//    }
//
//    public List<Companycatalogue> getSelectedCatalogues() {
//        return selectedCatalogues;
//    }
//
//    public void setSelectedCatalogues(List<Companycatalogue> selectedCatalogues) {
//        this.selectedCatalogues = selectedCatalogues;
//    }
//
//    public List<Catalogueproductline> getProductLines() {
//        return productLines;
//    }
//
//    public void setProductLines(List<Catalogueproductline> productLines) {
//        this.productLines = productLines;
//    }
//
//    public List<Catalogueproductline> getSelectedProductLines() {
//        return selectedProductLines;
//    }
//
//    public void setSelectedProductLines(List<Catalogueproductline> selectedProductLines) {
//        this.selectedProductLines = selectedProductLines;
//    }
//    public List<Company> getSelectedCompanies() {
//        return selectedCompanies;
//    }
//
//    public void setSelectedCompanies(List<Company> selectedCompanies) {
//        this.selectedCompanies = selectedCompanies;
//    }
//
//    public List<Productline> getProductLines() {
//        return productLines;
//    }
//
//    public void setProductLines(List<Productline> productLines) {
//        this.productLines = productLines;
//    }
//
//    public List<Productline> getSelectedProductLines() { 
//        return selectedProductLines;
//    }
//
//    public void setSelectedProductLines(List<Productline> selectedProductLines) {
//        this.selectedProductLines = selectedProductLines;
//    }
//    private StreamedContent imagem = new DefaultStreamedContent();
//    public StreamedContent getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(StreamedContent imagem) {
//        this.imagem = imagem;
//    }
//    
//    
//    
    public Imageproduct getNewImage() {
        return newImage;
    }

    public void setNewImage(Imageproduct newImage) {
        this.newImage = newImage;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

//    public List<Product> getParentProductList() {
//        return parentProductList;
//    }
//
//    public void setParentProductList(List<Product> parentProductList) {
//        this.parentProductList = parentProductList;
//    }
    public Productspecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(Productspecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getSvalue() {
        return svalue;
    }

    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    public Specificationvalue getSelectedSvalue() {
        return selectedSvalue;
    }

    public void setSelectedSvalue(Specificationvalue selectedSvalue) {
        this.selectedSvalue = selectedSvalue;
    }

    public List<Productspecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(List<Productspecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public List<Productvalue> getProductValues() {
        return productValues;
    }

    public void setProductValues(List<Productvalue> productValues) {
        this.productValues = productValues;
    }

    public List<Specificationvalue> getSvalues() {
        return svalues;
    }

    public void setSvalues(List<Specificationvalue> svalues) {
        this.svalues = svalues;
    }

    public List<Specificationvalue> getSelectedSvalues() {
        return selectedSvalues;
    }

    public void setSelectedSvalues(List<Specificationvalue> selectedSvalues) {
        this.selectedSvalues = selectedSvalues;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public List<Specification> getSelectedSpecification() {
        return selectedSpecification;
    }

    public void setSelectedSpecification(List<Specification> selectedSpecification) {
        this.selectedSpecification = selectedSpecification;
    }

    public List<Specificationcategory> getSelectedSpecificationCat() {
        return selectedSpecificationCat;
    }

    public void setSelectedSpecificationCat(List<Specificationcategory> selectedSpecificationCat) {
        this.selectedSpecificationCat = selectedSpecificationCat;
    }

    public List<Videoproduct> getVideos() {
        return videos;
    }

    public void setVideos(List<Videoproduct> videos) {
        this.videos = videos;
    }

    public Videoproduct getNewVideo() {
        return newVideo;
    }

    public void setNewVideo(Videoproduct newVideo) {
        this.newVideo = newVideo;
    }
}
