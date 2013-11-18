package com.furniture.converter;

import com.furniture.entities.Companycatalogue;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;

public class CompanyCatalogueConverter implements Converter {
    
    private PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().isEmpty()) {
            return null;
        } else {
            try {
                BigDecimal number = new BigDecimal(submittedValue);

                Companycatalogue companycatalogue = persistenceHelper.getEntityManager().find(Companycatalogue.class, number);
                return companycatalogue;


            } catch (Exception exception) {
                exception.printStackTrace();
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid CompanyCatalogue"));
            }
        }


    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        try {
            if (value == null || value.equals("")) {
                return "";
            } else {

                return String.valueOf(((Companycatalogue) value).getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
