/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.util;

import com.furniture.entities.Catalogue;
import com.furniture.entities.Catalogueproductline;
import com.furniture.entities.Company;
import com.furniture.entities.Companycatalogue;
import com.furniture.entities.Productline;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author peukianm
 */
public class FurnitureUtil {

    public static List<Productline> getProductLineFromCompany(Company company) {
        ArrayList<Productline> retValue = new ArrayList<Productline>(0);
        try {
            List<Companycatalogue> catalogues = new ArrayList<Companycatalogue>(company.getCompanycatalogues());
            for (int i = 0; i < catalogues.size(); i++) {
                Catalogue catalogue = catalogues.get(i).getCatalogue();
                if (catalogue.getActive().equals(BigDecimal.ONE)) {
                    List<Catalogueproductline> lines = new ArrayList<Catalogueproductline>(catalogue.getCatalogueproductlines());
                    for (int j = 0; j < lines.size(); j++) {
                        Catalogueproductline catalogueproductline = lines.get(j);
                        if (catalogueproductline.getProductline().getActive().equals(BigDecimal.ONE)) {
                            retValue.add(catalogueproductline.getProductline());
                        }
                    }
                }
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw ex;
        }

        Collections.sort(retValue, new Comparator<Productline>() {
            public int compare(Productline one, Productline other) {
                return one.getName().compareTo(other.getName());
            }
        });
        return retValue;
    }
}
