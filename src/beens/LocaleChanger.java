package beens;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class LocaleChanger implements Serializable {

    private Locale currentLocale; // = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @PostConstruct
    public void init() {
        currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    private UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
    public LocaleChanger() {
    }

    public void changeLocale(String localeCode) {
        currentLocale = new Locale(localeCode);
        //FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(localeCode));
       // FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
       // return "books";
    }

    public Locale getCurrentLocale() {
       /* if (null == viewRoot){
            currentLocale = Locale.getDefault();
        } else {
            currentLocale = viewRoot.getLocale();
        }*/
        return currentLocale;
    }
}
