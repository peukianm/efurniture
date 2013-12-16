package com.furniture.util;

import com.furniture.entities.Action;
import com.furniture.entities.Auditing;
import com.furniture.entities.Catalogue;
import com.furniture.entities.Item;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import com.furniture.entities.Specification;
//import com.furniture.entities.Auditing;
import com.furniture.entities.Users;
import com.furniture.exception.HerpRollbackException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PersistenceUtil {

    @EJB
    private PersistenceHelper persistenceHelper;

    public void audit(Users user, BigDecimal actionID,  String comments) throws Exception {
        try {

            Action action = (Action) persistenceHelper.find(Action.class, actionID);

            Auditing auditing = new Auditing();
            auditing.setUsers(user);

            System.out.println(FormatUtils.formatDateToTimestamp(new Date(), FormatUtils.FULLDATEPATTERN));
            auditing.setActiondate(FormatUtils.formatDateToTimestamp(new Date(), FormatUtils.FULLDATEPATTERN));
            auditing.setAction(action);
            if (user.getCompany()!=null)
                auditing.setCompany(user.getCompany());
            
            auditing.setComments(comments);
           
            persistenceHelper.create(auditing);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public void audit(Users user, BigDecimal actionID, Product product, Productline productline, Catalogue catalogue, Item item, Specification specification,  String comments) throws Exception {
        try {

            Action action = (Action) persistenceHelper.find(Action.class, actionID);

            Auditing auditing = new Auditing();
            auditing.setUsers(user);

            System.out.println(FormatUtils.formatDateToTimestamp(new Date(), FormatUtils.FULLDATEPATTERN));
            auditing.setActiondate(FormatUtils.formatDateToTimestamp(new Date(), FormatUtils.FULLDATEPATTERN));
            auditing.setAction(action);
            auditing.setComments(comments);
           
            if (product!=null) {
                auditing.setProduct(product);
            }
            
            if (productline!=null) {
                auditing.setProductline(productline);
            }
            
            if (catalogue!=null) {
                auditing.setCatalogue(catalogue);
            }
            
            if (item!=null) {
                auditing.setItem(item);
            }
            
            if (specification!=null) {
                auditing.setSpecification(specification);
            }
            persistenceHelper.create(auditing);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    

    
   
}
