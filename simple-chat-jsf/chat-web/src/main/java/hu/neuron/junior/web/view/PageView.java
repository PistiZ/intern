package hu.neuron.junior.web.view;

import org.apache.commons.lang3.LocaleUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "pageView")
@ViewScoped
public class PageView {

    private static final String HUNGARIAN = "Hungarian";

    private static final String ENGLISH = "English";

    private static Map<String, Locale> locales;

    private static LinkedList<String> countriesList;

    private String locale;

    @PostConstruct
    public void init() {
        locales = new LinkedHashMap<>();
        locales.put(HUNGARIAN, LocaleUtils.toLocale("hu_HU"));
        locales.put(ENGLISH, Locale.ENGLISH);

        countriesList = new LinkedList<>();
        countriesList.add(ENGLISH);
        countriesList.add(HUNGARIAN);
    }

    public Locale getLocaleVar() {
        if (locale != null && locales.containsKey(locale)) {
            return locales.get(locale);
        } else {
            return locales.get(HUNGARIAN);
        }
    }

    public void setLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locales.get(locale));
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LinkedList<String> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(LinkedList<String> countriesList) {
        PageView.countriesList = countriesList;
    }

}
