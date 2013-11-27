/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.action;

import com.furniture.bean.ErrorBean;
import com.furniture.bean.NewProductBean;
import com.furniture.bean.PriceBean;
import com.furniture.bean.ProductSearchBean;
import com.furniture.bean.SessionBean;
import com.furniture.bean.UpdateProductBean;
import com.furniture.bean.UserBean;
import com.furniture.dao.CompanyDAO;
import com.furniture.dao.CompanyproductDAO;
import com.furniture.dao.ItemDAO;
import com.furniture.dao.ItemspecificationDAO;
import com.furniture.dao.PriceDAO;
import com.furniture.dao.ProductDAO;
import com.furniture.dao.SpecificationDAO;
import com.furniture.dao.SpecificationvalueDAO;
import com.furniture.entities.Catalogue;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Imageproduct;
import com.furniture.entities.Item;
import com.furniture.entities.Price;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import com.furniture.entities.Productlineproduct;
import com.furniture.entities.Productspecification;
import com.furniture.entities.Productvalue;
import com.furniture.entities.Specification;
import com.furniture.entities.Specificationcategory;
import com.furniture.entities.Specificationvalue;
import com.furniture.entities.Videoproduct;
import com.furniture.util.FacesUtils;
import com.furniture.util.FurnitureUtil;
import com.furniture.util.IOUtils;
import com.furniture.util.MessageBundleLoader;
import com.furniture.util.PersistenceHelper;
import com.furniture.util.PersistenceUtil;
import com.furniture.util.SystemParameters;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author peukianm
 */
public class FurnitureAction {

    private static final Logger logger = Logger.getLogger(FurnitureAction.class);
    private SessionBean sessionBean = (SessionBean) FacesUtils.getManagedBean("sessionBean");
    @EJB
    private PersistenceUtil persistenceUtil;
    @EJB
    private PersistenceHelper persistenceHelper;

    public String createProduct() {
        try {
            //sessionBean.setParameter(null);
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_CREATE_PRODUCT"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("insertProduct"));
            return "createProduct?faces-redirect=true "; //paraBean.
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String updateProduct(Product product) {
        try {
            //sessionBean.setParameter(null);
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            updateProductBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_UPDATE_PRODUCT"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("updateProduct"));
            return "updateProduct?faces-redirect=true "; //paraBean.
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String priceProduct() {

        try {
            //sessionBean.setParameter(null);
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            priceBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_PRICE_PRODUCT"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("productPricing"));
            return "priceProduct?faces-redirect=true "; //paraBean.
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String searchProduct() {

        try {
            //sessionBean.setParameter(null);
            ProductSearchBean productSearchBean = new ProductSearchBean();
            productSearchBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_SEARCH_PRODUCT"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("searchProduct"));
            return "searchProduct?faces-redirect=true "; //paraBean.
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void handleCompanySelect() {
        System.out.println("HANDLING CHANGE COMPAMNY !!!!");
        try {
        NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
        Company company = newProductBean.getSelectedCompany();
        if (company!=null) {
            CompanyDAO dao = new CompanyDAO();
            List<Category> categories = dao.getCompanyRootCategories(company, Boolean.TRUE);
            TreeNode root = FurnitureUtil.getCategoriesTree(categories);
            newProductBean.setRoot(root);     
            CompanyproductDAO dao1 = new CompanyproductDAO();
            List<Product> products = dao1.getCompanyProducts(company, Boolean.TRUE);
            Collections.sort(products, new Comparator<Product>() {
                public int compare(Product one, Product other) {
                    return one.getName().compareTo(other.getName());
                }
            });
             
            newProductBean.setProducts(new DualListModel<Product>(products, new ArrayList<Product>()));
            newProductBean.setSubProducts(new DualListModel<Product>(products, new ArrayList<Product>()));
        }
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Imageproduct newImage = new Imageproduct();
            newImage.setActive(BigDecimal.ONE);
            newImage.setFilename(file.getFileName());
            newImage.setProduct(newProductBean.getNewProduct());
            newImage.setPath(path + "\\" + file.getFileName());
            newProductBean.setNewImage(newImage);

            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void handleFileUploadVideo(FileUploadEvent event) {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Videoproduct newVideo = new Videoproduct();
            newVideo.setActive(BigDecimal.ONE);
            newVideo.setFilename(file.getFileName());
            newVideo.setProduct(newProductBean.getNewProduct());
            newVideo.setPath(path + "\\" + file.getFileName());
            newProductBean.setNewVideo(newVideo);

            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void savePhoto() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            List<Imageproduct> images = newProductBean.getImages();
            images.add(newProductBean.getNewImage());
            newProductBean.setNewImage(new Imageproduct());
            FacesUtils.callRequestContext("dialogFotos.hide();");
            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newPhotoUploaded"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void saveVideo() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            List<Videoproduct> videos = newProductBean.getVideos();
            videos.add(newProductBean.getNewVideo());
            newProductBean.setNewVideo(new Videoproduct());
            FacesUtils.callRequestContext("dialogVideos.hide();");
            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newVideoUploaded"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void openSelectImageDlg() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.setNewImage(new Imageproduct());
            FacesUtils.callRequestContext("dialogFotos.show();");
            FacesUtils.updateHTMLComponnetWIthJS("imgData");

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }
 
    public void openSelectVideoDlg() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.setNewVideo(new Videoproduct());
            FacesUtils.updateHTMLComponnetWIthJS("videoData");
            FacesUtils.callRequestContext("dialogVideos.show();");
            

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void showSpecifications() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            List<Specificationcategory> specs = newProductBean.getSelectedSpecificationCat();
            SpecificationDAO dao = new SpecificationDAO();
            Set<Specification> specifications = new HashSet<Specification>(0);
            for (int i = 0; i < specs.size(); i++) {
                Specificationcategory specificationcategory = (Specificationcategory) specs.get(i);
                specifications.addAll(dao.findByProperty("specificationcategory", specificationcategory));
            }

            if (newProductBean.getNewProduct().getItem() != null) {
                ItemspecificationDAO d = new ItemspecificationDAO();
                specifications.addAll(d.fetchItemSpecifications(newProductBean.getNewProduct().getItem(), true, false));
            }


            newProductBean.setSpecifications(new ArrayList<Specification>(specifications));
            FacesUtils.callRequestContext("selectSpecDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void selectSpecification() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Specification spec = newProductBean.getSpecification();
            SpecificationvalueDAO dao = new SpecificationvalueDAO();
            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);

            newProductBean.setSvalue(null);
            newProductBean.setSelectedSvalues(null);
            newProductBean.setSelectedSvalue(null);

            newProductBean.setSvalues(svalues);
            FacesUtils.callRequestContext("selectValueDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void selectSValues() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Product newProduct = newProductBean.getNewProduct();
            Specification spec = newProductBean.getSpecification();

            List<Productspecification> productSpecifications = newProductBean.getProductSpecifications();
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setProduct(newProduct);
            productSpecifications.add(productSpecification);
            productSpecification.setActive(BigDecimal.ONE);

            List<Productvalue> productValues = newProductBean.getProductValues();

            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {
                List<Specificationvalue> svalues = newProductBean.getSelectedSvalues();
                for (int i = 0; i < svalues.size(); i++) {
                    Productvalue productvalue = new Productvalue();
                    productvalue.setProductspecification(productSpecification);
                    Specificationvalue specificationvalue = svalues.get(i);
                    productvalue.setSvalue(specificationvalue.getSvalue());
                    productValues.add(productvalue);
                    productvalue.setActive(BigDecimal.ONE);
                    productSpecification.getProductvalues().add(productvalue);
                }

            }


            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ZERO)) {
                Specificationvalue svalue = newProductBean.getSelectedSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setSvalue(svalue.getSvalue());
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

            if (spec.getFreetext().equals(BigDecimal.ONE)) {
                String svalue = newProductBean.getSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setValue(svalue);
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }


            System.out.println("productSpecifications=" + productSpecifications.size());
            System.out.println("productValues=" + productValues.size());

            newProductBean.setProductSpecifications(productSpecifications);
            newProductBean.setProductValues(productValues);

            FacesUtils.callRequestContext("selectValueDialog.hide();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void deleteProductSpecification() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            List<Productspecification> productSpecifications = newProductBean.getProductSpecifications();
            Productspecification productSpecification = newProductBean.getProductSpecification();                        
            productSpecifications.remove(productSpecification);            
            newProductBean.setProductSpecifications(productSpecifications);            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
            
            
    
    
    
    
     
    
    
    
    
    
    public void saveImageOrder() {
        try {
            System.out.println("SAVING ORDER");
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Imageproduct image = newProductBean.getSelectedImage();
            FacesUtils.callRequestContext("updateImageOrderDialog.hide()");            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    public void saveImageDescription() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Imageproduct image = newProductBean.getSelectedImage();
            FacesUtils.callRequestContext("updateImageDescriptionDialog.hide()");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    public void removeImage() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Imageproduct image = newProductBean.getSelectedImage();
            List<Imageproduct> images = newProductBean.getImages();
            images.remove(image);
            newProductBean.setImages(images);
            FacesUtils.callRequestContext("updateImageDialog.hide()");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    public void saveVideoOrder() {
        try {            
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Videoproduct video = newProductBean.getSelectedVideo();
            FacesUtils.callRequestContext("updateVideoOrderDialog.hide()");            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    public void saveVideoDescription() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Videoproduct video = newProductBean.getSelectedVideo();
            FacesUtils.callRequestContext("updateVideoDescriptionDialog.hide()");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    public void removeVideo() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Videoproduct video = newProductBean.getSelectedVideo();
            List<Videoproduct> videos = newProductBean.getVideos();
            videos.remove(video);
            newProductBean.setVideos(videos);
            FacesUtils.callRequestContext("updateVideoDialog.hide()");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    public void showDimensionSpecifications() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Item item = newProductBean.getNewProduct().getItem();
            ItemspecificationDAO dao = new ItemspecificationDAO();
            List<Specification> specs = dao.fetchItemDimensionSpecifications(item, Boolean.TRUE);
            newProductBean.setSpecifications(specs);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    public void insertPriceCreateProduct() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Price newPrice = new Price();
            newPrice.setAmount(newProductBean.getAmount());
            newPrice.setInitialprice(newProductBean.getInitialAmount());
            newPrice.setDiscount(newProductBean.getDiscount());
            newPrice.setCompany(newProductBean.getSelectedCompany());
            
            newPrice.setActive(BigDecimal.ONE);
            newPrice.setProduct(newProductBean.getNewProduct());
            newPrice.setPricedate(newProductBean.getPriceDate());
            newPrice.setCurrency(newProductBean.getCurrency());

            List<Price> prices = new ArrayList<Price>(0);
            prices.add(newPrice);
            newProductBean.setPrices(prices);
            
            newProductBean.setAmount(null);
            newProductBean.setInitialAmount(null);
            newProductBean.setDiscount(null);
            newProductBean.setPriceDate(null);
            newProductBean.setCurrency(null);
            System.out.println("PROCES.size="+newProductBean.getPrices().size());
            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    public void resetPriceCreateProduct() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.setAmount(null);
            newProductBean.setInitialAmount(null);
            newProductBean.setDiscount(null);
            newProductBean.setPriceDate(null);
            newProductBean.setCurrency(null);
            System.out.println("RESET!!!!!!!!!!!!!!!");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    public void resetPriceValue() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.setPrices(new ArrayList<Price>(0));            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    
            
     public void insertDimensionAction() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
             
            Product newProduct = newProductBean.getNewProduct();
            Specification spec = newProductBean.getSpecification();

            
            List<Productspecification> dimensionProductSpecifications = newProductBean.getDimesionProductSpecifications();
            System.out.println("SPECIFICATIO PRIN TO ADD="+dimensionProductSpecifications.size());
            
            
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setProduct(newProduct);
            productSpecification.setActive(BigDecimal.ONE);
            
            
            
            
            List<Productvalue> productValues = newProductBean.getProductValues();
            String svalue = newProductBean.getSvalue();               
            Productvalue productvalue = new Productvalue();
            productvalue.setProductspecification(productSpecification);
            productvalue.setValue(svalue);            
            productvalue.setActive(BigDecimal.ONE);
            productvalue.setMeasurment(newProductBean.getSelectedMeasurment());
            productValues.add(productvalue);            
            productSpecification.getProductvalues().add(productvalue);
            
            dimensionProductSpecifications.add(productSpecification);
            
            System.out.println("SPECIFICATIO META TO ADD="+dimensionProductSpecifications.size());
            newProductBean.setDimesionProductSpecifications(dimensionProductSpecifications);
            newProductBean.setProductValues(productValues);

            FacesUtils.callRequestContext("selectDimensionValueDialog.hide();");
            
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    public void deleteProductDimensionSpecification() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            List<Productspecification> productDimensionSpecifications = newProductBean.getDimesionProductSpecifications();
            Productspecification productSpecification = newProductBean.getProductSpecification();            
            productDimensionSpecifications.remove(productSpecification);            
            newProductBean.setDimesionProductSpecifications(productDimensionSpecifications);
            FacesUtils.updateHTMLComponnetWIthJS("dimensionForm");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
   
    public void selectPProduct(TransferEvent event) {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
             for(Object item : event.getItems()) {  
                  System.out.println(item);
            }    

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
            
//    public void handleSubproductSelect() {
//        try {
//            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
//            Product newProduct = newProductBean.getNewProduct();
//
//            if (newProduct.getSubproduct().equals(BigDecimal.ONE)) {
//                Company company = newProductBean.getSelectedCompany();                
//                if (company == null) {
//                    sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noCompanySelected"));
//                    sessionBean.setShowGeneralDialog(Boolean.TRUE);
//                    return;
//                } else {
//                    List<Product> products = new ArrayList<Product>(0);
//                    newProduct.setSubproduct(BigDecimal.ONE);
//                    CompanyproductDAO dao = new CompanyproductDAO();
//                    products = dao.getCompanyProducts(company, Boolean.TRUE);
//
//                    Collections.sort(products, new Comparator<Product>() {
//                        public int compare(Product one, Product other) {
//                            return one.getName().compareTo(other.getName());
//                        }
//                    });
//
//                    newProductBean.setParentProductList(products);
//                    FacesUtils.updateHTMLComponnetWIthJS("selectParentProduct");
//                }
//            } else {
//                newProduct.setSubproduct(BigDecimal.ZERO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }

    public void insertProduct() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            if (newProductBean.getImages() == null || newProductBean.getImages().size() == 0) {
                System.out.println("no images!!!!!!");
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noImagesInserted"));
                sessionBean.setShowGeneralDialog(Boolean.TRUE);
                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
                return;
            }

            if (newProductBean.getProductSpecifications() == null || newProductBean.getProductSpecifications().size() == 0) {
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noSpecificationsInserted"));
                sessionBean.setShowGeneralDialog(Boolean.TRUE);
                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
                return;
            }


            Product product = newProductBean.getNewProduct();
            product.setActive(BigDecimal.ONE);


            Company company = newProductBean.getSelectedCompany();
            List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);            
            Companyproduct companyproduct = new Companyproduct();
            companyproduct.setActive(BigDecimal.ONE);
            companyproduct.setCompany(company);
            companyproduct.setProduct(product);
            companyproducts.add(companyproduct);            
            product.setCompanyproducts(companyproducts);


//            List<Productline> productlines = newProductBean.getSelectedProductLines();
//            List<Productlineproduct> productlineproducts = new ArrayList<Productlineproduct>(0);
//            for (int i = 0; i < productlines.size(); i++) {
//                Productline productline = productlines.get(i);
//                Productlineproduct productlineproduct = new Productlineproduct();
//                productlineproduct.setActive(BigDecimal.ONE);
//                productlineproduct.setProduct(product);
//                productlineproduct.setProductline(productline);
//                productlineproducts.add(productlineproduct);
//            }
//            product.setProductlineproducts(productlineproducts);



            List<Productspecification> productSpecifications = newProductBean.getProductSpecifications();
            product.setProductspecifications(productSpecifications);

            List<Imageproduct> images = newProductBean.getImages();
            product.setImageproducts(images);

            List<Videoproduct> videos = newProductBean.getVideos();
            product.setVideoproducts(videos);

            FacesUtils.updateHTMLComponnetWIthJS("productPreviewForm");
            FacesUtils.callRequestContext("productPreviewDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void handleCompanySelectUpdate() {
        System.out.println("HANDLING CHANGE COMPAMNY !!!!");
        UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");

        Set<Productline> productLines = new HashSet<Productline>(0);

        for (int i = 0; i < updateProductBean.getSelectedCompanies().size(); i++) {
            Company company = updateProductBean.getSelectedCompanies().get(i);
            productLines.addAll(FurnitureUtil.getProductLineFromCompany(company));

        }

        List<Productline> pl = new ArrayList<Productline>(productLines);
        Collections.sort(pl, new Comparator<Productline>() {
            public int compare(Productline one, Productline other) {
                return one.getName().compareTo(other.getName());
            }
        });
        updateProductBean.setProductLines(pl);
        
    }

    public void handleFileUploadUpdate(FileUploadEvent event) {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Imageproduct newImage = new Imageproduct();
            newImage.setActive(BigDecimal.ONE);
            newImage.setFilename(file.getFileName());
            newImage.setProduct(updateProductBean.getUpdateProduct());
            newImage.setPath(path + "\\" + file.getFileName());
            updateProductBean.setNewImage(newImage);

            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void handleFileUploadVideoUpdate(FileUploadEvent event) {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Videoproduct newVideo = new Videoproduct();
            newVideo.setActive(BigDecimal.ONE);
            newVideo.setFilename(file.getFileName());
            newVideo.setProduct(updateProductBean.getUpdateProduct());
            newVideo.setPath(path + "\\" + file.getFileName());
            updateProductBean.setNewVideo(newVideo);

            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void savePhotoUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            List<Imageproduct> images = updateProductBean.getImages();
            images.add(updateProductBean.getNewImage());
            updateProductBean.setNewImage(new Imageproduct());
            FacesUtils.callRequestContext("dialogFotos.hide();");
            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newPhotoUploaded"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void saveVideoUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            List<Videoproduct> videos = updateProductBean.getVideos();
            videos.add(updateProductBean.getNewVideo());
            updateProductBean.setNewVideo(new Videoproduct());
            FacesUtils.callRequestContext("dialogVideos.hide();");
            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newVideoUploaded"), null);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void openSelectImageDlgUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            updateProductBean.setNewImage(new Imageproduct());
            FacesUtils.callRequestContext("dialogFotos.show();");
            FacesUtils.updateHTMLComponnetWIthJS(":imgData");

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void openSelectVideoDlgUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            updateProductBean.setNewVideo(new Videoproduct());
            FacesUtils.callRequestContext("dialogVideos.show();");
            FacesUtils.updateHTMLComponnetWIthJS(":videoData");

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void showSpecificationsUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            List<Specificationcategory> specs = updateProductBean.getSelectedSpecificationCat();
            SpecificationDAO dao = new SpecificationDAO();
            Set<Specification> specifications = new HashSet<Specification>(0);
            for (int i = 0; i < specs.size(); i++) {
                Specificationcategory specificationcategory = (Specificationcategory) specs.get(i);
                specifications.addAll(dao.findByProperty("specificationcategory", specificationcategory));
            }

            if (updateProductBean.getUpdateProduct().getItem() != null) {
                ItemspecificationDAO d = new ItemspecificationDAO();
                specifications.addAll(d.fetchItemSpecifications(updateProductBean.getUpdateProduct().getItem(), true, false));
            }


            updateProductBean.setSpecifications(new ArrayList<Specification>(specifications));
            FacesUtils.callRequestContext("selectSpecDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void selectSpecificationUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            Specification spec = updateProductBean.getSpecification();
            SpecificationvalueDAO dao = new SpecificationvalueDAO();
            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);

            updateProductBean.setSvalue(null);
            updateProductBean.setSelectedSvalues(null);
            updateProductBean.setSelectedSvalue(null);

            updateProductBean.setSvalues(svalues);
            FacesUtils.callRequestContext("selectValueDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void selectSValuesUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            Product updateProduct = updateProductBean.getUpdateProduct();
            Specification spec = updateProductBean.getSpecification();

            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setProduct(updateProduct);
            productSpecifications.add(productSpecification);
            productSpecification.setActive(BigDecimal.ONE);

            List<Productvalue> productValues = updateProductBean.getProductValues();

            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {
                List<Specificationvalue> svalues = updateProductBean.getSelectedSvalues();
                for (int i = 0; i < svalues.size(); i++) {
                    Productvalue productvalue = new Productvalue();
                    productvalue.setProductspecification(productSpecification);
                    Specificationvalue specificationvalue = svalues.get(i);
                    productvalue.setSvalue(specificationvalue.getSvalue());
                    productValues.add(productvalue);
                    productvalue.setActive(BigDecimal.ONE);
                    productSpecification.getProductvalues().add(productvalue);
                }

            }


            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ZERO)) {
                Specificationvalue svalue = updateProductBean.getSelectedSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setSvalue(svalue.getSvalue());
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

            if (spec.getFreetext().equals(BigDecimal.ONE)) {
                String svalue = updateProductBean.getSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setValue(svalue);
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }


            System.out.println("productSpecifications=" + productSpecifications.size());
            System.out.println("productValues=" + productValues.size());

            updateProductBean.setProductSpecifications(productSpecifications);
            updateProductBean.setProductValues(productValues);

            FacesUtils.callRequestContext("selectValueDialog.hide();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void deleteProductSpecificationUpdate() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
            Productspecification productSpecification = updateProductBean.getProductSpecification();
            productSpecifications.remove(productSpecification);
            System.out.println("size=" + productSpecifications.size());
            updateProductBean.setProductSpecifications(productSpecifications);

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

//    public void handleSubproductSelectUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            Product newProduct = updateProductBean.getUpdateProduct();
//
//            if (newProduct.getSubproduct().equals(BigDecimal.ONE)) {
//
//                List<Company> companies = updateProductBean.getSelectedCompanies();
//                newProduct.setSubproduct(BigDecimal.ONE);
//                if (companies == null || companies.size() == 0) {
//                    sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noCompanySelected"));
//                    sessionBean.setShowGeneralDialog(Boolean.TRUE);
//                    return;
//                } else {
//                    List<Product> products = new ArrayList<Product>(0);
//
//                    CompanyproductDAO dao = new CompanyproductDAO();
//                    products = dao.getCompanyProducts(companies, Boolean.TRUE);
//
//                    Collections.sort(products, new Comparator<Product>() {
//                        public int compare(Product one, Product other) {
//                            return one.getName().compareTo(other.getName());
//                        }
//                    });
//
//                    updateProductBean.setParentProductList(products);
//                    FacesUtils.updateHTMLComponnetWIthJS("selectParentProduct");
//                }
//            } else {
//                newProduct.setSubproduct(BigDecimal.ZERO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }

    public void updateProduct() {
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            if (updateProductBean.getImages() == null || updateProductBean.getImages().size() == 0) {
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noImagesInserted"));
                sessionBean.setShowGeneralDialog(Boolean.TRUE);
                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
                return;
            }

            if (updateProductBean.getProductSpecifications() == null || updateProductBean.getProductSpecifications().size() == 0) {
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noSpecificationsInserted"));
                sessionBean.setShowGeneralDialog(Boolean.TRUE);
                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
                return;
            }


            Product product = updateProductBean.getUpdateProduct();
//            product.setActive(BigDecimal.ONE);
//
//
//            List<Company> companies = updateProductBean.getSelectedCompanies();
//            List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);
//            System.out.println("COMPANIES SIZE="+companies.size());
//            for (int i = 0; i < companies.size(); i++) {
//                Company company = companies.get(i);
//                Companyproduct companyproduct = new Companyproduct();
//                companyproduct.setActive(BigDecimal.ONE);
//                companyproduct.setCompany(company);
//                companyproduct.setProduct(product);
//                companyproducts.add(companyproduct);
//            }
//            product.setCompanyproducts(companyproducts);
//
//
//            List<Productline> productlines = updateProductBean.getSelectedProductLines();
//            List<Productlineproduct> productlineproducts = new ArrayList<Productlineproduct>(0);
//            System.out.println("productlines SIZE="+productlines.size());
//            for (int i = 0; i < productlines.size(); i++) {
//                Productline productline = productlines.get(i);
//                Productlineproduct productlineproduct = new Productlineproduct();
//                productlineproduct.setActive(BigDecimal.ONE);
//                productlineproduct.setProduct(product);
//                productlineproduct.setProductline(productline);
//                productlineproducts.add(productlineproduct);
//            }
//            product.setProductlineproducts(productlineproducts);
//
//
//
//            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
//            product.setProductspecifications(productSpecifications);
//           
//            List<Imageproduct> images = updateProductBean.getImages();
//            product.setImageproducts(images);
//
//            List<Videoproduct> videos = updateProductBean.getVideos();
//            product.setVideoproducts(videos);
//            
            FacesUtils.updateHTMLComponnetWIthJS("productPreviewForm");
            FacesUtils.callRequestContext("productPreviewDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
   
    
    
    
    
    
    public String confirmInsertProduct() {

        UserTransaction userTransaction = null;
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Product product = newProductBean.getNewProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.create(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCT")), product, null, null, null, null, null);
            userTransaction.commit();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            return "main?faces-redirect=true";
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String confirmUpdateProduct() {

        UserTransaction userTransaction = null;
        try {
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
            Product product = updateProductBean.getUpdateProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();


            for (int i = 0; i < product.getCompanyproducts().size(); i++) {
                System.out.println("REMOVINH Companyproducts");
                System.out.println("ID="+product.getCompanyproducts().get(i).getId());                
                persistenceHelper.remove(product.getCompanyproducts().get(i));
            }

            for (int i = 0; i < product.getProductlineproducts().size(); i++) {
                System.out.println("REMOVINH Productlineproducts");
                System.out.println("ID="+product.getProductlineproducts().get(i).getId());
                persistenceHelper.remove(product.getProductlineproducts().get(i));
            }
 
//            for (int i = 0; i < product.getImageproducts().size(); i++) {
//                System.out.println("REMOVINH images");
//                System.out.println("ID="+product.getImageproducts().get(i).getImageid());
//                persistenceHelper.remove(product.getImageproducts().get(i));
//            }
//            for (int i = 0; i < product.getVideoproducts().size(); i++) {
//                System.out.println("REMOVINH video");
//                System.out.println("ID="+product.getVideoproducts().get(i).getVideoid());
//                persistenceHelper.remove(product.getVideoproducts().get(i));
//            }
            
            
            
            
            product.setProductspecifications(null);
            product.setImageproducts(null);
            product.setVideoproducts(null);
            product.setActive(BigDecimal.ONE);
            product.setCompanyproducts(null);
            product.setProductlineproducts(null);
            System.out.println("NAME="+product.getName());
            product = persistenceHelper.editPersist(product);
            System.out.println("NAME="+product.getName());
            
            
            List<Company> companies = updateProductBean.getSelectedCompanies();
            List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);
            for (int i = 0; i < companies.size(); i++) {
                System.out.println("ADDING COMPANY="+companies.get(i));
                Company company = companies.get(i);
                Companyproduct companyproduct = new Companyproduct();
                companyproduct.setActive(BigDecimal.ONE);
                companyproduct.setCompany(company);
                companyproduct.setProduct(product);
                companyproducts.add(companyproduct);
            }
            product.setCompanyproducts(companyproducts);

            List<Productline> productlines = updateProductBean.getSelectedProductLines();
            List<Productlineproduct> productlineproducts = new ArrayList<Productlineproduct>(0);
            for (int i = 0; i < productlines.size(); i++) {
                Productline productline = productlines.get(i);
                Productlineproduct productlineproduct = new Productlineproduct();
                productlineproduct.setActive(BigDecimal.ONE);
                productlineproduct.setProduct(product);
                productlineproduct.setProductline(productline);
                productlineproducts.add(productlineproduct);
            }
            product.setProductlineproducts(productlineproducts);

            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
            product.setProductspecifications(productSpecifications);

            List<Imageproduct> images = updateProductBean.getImages();
            product.setImageproducts(images);

            List<Videoproduct> videos = updateProductBean.getVideos();
            product.setVideoproducts(videos);
            System.out.println("NAME="+product.getName());
            product = persistenceHelper.editPersist(product);
            System.out.println("NAME="+product.getName());
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), product, null, null, null, null, null);
            //persistenceHelper.getEntityManager().refresh(product);
            userTransaction.commit();
            
            

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            return "main?faces-redirect=true";
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void searchProductAction() {
        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            ProductDAO dao = new ProductDAO();
            List<Product> products = dao.searchProducts(productSearchBean.getSearchByName(), productSearchBean.getSearchByCompany(), productSearchBean.getSearchByProductline(), productSearchBean.getSearchByCatalogue(), productSearchBean.getSearchByProductcategory());
            productSearchBean.setProducts(products);
            productSearchBean.setProductsModel(new ListDataModel<Product>(products));
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void showProductDetails() {
        try {

            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            System.out.println(productSearchBean.getSelectedProduct());
            FacesUtils.updateHTMLComponnetWIthJS("productPreviewForm");
            FacesUtils.callRequestContext("productPreviewDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void resetSearchProduct() {
        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            productSearchBean.reset();

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String editProduct(Product product) {
        try {
            sessionBean.setParameter(product);
            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");

            updateProductBean.setProductID(product.getProductid());
            sessionBean.setParameter(product);

            return "updateProduct?faces-redirect=true&includeViewParams=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void removeProduct() {
        UserTransaction userTransaction = null;
        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            System.out.println("PRODUCT=" + productSearchBean.getSelectedProduct());
            System.out.println(productSearchBean.getSearchByName());

            Product product = productSearchBean.getSelectedProduct();

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product.setActive(BigDecimal.ZERO);
            product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETEPRODUCT")), product, null, null, null, null, null);
            userTransaction.commit();
            searchProductAction();
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public List<Product> completeNameProduct(String name) {
        try {
            if (name != null && !name.trim().isEmpty() && name.trim().length() >= 1) {
                name = name.trim();
                ProductDAO productDAO = new ProductDAO();
                List<Product> products;
                if (sessionBean.getUsers().getRole().getRoleid().equals(BigDecimal.ONE)) {
                    products = productDAO.fetchProductAutoCompleteName(name, null);
                } else {
                    products = productDAO.fetchProductAutoCompleteName(name, sessionBean.getUsers().getCompany());
                }
                return products;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return null;
        }
    }

    public void autocompleteNameSelectProduct(SelectEvent event) {
        try {
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            Product product = priceBean.getSelectedProductByName();
            priceBean.setSelectedProduct(product);

            if (product != null) {
                List<Price> prices = (new PriceDAO()).findByProperty("product", product);
                List<Company> companies = product.getOrderedCompanies();
                System.out.println("COMPANIES SIZE=" + companies.size());
//                Collections.sort(prices, new Comparator<Price>() {
//                    public int compare(Price one, Price other) {
//                        return one.getPricedate().compareTo(other.getPricedate());
//                    }
//                });
                priceBean.setCompanies(companies);
                priceBean.setPrices(prices);
            }

            priceBean.setSelectedProductByName(null);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void handleCompanySelectCatalogue() {
        try {
            System.out.println("HANDLING CHANGE COMPAMNY !!!!");
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");

            Company company = priceBean.getSelectedCompany();
            if (company != null) {
                List<Catalogue> catalogues = company.getCatalogues();
                Collections.sort(catalogues, new Comparator<Catalogue>() {
                    public int compare(Catalogue one, Catalogue other) {
                        return one.getName().compareTo(other.getName());
                    }
                });
                priceBean.setCatalogues(catalogues);
            } else {
                priceBean.setCatalogues(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void insertPrice() {
        UserTransaction userTransaction = null;
        try {
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            Price newPrice = new Price();
            newPrice.setAmount(priceBean.getAmount());
            newPrice.setInitialprice(priceBean.getInitialAmount());
            newPrice.setDiscount(priceBean.getDiscount());
            newPrice.setCompany(priceBean.getSelectedCompany());
            newPrice.setCatalogue(priceBean.getSelectedCatalogue());
            newPrice.setActive(BigDecimal.ONE);
            newPrice.setProduct(priceBean.getSelectedProduct());
            newPrice.setPricedate(priceBean.getPriceDate());
            newPrice.setCurrency(priceBean.getCurrency());
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.create(newPrice);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCTPRICE")), priceBean.getSelectedProduct(),
                    null, null, null, null, null);
            userTransaction.commit();
            List<Price> prices = (new PriceDAO()).findByProperty("product", priceBean.getSelectedProduct());            
            priceBean.setPrices(prices);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("msgInsertPrice"));

        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String resetPrice() {
        try {
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            priceBean.reset();
            return "priceProduct?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void removePrice() {
        UserTransaction userTransaction = null;
        try {
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            Price price = priceBean.getPrice();
            System.out.println("PRICE=" + price);

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.remove(price);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCTPRICE")), priceBean.getSelectedProduct(),
                    null, null, null, null, null);
            userTransaction.commit();
            List<Price> prices = (new PriceDAO()).findByProperty("product", priceBean.getSelectedProduct());
            System.out.println("Prices size="+prices.size());
            priceBean.setPrices(prices);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("msgDeletePrice"));

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void updateFinalPrice() {
        try {
            PriceBean priceBean = (PriceBean) FacesUtils.getManagedBean("priceBean");
            if (priceBean.getInitialAmount() != null && priceBean.getInitialAmount() != null) {
                Double finalPrice = priceBean.getInitialAmount() * (100 - priceBean.getDiscount()) / 100;
                priceBean.setAmount(finalPrice);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            //sessionBean.setErrorMsgKey("errMsg_GeneralError");
            //goError(e);
        }
    }
    
     public void updateFinalPriceCreateProduct() {
        try {
            NewProductBean priceBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            if (priceBean.getInitialAmount() != null && priceBean.getInitialAmount() != null) {
                Double finalPrice = priceBean.getInitialAmount() * (100 - priceBean.getDiscount()) / 100;
                priceBean.setAmount(finalPrice);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            //sessionBean.setErrorMsgKey("errMsg_GeneralError");
            //goError(e);
        }
    }

    public Boolean checkProductInCompany(Product product) {
        try {
            ProductDAO dao = new ProductDAO();
            if (sessionBean.getUsers().getRole().getRoleid().equals(BigDecimal.ONE)) {
                return true;
            } else {
                return dao.checkProductInCompany(sessionBean.getUsers().getCompany(), product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                          TEMPLATES!!!!!!!!!!!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void test() {

        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            System.out.println(productSearchBean.getSelectedProduct());
            Product product = productSearchBean.getSelectedProduct();
            productSearchBean.setSelectedProduct(product);
            FacesUtils.updateHTMLComponnetWIthJS("confDeleteProductDlgID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void templateNoReturnAction() {

        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String templateReturnAction() {

        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");


            return "main?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String templateUpdateDBAction() {

        UserTransaction userTransaction = null;
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();


            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCT")), null, null, null, null, null, null);
            userTransaction.commit();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            return "main?faces-redirect=true";
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void goError(Exception ex) {
        try {
            logger.error("-----------AN ERROR HAPPENED !!!! -------------------- : " + ex.toString());
            if (sessionBean.getUsers() != null) {
                logger.error("User=" + sessionBean.getUsers().getUsername());
            }
            logger.error("Cause=" + ex.getCause());
            logger.error("Class=" + ex.getClass());
            logger.error("Message=" + ex.getLocalizedMessage());
            logger.error(ex, ex);
            logger.error("--------------------- END OF ERROR --------------------------------------------------------\n\n");

            ErrorBean errorBean = (ErrorBean) FacesUtils.getManagedBean("errorBean");
            errorBean.reset();
            errorBean.setErrorMSG(MessageBundleLoader.getMessage(sessionBean.getErrorMsgKey()));
            //FacesUtils.redirectAJAX("./templates/error.jsf?faces-redirect=true");
            FacesUtils.redirectAJAX(FacesUtils.getContextPath() + "/common/error.jsf?faces-redirect=true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
