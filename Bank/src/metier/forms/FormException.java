package metier.forms;

import java.text.Normalizer;

public class FormException extends Exception{
    private String formName,fieldName;
    String consigne;
    public FormException(){super("Erreur dans le formulaire !");}
    public FormException(String errorMsg){super(errorMsg);}
    public FormException(String errorMsg,String cons){super(errorMsg);this.consigne=cons;}


    public FormException(String formName,String fieldName,String errorMsg,String cons){
        super(errorMsg);
        this.fieldName=fieldName;
        this.formName=formName;
        this.consigne=cons;
    }

    public FormException(String formName,String fieldName,String cons){
        this();
        this.fieldName=fieldName;
        this.formName=formName;
        this.consigne=cons;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }


}
