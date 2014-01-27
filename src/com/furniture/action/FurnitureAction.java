/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.action;

import com.furniture.bean.ApplicationBean;
import com.furniture.bean.ErrorBean;
import com.furniture.bean.NewProductBean;
import com.furniture.bean.PriceBean;
import com.furniture.bean.ProductSearchBean;
import com.furniture.bean.SessionBean;
import com.furniture.bean.UpdateProductBean;
import com.furniture.bean.ViewProductBean;
import com.furniture.dao.CompanyDAO;
import com.furniture.dao.CompanyproductDAO;
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

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
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

    public String viewProduct(Product product) {
        try {
            sessionBean.setParameter(product);
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            viewProductBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_VIEW_PRODUCT"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("viewProduct"));

            sessionBean.setPageName(product.getName());

            return "viewProduct?faces-redirect=true ";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void showProduct(BigDecimal productID) {
        try {
            
            //Product product = (Product)persistenceHelper.find(Product.class, productID);            
            //sessionBean.setParameter(product);
            Map<String,Object> options = new HashMap<String, Object>();  
            options.put("modal", true);  
            options.put("draggable", false);  
            options.put("resizable", false);  
            options.put("contentHeight", 500);  
            options.put("contentWidth", 950); 
            //options.put("productid", productID);
            
            
            Map<String,List<String>> params = new HashMap<String, List<String>>(); 
            List<String> data = new ArrayList<String>(0);
            data.add(productID.toString());
            params.put("productid", data);
            
            RequestContext.getCurrentInstance().openDialog("viewOnlyProduct.jsf?productid="+productID, options, params);

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);            
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
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Company company = newProductBean.getSelectedCompany();
            if (company != null) {
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

                newProductBean.setSelectedNode(null);
                newProductBean.setProducts(new DualListModel<Product>(products, new ArrayList<Product>()));
                newProductBean.setSubProducts(new DualListModel<Product>(products, new ArrayList<Product>()));

                ApplicationBean appBean = (ApplicationBean) FacesUtils.getManagedBean("applicationBean");
                List<Company> companies = new ArrayList<Company>(0);
                for (int i = 0; i < appBean.getCompanies().size(); i++) {
                    Company comp = appBean.getCompanies().get(i);
                    if (!comp.equals(company)) {
                        companies.add(comp);
                    }
                }
                newProductBean.setScopeCompanies(new DualListModel<Company>(companies, new ArrayList<Company>()));
                //FacesUtils.updateHTMLComponnetWIthJS("@form:showTree");

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

            if (newProductBean.getNewProduct().getItem() != null) {
                ItemspecificationDAO d = new ItemspecificationDAO();
                specifications.addAll(d.fetchItemSpecifications(newProductBean.getNewProduct().getItem(), true, true));
            }

            for (int i = 0; i < specs.size(); i++) {
                Specificationcategory specificationcategory = (Specificationcategory) specs.get(i);
                specifications.addAll(dao.findByProperty("specificationcategory", specificationcategory));
            }


            List<Specification> tempList = new ArrayList<Specification>(specifications);
            Collections.sort(tempList, new Comparator<Specification>() {
                public int compare(Specification one, Specification other) {
                    return one.getOrdered().compareTo(other.getOrdered());
                }
            });

            newProductBean.setSpecifications(new ArrayList<Specification>(tempList));
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
    
    public void selectUpdateSpecification(Productspecification productSpecification) {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            newProductBean.setProductSpecification(productSpecification);
            newProductBean.setSpecification(productSpecification.getSpecification());
            Specification spec = newProductBean.getSpecification();
            
            SpecificationvalueDAO dao = new SpecificationvalueDAO();
            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);

            if (productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE)) {
                newProductBean.setSvalue(productSpecification.getProductvalues().get(0).getValue());
                
                if (productSpecification.getSpecification().getDimension().equals(BigDecimal.ONE))
                    newProductBean.setSelectedMeasurment(productSpecification.getProductvalues().get(0).getMeasurment());
                
            } else if(!productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE) && !productSpecification.getSpecification().getMultiplevalues().equals(BigDecimal.ONE)) {               
                SpecificationvalueDAO svdao = new SpecificationvalueDAO();
                Specificationvalue specificationvalue = svdao.getSpecificationValue(productSpecification.getSpecification(), productSpecification.getProductvalues().get(0).getSvalue());                
                newProductBean.setSelectedSvalue(specificationvalue);
            } else if(!productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE) && productSpecification.getSpecification().getMultiplevalues().equals(BigDecimal.ONE)) {
                List<Specificationvalue> specificationValues = new ArrayList<Specificationvalue>(0);
                for (int i = 0; i < productSpecification.getProductvalues().size(); i++) {
                    Productvalue productvalue = productSpecification.getProductvalues().get(i);
                    SpecificationvalueDAO svdao = new SpecificationvalueDAO();
                    Specificationvalue specificationvalue = svdao.getSpecificationValue(productSpecification.getSpecification(), productvalue.getSvalue());                    
                    specificationValues.add(specificationvalue);                    
                }
                newProductBean.setSelectedSvalues(specificationValues);
            }
            
            newProductBean.setSvalues(svalues);
            FacesUtils.callRequestContext("selectUpdateValueDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    
    
     public void selectUpdateSpecificationView(Productspecification productSpecification) {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            viewProductBean.setProductSpecification(productSpecification);
            viewProductBean.setSpecification(productSpecification.getSpecification());
            Specification spec = viewProductBean.getSpecification();            
            SpecificationvalueDAO dao = new SpecificationvalueDAO();
            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);

            if (productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE)) {
                viewProductBean.setSvalue(productSpecification.getProductvalues().get(0).getValue());
                if (productSpecification.getSpecification().getDimension().equals(BigDecimal.ONE))
                    viewProductBean.setSelectedMeasurment(productSpecification.getProductvalues().get(0).getMeasurment());
                
            } else if(!productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE) && !productSpecification.getSpecification().getMultiplevalues().equals(BigDecimal.ONE)) {               
                SpecificationvalueDAO svdao = new SpecificationvalueDAO();
                Specificationvalue specificationvalue = svdao.getSpecificationValue(productSpecification.getSpecification(), productSpecification.getProductvalues().get(0).getSvalue());                
                viewProductBean.setSelectedSvalue(specificationvalue);
            } else if(!productSpecification.getSpecification().getFreetext().equals(BigDecimal.ONE) && productSpecification.getSpecification().getMultiplevalues().equals(BigDecimal.ONE)) {
                List<Specificationvalue> specificationValues = new ArrayList<Specificationvalue>(0);
                for (int i = 0; i < productSpecification.getProductvalues().size(); i++) {
                    Productvalue productvalue = productSpecification.getProductvalues().get(i);
                    SpecificationvalueDAO svdao = new SpecificationvalueDAO();
                    Specificationvalue specificationvalue = svdao.getSpecificationValue(productSpecification.getSpecification(), productvalue.getSvalue());                    
                    specificationValues.add(specificationvalue);                    
                }
                viewProductBean.setSelectedSvalues(specificationValues);
            }
            
            viewProductBean.setSvalues(svalues);
            FacesUtils.callRequestContext("selectUpdateValueDialog.show();");

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
            
            for (int i = 0; i < productSpecifications.size(); i++) {
                Productspecification productspecification = productSpecifications.get(i);
                if (productspecification.getSpecification().equals(spec) && !spec.getMultipleinsert().equals(BigDecimal.ONE)) {                    
                     sessionBean.setAlertMessage(MessageBundleLoader.getMessage("specAlreadySelected"));
                     FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                     FacesUtils.callRequestContext("generalAlertWidget.show()");                    
                    return;
                }                
            }
            
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setProduct(newProduct);
            productSpecification.setActive(BigDecimal.ONE);
            productSpecification.setOrdered(spec.getOrdered());
            productSpecifications.add(productSpecification);

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
                
                if (spec.getDimension().equals(BigDecimal.ONE))
                   productvalue.setMeasurment(newProductBean.getSelectedMeasurment());
                
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

//            if (spec.getFreetext().equals(BigDecimal.ONE) && spec.getColor().equals(BigDecimal.ONE)) {
//                String svalue = newProductBean.getSvalue();
//                Productvalue productvalue = new Productvalue();
//                productvalue.setProductspecification(productSpecification);
//                productvalue.setHexcolor(svalue);
//                productValues.add(productvalue);
//                productvalue.setActive(BigDecimal.ONE);
//                productSpecification.getProductvalues().add(productvalue);
//            }



            Collections.sort(productSpecifications, new Comparator<Productspecification>() {
                public int compare(Productspecification one, Productspecification other) {
                    if (one.getOrdered() != null && other.getOrdered() != null) {
                        return one.getOrdered().compareTo(other.getOrdered());
                    } else if (one.getSpecification().getOrdered() != null && other.getSpecification().getOrdered() != null) {
                        return one.getSpecification().getOrdered().compareTo(other.getSpecification().getOrdered());
                    } else {
                        return one.getSpecification().getName().compareTo(other.getSpecification().getName());
                    }
                }
            });
            newProductBean.setProductSpecifications(productSpecifications);
            newProductBean.setProductValues(productValues);

            FacesUtils.callRequestContext("selectValueDialog.hide();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    
    
    public void selectUpdateSValues() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Product newProduct = newProductBean.getNewProduct();
            Specification spec = newProductBean.getSpecification();
            Productspecification productSpecification = newProductBean.getProductSpecification();
            
//            List<Productspecification> productSpecifications = newProductBean.getProductSpecifications();
//            Productspecification productSpecification = new Productspecification();
//            productSpecification.setSpecification(spec);
//            productSpecification.setProduct(newProduct);
//            productSpecification.setActive(BigDecimal.ONE);
//            productSpecification.setOrdered(spec.getOrdered());
//            productSpecifications.add(productSpecification);

            List<Productvalue> productValues = newProductBean.getProductValues();

            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));
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
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));
                Specificationvalue svalue = newProductBean.getSelectedSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setSvalue(svalue.getSvalue());
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

            if (spec.getFreetext().equals(BigDecimal.ONE)) {
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));
                String svalue = newProductBean.getSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setValue(svalue);
                
                if (spec.getDimension().equals(BigDecimal.ONE))
                    productvalue.setMeasurment(newProductBean.getSelectedMeasurment());
                
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

//            Collections.sort(productSpecifications, new Comparator<Productspecification>() {
//                public int compare(Productspecification one, Productspecification other) {
//                    if (one.getOrdered() != null && other.getOrdered() != null) {
//                        return one.getOrdered().compareTo(other.getOrdered());
//                    } else if (one.getSpecification().getOrdered() != null && other.getSpecification().getOrdered() != null) {
//                        return one.getSpecification().getOrdered().compareTo(other.getSpecification().getOrdered());
//                    } else {
//                        return one.getSpecification().getName().compareTo(other.getSpecification().getName());
//                    }
//                }
//            });
//            newProductBean.setProductSpecifications(productSpecifications);
            newProductBean.setProductValues(productValues);

            FacesUtils.callRequestContext("selectUpdateValueDialog.hide();");

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

            for (int i = 0; i < dimensionProductSpecifications.size(); i++) {
                Productspecification productspecification = dimensionProductSpecifications.get(i);
                if (productspecification.getSpecification().equals(spec) && !spec.getMultipleinsert().equals(BigDecimal.ONE)) {                    
                     sessionBean.setAlertMessage(MessageBundleLoader.getMessage("specAlreadySelected"));
                     FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                     FacesUtils.callRequestContext("generalAlertWidget.show()");                    
                    return;
                }                
            }
            
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setProduct(newProduct);
            productSpecification.setOrdered(new BigDecimal(1000));
            productSpecification.setActive(BigDecimal.ONE);
            productSpecification.setOrdered(spec.getOrdered());


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

            Collections.sort(dimensionProductSpecifications, new Comparator<Productspecification>() {
                public int compare(Productspecification one, Productspecification other) {
                    try {
                        return one.getOrdered().compareTo(other.getOrdered());
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });

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
//             for(Object item : event.getItems()) {  
//            }    

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void insertProduct() {
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
//            if (newProductBean.getImages() == null || newProductBean.getImages().size() == 0) {                
//                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noImagesInserted"));                                
//                FacesUtils.callRequestContext("alertMessageDlg.show()");        
//                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
//                return;
//            }

//            if (newProductBean.getProductSpecifications() == null || newProductBean.getProductSpecifications().size() == 0) {
//                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noSpecificationsInserted"));                                
//                FacesUtils.callRequestContext("alertMessageDlg.show()");        
//                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");//            }

            if (newProductBean.getSelectedNode() == null) {
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noCategorySelected"));
//                FacesUtils.callRequestContext("alertMessageDlg.show()"); 
//                FacesUtils.updateHTMLComponnetWIthJS(":alertMsgForm:primePanel");
//                FacesUtils.callRequestContext("alertMessageDialog.show()");
//                FacesUtils.updateHTMLComponnetWIthJS("@alertMsgForm:primeAlertPanel");
                FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                //FacesUtils.updateHTMLComponnetWIthJS("@widgetVar(alertPanelWidget)");
                FacesUtils.callRequestContext("generalAlertWidget.show()");  
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


            List<Productspecification> productSpecifications = new ArrayList<Productspecification>(0);

            if (newProductBean.getProductSpecifications() != null) {
                productSpecifications.addAll(newProductBean.getProductSpecifications());
            }

            if (newProductBean.getDimesionProductSpecifications() != null) {
                productSpecifications.addAll(newProductBean.getDimesionProductSpecifications());
            }


            product.setProductspecifications(productSpecifications);

            List<Imageproduct> images = newProductBean.getImages();
            product.setImageproducts(images);

            List<Videoproduct> videos = newProductBean.getVideos();
            product.setVideoproducts(videos);

            if (newProductBean.getSubProducts().getTarget() != null && newProductBean.getSubProducts().getTarget().size() > 0) {
                product.setSubproducts(newProductBean.getSubProducts().getTarget());
            }

            if (newProductBean.getProducts().getTarget() != null && newProductBean.getProducts().getTarget().size() > 0) {
                product.setParentproducts(newProductBean.getProducts().getTarget());
            }

            if (newProductBean.getScopeCompanies().getTarget() != null && newProductBean.getScopeCompanies().getTarget().size() > 0) {
                product.setScopeCompanies(newProductBean.getScopeCompanies().getTarget());
            }



            if (newProductBean.getSelectedNode() != null && newProductBean.getSelectedNode().getData() != null) {
                List<Category> categ0ries = new ArrayList<Category>(0);
                categ0ries.add((Category) newProductBean.getSelectedNode().getData());
                product.setCategories(categ0ries);
            }


            FacesUtils.updateHTMLComponnetWIthJS("productPreviewForm");
            FacesUtils.callRequestContext("productPreviewDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

//    public void handleCompanySelectUpdate() {
//        UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//
//        Set<Productline> productLines = new HashSet<Productline>(0);
//
//        for (int i = 0; i < updateProductBean.getSelectedCompanies().size(); i++) {
//            Company company = updateProductBean.getSelectedCompanies().get(i);
//            productLines.addAll(FurnitureUtil.getProductLineFromCompany(company));
//
//        }
//
//        List<Productline> pl = new ArrayList<Productline>(productLines);
//        Collections.sort(pl, new Comparator<Productline>() {
//            public int compare(Productline one, Productline other) {
//                return one.getName().compareTo(other.getName());
//            }
//        });
//        updateProductBean.setProductLines(pl);
//
//    }

    public void onNodeSelect(NodeSelectEvent event) {
    }

//    public void handleFileUploadUpdate(FileUploadEvent event) {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            UploadedFile file = event.getFile();
//            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");
//
//            Imageproduct newImage = new Imageproduct();
//            newImage.setActive(BigDecimal.ONE);
//            newImage.setFilename(file.getFileName());
//            newImage.setProduct(updateProductBean.getUpdateProduct());
//            newImage.setPath(path + "\\" + file.getFileName());
//            updateProductBean.setNewImage(newImage);
//
//            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void handleFileUploadVideoUpdate(FileUploadEvent event) {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            UploadedFile file = event.getFile();
//            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");
//
//            Videoproduct newVideo = new Videoproduct();
//            newVideo.setActive(BigDecimal.ONE);
//            newVideo.setFilename(file.getFileName());
//            newVideo.setProduct(updateProductBean.getUpdateProduct());
//            newVideo.setPath(path + "\\" + file.getFileName());
//            updateProductBean.setNewVideo(newVideo);
//
//            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void savePhotoUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            List<Imageproduct> images = updateProductBean.getImages();
//            images.add(updateProductBean.getNewImage());
//            updateProductBean.setNewImage(new Imageproduct());
//            FacesUtils.callRequestContext("dialogFotos.hide();");
//            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newPhotoUploaded"), null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void saveVideoUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            List<Videoproduct> videos = updateProductBean.getVideos();
//            videos.add(updateProductBean.getNewVideo());
//            updateProductBean.setNewVideo(new Videoproduct());
//            FacesUtils.callRequestContext("dialogVideos.hide();");
//            FacesUtils.addGrowlMessage(MessageBundleLoader.getMessage("newVideoUploaded"), null);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void openSelectImageDlgUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            updateProductBean.setNewImage(new Imageproduct());
//            FacesUtils.callRequestContext("dialogFotos.show();");
//            FacesUtils.updateHTMLComponnetWIthJS(":imgData");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void openSelectVideoDlgUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            updateProductBean.setNewVideo(new Videoproduct());
//            FacesUtils.callRequestContext("dialogVideos.show();");
//            FacesUtils.updateHTMLComponnetWIthJS(":videoData");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
//        }
//    }
//
//    public void showSpecificationsUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            List<Specificationcategory> specs = updateProductBean.getSelectedSpecificationCat();
//            SpecificationDAO dao = new SpecificationDAO();
//            Set<Specification> specifications = new HashSet<Specification>(0);
//            for (int i = 0; i < specs.size(); i++) {
//                Specificationcategory specificationcategory = (Specificationcategory) specs.get(i);
//                specifications.addAll(dao.findByProperty("specificationcategory", specificationcategory));
//            }
//
//            if (updateProductBean.getUpdateProduct().getItem() != null) {
//                ItemspecificationDAO d = new ItemspecificationDAO();
//                specifications.addAll(d.fetchItemSpecifications(updateProductBean.getUpdateProduct().getItem(), true, false));
//            }
//
//
//            updateProductBean.setSpecifications(new ArrayList<Specification>(specifications));
//            FacesUtils.callRequestContext("selectSpecDialog.show();");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }
//
//    public void selectSpecificationUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            Specification spec = updateProductBean.getSpecification();
//            SpecificationvalueDAO dao = new SpecificationvalueDAO();
//            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);
//
//            updateProductBean.setSvalue(null);
//            updateProductBean.setSelectedSvalues(null);
//            updateProductBean.setSelectedSvalue(null);
//
//            updateProductBean.setSvalues(svalues);
//            FacesUtils.callRequestContext("selectValueDialog.show();");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }
//
//    public void selectSValuesUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            Product updateProduct = updateProductBean.getUpdateProduct();
//            Specification spec = updateProductBean.getSpecification();
//
//            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
//            Productspecification productSpecification = new Productspecification();
//            productSpecification.setSpecification(spec);
//            productSpecification.setProduct(updateProduct);
//            productSpecifications.add(productSpecification);
//            productSpecification.setActive(BigDecimal.ONE);
//
//            List<Productvalue> productValues = updateProductBean.getProductValues();
//
//            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {
//                List<Specificationvalue> svalues = updateProductBean.getSelectedSvalues();
//                for (int i = 0; i < svalues.size(); i++) {
//                    Productvalue productvalue = new Productvalue();
//                    productvalue.setProductspecification(productSpecification);
//                    Specificationvalue specificationvalue = svalues.get(i);
//                    productvalue.setSvalue(specificationvalue.getSvalue());
//                    productValues.add(productvalue);
//                    productvalue.setActive(BigDecimal.ONE);
//                    productSpecification.getProductvalues().add(productvalue);
//                }
//
//            }
//
//
//            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ZERO)) {
//                Specificationvalue svalue = updateProductBean.getSelectedSvalue();
//                Productvalue productvalue = new Productvalue();
//                productvalue.setProductspecification(productSpecification);
//                productvalue.setSvalue(svalue.getSvalue());
//                productValues.add(productvalue);
//                productvalue.setActive(BigDecimal.ONE);
//                productSpecification.getProductvalues().add(productvalue);
//            }
//
//            if (spec.getFreetext().equals(BigDecimal.ONE)) {
//                String svalue = updateProductBean.getSvalue();
//                Productvalue productvalue = new Productvalue();
//                productvalue.setProductspecification(productSpecification);
//                productvalue.setValue(svalue);
//                productValues.add(productvalue);
//                productvalue.setActive(BigDecimal.ONE);
//                productSpecification.getProductvalues().add(productvalue);
//            }
//
//
//            updateProductBean.setProductSpecifications(productSpecifications);
//            updateProductBean.setProductValues(productValues);
//
//            FacesUtils.callRequestContext("selectValueDialog.hide();");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }
//
//    public void deleteProductSpecificationUpdate() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
//            Productspecification productSpecification = updateProductBean.getProductSpecification();
//            productSpecifications.remove(productSpecification);
//            updateProductBean.setProductSpecifications(productSpecifications);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }


//    public void updateProduct() {
//        try {
//            UpdateProductBean updateProductBean = (UpdateProductBean) FacesUtils.getManagedBean("updateProductBean");
//            if (updateProductBean.getImages() == null || updateProductBean.getImages().size() == 0) {
//                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noImagesInserted"));
//                sessionBean.setShowGeneralDialog(Boolean.TRUE);
//                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
//                return;
//            }
//
//            if (updateProductBean.getProductSpecifications() == null || updateProductBean.getProductSpecifications().size() == 0) {
//                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("noSpecificationsInserted"));
//                sessionBean.setShowGeneralDialog(Boolean.TRUE);
//                FacesUtils.updateHTMLComponnetWIthJS("primeAlertPanel");
//                return;
//            }
//
//
//            Product product = updateProductBean.getUpdateProduct();
////            product.setActive(BigDecimal.ONE);
////
////
////            List<Company> companies = updateProductBean.getSelectedCompanies();
////            List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);
////            System.out.println("COMPANIES SIZE="+companies.size());
////            for (int i = 0; i < companies.size(); i++) {
////                Company company = companies.get(i);
////                Companyproduct companyproduct = new Companyproduct();
////                companyproduct.setActive(BigDecimal.ONE);
////                companyproduct.setCompany(company);
////                companyproduct.setProduct(product);
////                companyproducts.add(companyproduct);
////            }
////            product.setCompanyproducts(companyproducts);
////
////
////            List<Productline> productlines = updateProductBean.getSelectedProductLines();
////            List<Productlineproduct> productlineproducts = new ArrayList<Productlineproduct>(0);
////            System.out.println("productlines SIZE="+productlines.size());
////            for (int i = 0; i < productlines.size(); i++) {
////                Productline productline = productlines.get(i);
////                Productlineproduct productlineproduct = new Productlineproduct();
////                productlineproduct.setActive(BigDecimal.ONE);
////                productlineproduct.setProduct(product);
////                productlineproduct.setProductline(productline);
////                productlineproducts.add(productlineproduct);
////            }
////            product.setProductlineproducts(productlineproducts);
////
////
////
////            List<Productspecification> productSpecifications = updateProductBean.getProductSpecifications();
////            product.setProductspecifications(productSpecifications);
////           
////            List<Imageproduct> images = updateProductBean.getImages();
////            product.setImageproducts(images);
////
////            List<Videoproduct> videos = updateProductBean.getVideos();
////            product.setVideoproducts(videos);
////            
//            FacesUtils.updateHTMLComponnetWIthJS("productPreviewForm");
//            FacesUtils.callRequestContext("productPreviewDialog.show();");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            sessionBean.setErrorMsgKey("errMsg_GeneralError");
//            goError(e);
//        }
//    }

    public String confirmInsertProduct() {

        UserTransaction userTransaction = null;
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            Product product = newProductBean.getNewProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.create(product);
            if (newProductBean.getPrices() != null && newProductBean.getPrices().size() > 0) {
                persistenceHelper.create(newProductBean.getPrices().get(0));
            }


            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCT")), "Product " + product.getName() + " inserted");
            userTransaction.commit();

            if (product.getImageproducts() != null) {
                for (int i = 0; i < product.getImageproducts().size(); i++) {
                    Imageproduct image = product.getImageproducts().get(i);
                    String tempPath = image.getPath();
                    String finalPath = SystemParameters.getInstance().getProperty("PATH_WEB_PRODUCTS") + "\\" + product.getProductid() + "\\images\\" + image.getFilename();
                    IOUtils.saveBinaryFile(finalPath, new FileInputStream(tempPath));
                    IOUtils.deleteFile(tempPath);
                }
            }

            if (product.getVideoproducts() != null) {
                for (int i = 0; i < product.getVideoproducts().size(); i++) {
                    Videoproduct video = product.getVideoproducts().get(i);
                    String tempPath = video.getPath();
                    String finalPath = SystemParameters.getInstance().getProperty("PATH_WEB_PRODUCTS") + "\\" + product.getProductid() + "\\videos\\" + video.getFilename();
                    IOUtils.saveBinaryFile(finalPath, new FileInputStream(tempPath));
                    IOUtils.deleteFile(tempPath);
                }
            }


            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productInserted"));
            
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            //return "main?faces-redirect=true";
            return createProduct();
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
                persistenceHelper.remove(product.getCompanyproducts().get(i));
            }

            for (int i = 0; i < product.getProductlineproducts().size(); i++) {
                persistenceHelper.remove(product.getProductlineproducts().get(i));
            }

//            for (int i = 0; i < product.getImageproducts().size(); i++) {
//                persistenceHelper.remove(product.getImageproducts().get(i));
//            }
//            for (int i = 0; i < product.getVideoproducts().size(); i++) {
//                persistenceHelper.remove(product.getVideoproducts().get(i));
//            }




            product.setProductspecifications(null);
            product.setImageproducts(null);
            product.setVideoproducts(null);
            product.setActive(BigDecimal.ONE);
            product.setCompanyproducts(null);
            product.setProductlineproducts(null);
            product = persistenceHelper.editPersist(product);


            List<Company> companies = updateProductBean.getSelectedCompanies();
            List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);
            for (int i = 0; i < companies.size(); i++) {
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
            product = persistenceHelper.editPersist(product);
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

    public void updateProductName() {

        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            if (viewProductBean.getSelectedNode() != null && !viewProductBean.getSelectedNode().getData().equals(product.getFirstCategory())) {
                product.setCategories(null);
                product = persistenceHelper.editPersist(product);
                Category cat = (Category) viewProductBean.getSelectedNode().getData();
                List<Category> cats = new ArrayList<Category>(0);
                cats.add(cat);
                product.setCategories(cats);
            }


            product = persistenceHelper.editPersist(product);            
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + product.getName() + " updated");
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            viewProductBean.setProduct(product);
            FacesUtils.callRequestContext("nameDialogWidget.hide()");

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

    public void updateProductDescription() {

        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), product, null, null, null, null, null);
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            viewProductBean.setProduct(product);
            FacesUtils.callRequestContext("descriptionDialogWidget.hide()");

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

    public void onNodeSelec(NodeSelectEvent event) {
        ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
        viewProductBean.setSelectedNode(event.getTreeNode());
    }

    public void updateProductdescription() {

        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product = persistenceHelper.editPersist(product);
                        persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + product.getName() + " updated");

            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));

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

    public void showSpecificationsView() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Specificationcategory> specs = viewProductBean.getSelectedSpecificationCat();
            SpecificationDAO dao = new SpecificationDAO();
            Set<Specification> specifications = new HashSet<Specification>(0);

            ItemspecificationDAO d = new ItemspecificationDAO();
            specifications.addAll(d.fetchItemSpecifications(viewProductBean.getProduct().getItem(), true, true));

            for (int i = 0; i < specs.size(); i++) {
                Specificationcategory specificationcategory = (Specificationcategory) specs.get(i);
                specifications.addAll(dao.findByProperty("specificationcategory", specificationcategory));
            }


            List<Specification> tempSpecs = new ArrayList<Specification>(specifications);

            Collections.sort(tempSpecs, new Comparator<Specification>() {
                public int compare(Specification one, Specification other) {
                    try {
                        return one.getOrdered().compareTo(other.getOrdered());
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });


            viewProductBean.setSpecifications(tempSpecs);
            FacesUtils.callRequestContext("selectSpecDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void deleteProductSpecificationView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product product = viewProductBean.getProduct();
            List<Productspecification> productSpecifications = viewProductBean.getProductSpecifications();
            Productspecification productSpecification = viewProductBean.getProductSpecification();
            productSpecifications.remove(productSpecification);
            viewProductBean.setProductSpecifications(productSpecifications);
            userTransaction = persistenceHelper.getUserTransaction();

            product.getProductspecifications().remove(productSpecification);
            userTransaction.begin();                        
            persistenceHelper.remove(productSpecification);
            //product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + product.getName() + " updated");
            viewProductBean.setProduct(product);
            userTransaction.commit();

            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
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

    public void selectSpecificationView() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Specification spec = viewProductBean.getSpecification();
            SpecificationvalueDAO dao = new SpecificationvalueDAO();
            List<Specificationvalue> svalues = dao.findByProperty("specification", spec);

            viewProductBean.setSvalue(null);
            viewProductBean.setSelectedSvalues(null);
            viewProductBean.setSelectedSvalue(null);

            viewProductBean.setSvalues(svalues);
            FacesUtils.callRequestContext("selectValueDialog.show();");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void selectSValuesView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product newProduct = viewProductBean.getProduct();
            Specification spec = viewProductBean.getSpecification();
            List<Productspecification> productSpecifications = viewProductBean.getProductSpecifications();
            
            for (int i = 0; i < productSpecifications.size(); i++) {
                Productspecification productspecification = productSpecifications.get(i);
                if (productspecification.getSpecification().equals(spec) && !spec.getMultipleinsert().equals(BigDecimal.ONE)) {                    
                     sessionBean.setAlertMessage(MessageBundleLoader.getMessage("specAlreadySelected"));
                     FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                     FacesUtils.callRequestContext("generalAlertWidget.show()");                    
                    return;
                }                
            }
            
            
            userTransaction = persistenceHelper.getUserTransaction();

            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setOrdered(spec.getOrdered());
            productSpecification.setProduct(newProduct);
            productSpecification.setActive(BigDecimal.ONE);

            List<Productvalue> productValues = viewProductBean.getProductValues();

            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {
                List<Specificationvalue> svalues = viewProductBean.getSelectedSvalues();
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
                Specificationvalue svalue = viewProductBean.getSelectedSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setSvalue(svalue.getSvalue());
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

            if (spec.getFreetext().equals(BigDecimal.ONE)) {
                String svalue = viewProductBean.getSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setValue(svalue);
                
                if (spec.getDimension().equals(BigDecimal.ONE))
                   productvalue.setMeasurment(viewProductBean.getSelectedMeasurment());
                
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

//             if (spec.getFreetext().equals(BigDecimal.ONE) && spec.getColor().equals(BigDecimal.ONE)) {
//                String svalue = viewProductBean.getSvalue();
//                Productvalue productvalue = new Productvalue();
//                productvalue.setProductspecification(productSpecification);
//                productvalue.setHexcolor(svalue);
//                productValues.add(productvalue);
//                productvalue.setActive(BigDecimal.ONE);
//                productSpecification.getProductvalues().add(productvalue);
//            }




            productSpecifications.add(productSpecification);
            Collections.sort(productSpecifications, new Comparator<Productspecification>() {
                public int compare(Productspecification one, Productspecification other) {
                    try {
                        if (one.getOrdered() != null && other.getOrdered() != null) {
                            return one.getOrdered().compareTo(other.getOrdered());
                        } else if (one.getSpecification().getOrdered() != null && other.getSpecification().getOrdered() != null) {
                            return one.getSpecification().getOrdered().compareTo(other.getSpecification().getOrdered());
                        } else {
                            return one.getSpecification().getName().compareTo(other.getSpecification().getName());
                        }
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });
            //productSpecification = persistenceHelper.editPersist(productSpecification);

            //newProduct.getProductspecifications().add(productSpecification);
            userTransaction.begin();
            persistenceHelper.create(productSpecification);
            //newProduct = persistenceHelper.editPersist(newProduct);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + newProduct.getName() + " updated");
            userTransaction.commit();

            viewProductBean.setProductSpecifications(productSpecifications);            
            viewProductBean.setProductValues(productValues);
            viewProductBean.setProduct(newProduct);

            FacesUtils.callRequestContext("selectValueDialog.hide();");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
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
    
    
    public void selectUpdateSValuesView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Product newProduct = viewProductBean.getProduct();
            Specification spec = viewProductBean.getSpecification();

            List<Productspecification> productSpecifications = viewProductBean.getProductSpecifications();
            userTransaction = persistenceHelper.getUserTransaction();
            
            Productspecification productSpecification = viewProductBean.getProductSpecification();            
            productSpecifications.remove(productSpecification);           
            userTransaction.begin();
            for (int i = 0; i < productSpecification.getProductvalues().size(); i++) {
                    Productvalue productvalue = productSpecification.getProductvalues().get(i);                    
                    persistenceHelper.remove(productvalue);                    
            }
            
            
            List<Productvalue> productValues = viewProductBean.getProductValues();

            if (spec.getFreetext().equals(BigDecimal.ZERO) && spec.getMultiplevalues().equals(BigDecimal.ONE)) {                
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));                
                List<Specificationvalue> svalues = viewProductBean.getSelectedSvalues();
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
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));
                Specificationvalue svalue = viewProductBean.getSelectedSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setSvalue(svalue.getSvalue());
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }

            if (spec.getFreetext().equals(BigDecimal.ONE)) {
                productSpecification.setProductvalues(new ArrayList<Productvalue>(0));
                String svalue = viewProductBean.getSvalue();
                Productvalue productvalue = new Productvalue();
                productvalue.setProductspecification(productSpecification);
                productvalue.setValue(svalue);
                
                 if (spec.getDimension().equals(BigDecimal.ONE))
                   productvalue.setMeasurment(viewProductBean.getSelectedMeasurment());
                 
                productValues.add(productvalue);
                productvalue.setActive(BigDecimal.ONE);
                productSpecification.getProductvalues().add(productvalue);
            }



//            productSpecifications.add(productSpecification);
//            Collections.sort(productSpecifications, new Comparator<Productspecification>() {
//                public int compare(Productspecification one, Productspecification other) {
//                    try {
//                        if (one.getOrdered() != null && other.getOrdered() != null) {
//                            return one.getOrdered().compareTo(other.getOrdered());
//                        } else if (one.getSpecification().getOrdered() != null && other.getSpecification().getOrdered() != null) {
//                            return one.getSpecification().getOrdered().compareTo(other.getSpecification().getOrdered());
//                        } else {
//                            return one.getSpecification().getName().compareTo(other.getSpecification().getName());
//                        }
//                    } catch (Exception ex) {
//                        return 0;
//                    }
//                }
//            });
            

            //newProduct.getProductspecifications().add(productSpecification);
            
            
            
            productSpecification = persistenceHelper.editPersist(productSpecification);
            //newProduct = persistenceHelper.editPersist(newProduct);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + newProduct.getName() + " updated");
            userTransaction.commit();

            productSpecifications.add(productSpecification);                        
            viewProductBean.setProductSpecifications(productSpecifications);
            viewProductBean.setProductValues(productValues);
            viewProductBean.setProduct(newProduct);

            FacesUtils.callRequestContext("selectUpdateValueDialog.hide();");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
               // ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    
    
    

    public void insertDimensionActionView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");

            Product newProduct = viewProductBean.getProduct();
            Specification spec = viewProductBean.getSpecification();
            List<Productspecification> dimensionProductSpecifications = viewProductBean.getDimesionProductSpecifications();
            for (int i = 0; i < dimensionProductSpecifications.size(); i++) {
                Productspecification productspecification = dimensionProductSpecifications.get(i);
                if (productspecification.getSpecification().equals(spec) && !spec.getMultipleinsert().equals(BigDecimal.ONE)) {                    
                     sessionBean.setAlertMessage(MessageBundleLoader.getMessage("specAlreadySelected"));
                     FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                     FacesUtils.callRequestContext("generalAlertWidget.show()");                    
                    return;
                }                
            }
            
            
            
            
            Productspecification productSpecification = new Productspecification();
            productSpecification.setSpecification(spec);
            productSpecification.setOrdered(new BigDecimal(1000));
            productSpecification.setProduct(newProduct);
            productSpecification.setActive(BigDecimal.ONE);


            List<Productvalue> productValues = viewProductBean.getProductValues();
            String svalue = viewProductBean.getSvalue();
            Productvalue productvalue = new Productvalue();
            productvalue.setProductspecification(productSpecification);
            productvalue.setValue(svalue);
            productvalue.setActive(BigDecimal.ONE);
            productvalue.setMeasurment(viewProductBean.getSelectedMeasurment());
            productValues.add(productvalue);
            productSpecification.getProductvalues().add(productvalue);

            userTransaction = persistenceHelper.getUserTransaction();


            //productSpecification = persistenceHelper.editPersist(productSpecification);
            dimensionProductSpecifications.add(productSpecification);
            Collections.sort(dimensionProductSpecifications, new Comparator<Productspecification>() {
                public int compare(Productspecification one, Productspecification other) {
                    try {
                        return one.getOrdered().compareTo(other.getOrdered());
                    } catch (Exception ex) {
                        return 0;
                    }
                }
            });


            newProduct.getProductspecifications().add(productSpecification);
            userTransaction.begin();
            persistenceHelper.create(productSpecification);
            //newProduct = persistenceHelper.editPersist(newProduct);            
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + newProduct.getName() + " updated");
            userTransaction.commit();

            viewProductBean.setDimesionProductSpecifications(dimensionProductSpecifications);
            viewProductBean.setProductValues(productValues);

            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            //FacesUtils.callRequestContext("selectDimensionValueDialog.hide();");

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

    public void deleteProductDimensionSpecificationView(Productspecification productSpecification) {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Productspecification> productDimensionSpecifications = viewProductBean.getDimesionProductSpecifications();
            //Productspecification productSpecification = viewProductBean.getProductSpecification();            
            productDimensionSpecifications.remove(productSpecification);
            viewProductBean.setDimesionProductSpecifications(productDimensionSpecifications);
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            persistenceHelper.remove(productSpecification);
            Product product = viewProductBean.getProduct();
            product.getProductspecifications().remove(productSpecification);
            //product = persistenceHelper.editPersist(product);

            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();

            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.updateHTMLComponnetWIthJS("dimensionForm");

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

    public void showDimensionSpecificationsView() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Item item = viewProductBean.getProduct().getItem();
            ItemspecificationDAO dao = new ItemspecificationDAO();
            List<Specification> specs = dao.fetchItemDimensionSpecifications(item, Boolean.TRUE);
            viewProductBean.setSpecifications(specs);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void updateFinalPriceViewProduct() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            if (viewProductBean.getInitialAmount() != null && viewProductBean.getInitialAmount() != null) {
                Double finalPrice = viewProductBean.getInitialAmount() * (100 - viewProductBean.getDiscount()) / 100;
                viewProductBean.setAmount(finalPrice);
            }
        } catch (Exception e) {
        }
    }

    public void insertPriceViewProduct() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Price newPrice = new Price();
            newPrice.setAmount(viewProductBean.getAmount());
            newPrice.setInitialprice(viewProductBean.getInitialAmount());
            newPrice.setDiscount(viewProductBean.getDiscount());
            newPrice.setCompany(viewProductBean.getProduct().getFirstCompany());

            newPrice.setActive(BigDecimal.ONE);
            newPrice.setProduct(viewProductBean.getProduct());
            newPrice.setPricedate(viewProductBean.getPriceDate());
            newPrice.setCurrency(viewProductBean.getCurrency());
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            newPrice = persistenceHelper.editPersist(newPrice);


            List<Price> prices = viewProductBean.getPrices();
            for (int i = 0; i < prices.size(); i++) {
                Price price = prices.get(i);
                persistenceHelper.remove(price);
            }

            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();

            List<Price> pricess = new ArrayList<Price>(0);
            pricess.add(newPrice);
            viewProductBean.setPrices(pricess);

            viewProductBean.setAmount(null);
            viewProductBean.setInitialAmount(null);
            viewProductBean.setDiscount(null);
            viewProductBean.setPriceDate(null);
            viewProductBean.setCurrency(null);

            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));


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

    public void resetPriceViewProduct() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            viewProductBean.setAmount(null);
            viewProductBean.setInitialAmount(null);
            viewProductBean.setDiscount(null);
            viewProductBean.setPriceDate(null);
            viewProductBean.setCurrency(null);

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void resetPriceValueView() {
        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            persistenceHelper.remove(viewProductBean.getPrices().get(0));
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setPrices(new ArrayList<Price>(0));
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));

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

    public void saveBProducts() {
        UserTransaction userTransaction = null;
        try {

            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Product> pproducts = viewProductBean.getProducts().getTarget();
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product.setParentproducts(pproducts);
            product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setProduct(product);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));

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

    public void saveSProducts() {
        UserTransaction userTransaction = null;
        try {

            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Product> sproducts = viewProductBean.getSubProducts().getTarget();
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product.setSubproducts(sproducts);
            product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setProduct(product);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));

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

    public void saveScopeCompanies() {
        UserTransaction userTransaction = null;
        try {

            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Company> companies = viewProductBean.getScopeCompanies().getTarget();
            Product product = viewProductBean.getProduct();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product.setScopeCompanies(companies);
            product = persistenceHelper.editPersist(product);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setProduct(product);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));

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

    public void openSelectImageViewDlg() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            viewProductBean.setNewImage(new Imageproduct());
            FacesUtils.callRequestContext("dialogFotos.show();");
            FacesUtils.updateHTMLComponnetWIthJS("imgData");

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void openSelectVideoViewDlg() {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            viewProductBean.setNewVideo(new Videoproduct());
            FacesUtils.updateHTMLComponnetWIthJS("videoData");
            FacesUtils.callRequestContext("dialogVideos.show();");


        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void handleFileUploadView(FileUploadEvent event) {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Imageproduct newImage = new Imageproduct();
            newImage.setActive(BigDecimal.ONE);
            newImage.setFilename(file.getFileName());
            newImage.setProduct(viewProductBean.getProduct());
            newImage.setPath(path + "\\" + viewProductBean.getProduct().getProductid() + "\\images\\" + file.getFileName());
            viewProductBean.setNewImage(newImage);

            //IOUtils.saveBinaryFile(path + "\\" + viewProductBean.getProduct().getProductid() + "\\images\\" + file.getFileName(), file.getInputstream());
            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void handleFileUploadVideoView(FileUploadEvent event) {
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            UploadedFile file = event.getFile();
            String path = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP");

            Videoproduct newVideo = new Videoproduct();
            newVideo.setActive(BigDecimal.ONE);
            newVideo.setFilename(file.getFileName());
            newVideo.setProduct(viewProductBean.getProduct());
            newVideo.setPath(path + "\\" + viewProductBean.getProduct().getProductid() + "\\videos\\" + file.getFileName());
            viewProductBean.setNewVideo(newVideo);

            //IOUtils.saveBinaryFile(path + "\\" + viewProductBean.getProduct().getProductid() + "\\videos\\" + file.getFileName(), file.getInputstream());
            IOUtils.saveBinaryFile(path + "\\" + file.getFileName(), file.getInputstream());

        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void savePhotoView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Imageproduct> images = viewProductBean.getImages();
            Imageproduct image = viewProductBean.getNewImage();

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            image = persistenceHelper.editPersist(image);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");

            String tempPath = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP") + "\\" + image.getFilename();
            String finalPath = SystemParameters.getInstance().getProperty("PATH_WEB_PRODUCTS") + "\\" + viewProductBean.getProduct().getProductid() + "\\images\\" + image.getFilename();
            IOUtils.saveBinaryFile(finalPath, new FileInputStream(tempPath));
            IOUtils.deleteFile(tempPath);
            userTransaction.commit();


            images.add(image);

            viewProductBean.setNewImage(new Imageproduct());
            viewProductBean.setImages(images);
            FacesUtils.callRequestContext("dialogFotos.hide();");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
        } catch (Exception ex) {
            try {
                userTransaction.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void saveVideoView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            List<Videoproduct> videos = viewProductBean.getVideos();
            Videoproduct video = viewProductBean.getNewVideo();

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            video = persistenceHelper.editPersist(video);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            videos.add(video);


            String tempPath = SystemParameters.getInstance().getProperty("PATH_WEB_TEMP") + "\\" + video.getFilename();
            String finalPath = SystemParameters.getInstance().getProperty("PATH_WEB_PRODUCTS") + "\\" + viewProductBean.getProduct().getProductid() + "\\videos\\" + video.getFilename();
            IOUtils.saveBinaryFile(finalPath, new FileInputStream(tempPath));
            IOUtils.deleteFile(tempPath);
            userTransaction.commit();

            viewProductBean.setNewVideo(new Videoproduct());
            viewProductBean.setVideos(videos);
            FacesUtils.callRequestContext("dialogVideos.hide();");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
        } catch (Exception ex) {
            try {
                userTransaction.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }
    }

    public void saveImageOrderView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Imageproduct image = viewProductBean.getSelectedImage();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            image = persistenceHelper.editPersist(image);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setSelectedImage(image);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateImageOrderDialog.hide()");
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

    public void saveImageDescriptionView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Imageproduct image = viewProductBean.getSelectedImage();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            image = persistenceHelper.editPersist(image);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setSelectedImage(image);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateImageDescriptionDialog.hide()");
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

    public void removeImageView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Imageproduct image = viewProductBean.getSelectedImage();
            List<Imageproduct> images = viewProductBean.getImages();

            images.remove(image);
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.remove(image);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();


            viewProductBean.setImages(images);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateImageDialog.hide()");
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

    public void saveVideoOrderView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Videoproduct video = viewProductBean.getSelectedVideo();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            video = persistenceHelper.editPersist(video);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setSelectedVideo(video);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateVideoOrderDialog.hide()");
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

    public void saveVideoDescriptionView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Videoproduct video = viewProductBean.getSelectedVideo();

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            video = persistenceHelper.editPersist(video);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();
            viewProductBean.setSelectedVideo(video);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateVideoDescriptionDialog.hide()");
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

    public void removeVideoView() {
        UserTransaction userTransaction = null;
        try {
            ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
            Videoproduct video = viewProductBean.getSelectedVideo();
            List<Videoproduct> videos = viewProductBean.getVideos();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.remove(video);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCT")), "Product " + viewProductBean.getProduct().getName() + " updated");
            userTransaction.commit();

            videos.remove(video);
            viewProductBean.setVideos(videos);
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productUpdated"));
            FacesUtils.callRequestContext("updateVideoDialog.hide()");
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

    public void searchProductAction() {
        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            ProductDAO dao = new ProductDAO();
            TreeNode[] nodes = productSearchBean.getSelectedNodes();
            List<Category> categories = null;
            if (nodes != null) {
                categories = new ArrayList<Category>(0);
                for (int i = 0; i < nodes.length; i++) {
                    TreeNode treeNode = nodes[i];
                    categories.add((Category) treeNode.getData());
                }
            }

            List<Product> products = new ArrayList<Product>(0);

            if ((categories == null || categories.size() == 0) && (productSearchBean.getSearchBySelectedCompanies() == null || productSearchBean.getSearchBySelectedCompanies().size() == 0) && productSearchBean.getSearchByName() == null) {
//                if (sessionBean.getUsers().getRole().getRoleid().equals(BigDecimal.ONE)) {
                    products = dao.findByProperty("active", BigDecimal.ONE);
//                } else {
//                    List<Company> companies = new ArrayList<Company>(0);
//                    companies.add(sessionBean.getUsers().getCompany());
//                    products = dao.getCategoryProduct(null, companies, null);
//                }
            } else {
//                if (sessionBean.getUsers().getRole().getRoleid().equals(BigDecimal.ONE)) {
                   products = dao.getCategoryProduct(categories, productSearchBean.getSearchBySelectedCompanies(), productSearchBean.getSearchByName());
//                } else {
//                    List<Company> companies = new ArrayList<Company>(0);
//                    companies.add(sessionBean.getUsers().getCompany());
//                    products = dao.getCategoryProduct(categories, companies, productSearchBean.getSearchByName());
//                }                
            }


//            List<Product> products = dao.searchProducts(productSearchBean.getSearchByName(), productSearchBean.getSearchByCompany(), productSearchBean.getSearchByProductline(), productSearchBean.getSearchByCatalogue(), productSearchBean.getSearchByProductcategory());
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
            Product product = productSearchBean.getSelectedProduct();

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            product.setActive(BigDecimal.ZERO);
            product = persistenceHelper.editPersist(product);           
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETEPRODUCT")), "Product " + product.getName() + " deleted");
            userTransaction.commit();
            
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productDeleted"));
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
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCTPRICE")), "Product " + priceBean.getSelectedProduct().getName() + " new price inserted");
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

            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            persistenceHelper.remove(price);            
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPRODUCTPRICE")), "Product " + priceBean.getSelectedProduct().getName() + "  price updated");
            userTransaction.commit();
            List<Price> prices = (new PriceDAO()).findByProperty("product", priceBean.getSelectedProduct());
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

    public void morerows() {
        ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
        viewProductBean.setRows("40");
    }

    public void lessrows() {
        ViewProductBean viewProductBean = (ViewProductBean) FacesUtils.getManagedBean("viewProductBean");
        viewProductBean.setRows("5");
    }

    public void morerowsView() {
        NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
        newProductBean.setRows("40");
    }

    public void lessrowsView() {
        NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
        newProductBean.setRows("5");
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
        List<Product> products = productSearchBean.getProducts();

        String FONT = "c:\\Windows\\Fonts\\Arial.ttf";
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font f = new Font(bf, 12);

        //f.setStyle(Font.BOLD);

        Document pdf = (Document) document;

        pdf.open();
        pdf.setPageSize(PageSize.A4);

        String logo = FacesUtils.getServletContext().getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "efurn-logo.png";
        //servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";  

        System.out.println(logo);
        pdf.add(Image.getInstance(logo));

        Chunk chunk = new Chunk("kritiria Anazitisis", f);
        Paragraph paragraph = new Paragraph();
        paragraph.add(chunk);
        pdf.add(paragraph);


        PdfPTable table = new PdfPTable(4);


        // Font f = new Font(bf, 12);
        f.setColor(Color.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase("1", f));
        cell.setBackgroundColor(Color.BLACK);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("2", f));

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("3", f));
        cell.setBackgroundColor(Color.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("4", f));
        cell.setBackgroundColor(Color.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        table.getDefaultCell().setBackgroundColor(Color.LIGHT_GRAY);
        f.setColor(Color.BLACK);
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            table.addCell(String.valueOf(i + 1));
            table.addCell(new Phrase(product.getName(), f));
            table.addCell(new Phrase(product.getFirstCompany().getName(), f));
            List<Productspecification> ps = product.getProductspecifications();


            String txt = "";
            for (int j = 0; j < ps.size(); j++) {
                Productspecification productspecification = ps.get(j);
                txt = txt + productspecification.getSpecification().getName() + ": ";
                System.out.println(txt);
                List<Productvalue> values = productspecification.getProductvalues();
                for (int k = 0; k < values.size(); k++) {
                    Productvalue productvalue = values.get(k);
                    if (productspecification.getSpecification().getFreetext().equals(BigDecimal.ONE)) {
                        txt = txt + productvalue.getValue();
                        System.out.println(txt);
                    } else if (productspecification.getSpecification().getFreetext().equals(BigDecimal.ZERO)) {   //&& productspecification.getSpecification().getMultiplevalues().equals(BigDecimal.ZERO
                        txt = txt + productvalue.getSvalue().getName();
                        System.out.println(txt);
                    }
                }
            }



            table.addCell(new Phrase(txt, f));

        }

        pdf.add(table);

        //pdf.close();

//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
//        String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";  
//
//        pdf.add(Image.getInstance(logo));  
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                          TEMPLATES!!!!!!!!!!!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void test() {

        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
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
