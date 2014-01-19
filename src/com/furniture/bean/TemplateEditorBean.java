/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.*;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class TemplateEditorBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private List<Item> items = new ArrayList<Item>(0);
    private List<Specification> specifications = new ArrayList<Specification>(0);
    private List<Svalue> svalues = new ArrayList<Svalue>(0);
    private Item item;
    private Specification specification;
    private Svalue svalue;
    private DualListModel<Specification> specificationPickList = new DualListModel<Specification>(new ArrayList<Specification>(0), new ArrayList<Specification>(0));
    private DualListModel<Svalue> svaluePickList = new DualListModel<Svalue>(new ArrayList<Svalue>(0), new ArrayList<Svalue>(0));
    private boolean color;
    private boolean dimension;
    private boolean freetext;
    private boolean multivalue;
    private boolean multiinsert;

    @PostConstruct
    public void init() {
        System.out.println("INITIALIZING IN TEMPLATE EDITOR BEAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");
    }

    @PreDestroy
    public void reset() {
        color = false;
        dimension = false;
        freetext = false;
        multivalue = false;
        multiinsert = false;
        specificationPickList = new DualListModel<Specification>(new ArrayList<Specification>(0), new ArrayList<Specification>(0));
        svaluePickList = new DualListModel<Svalue>(new ArrayList<Svalue>(0), new ArrayList<Svalue>(0));
        item = null;
        specification = null;
        svalue = null;
        items = new ArrayList<Item>(0);
        specifications = new ArrayList<Specification>(0);
        svalues = new ArrayList<Svalue>(0);
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isDimension() {
        return dimension;
    }

    public void setDimension(boolean dimension) {
        this.dimension = dimension;
    }

    public boolean isFreetext() {
        return freetext;
    }

    public void setFreetext(boolean freetext) {
        this.freetext = freetext;
    }

    public boolean isMultivalue() {
        return multivalue;
    }

    public void setMultivalue(boolean multivalue) {
        this.multivalue = multivalue;
    }

    public boolean isMultiinsert() {
        return multiinsert;
    }

    public void setMultiinsert(boolean multiinsert) {
        this.multiinsert = multiinsert;
    }

    public DualListModel<Specification> getSpecificationPickList() {
        return specificationPickList;
    }

    public void setSpecificationPickList(DualListModel<Specification> specificationPickList) {
        this.specificationPickList = specificationPickList;
    }

    public DualListModel<Svalue> getSvaluePickList() {
        return svaluePickList;
    }

    public void setSvaluePickList(DualListModel<Svalue> svaluePickList) {
        this.svaluePickList = svaluePickList;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public List<Svalue> getSvalues() {
        return svalues;
    }

    public void setSvalues(List<Svalue> svalues) {
        this.svalues = svalues;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Svalue getSvalue() {
        return svalue;
    }

    public void setSvalue(Svalue svalue) {
        this.svalue = svalue;
    }
}
