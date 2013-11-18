package com.furniture.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;

import com.furniture.util.MessageBundleLoader;

import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.convert.ConverterException;
import javax.faces.validator.ValidatorException;

public class DoubleConverter extends DateTimeConverter implements Converter {

    private String patern = "#.###,00";

    public DoubleConverter() {
    }

    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        try {
            Object db = "";
            if (value == null || value.isEmpty()) {
                return null;
            }
            //DecimalFormat df =  new DecimalFormat(patern);     //new DecimalFormat("#,##");
            
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            format.setGroupingUsed(true);

            //db = df.parseObject(value);

            db = format.parseObject(value);
            return db;
        } catch (Exception e) {
            //e.printStackTrace();
            //context.addMessage(component.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageBundleLoader.getMessage("integerValidation"), MessageBundleLoader.getMessage("integerValidation")));
            throw new  ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    MessageBundleLoader.getMessage("integerValidation"),
                    MessageBundleLoader.getMessage("integerValidation")));
            //return value;
        }
    }

    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

        try {

            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            format.setGroupingUsed(true);
            return format.format(value);
        } catch (Exception e) {
            return "";
        }
    }
}
