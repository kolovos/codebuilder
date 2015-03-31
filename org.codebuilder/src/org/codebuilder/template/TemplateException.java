package org.codebuilder.template;

public class TemplateException extends Exception{

        protected Template template; // the template that caused the exception
        protected TemplateExceptionKind kind; // the kind of the exception

        public TemplateException( Template template, TemplateExceptionKind kind, Exception original){
                super(original);
                this.template = template;
                this.kind = kind;
        }

        /**
         * Sets the template that caused the exception
         * @param template The new value
        */
        public void setTemplate(Template template){
                this.template = template;
        }

        /**
         * Returns the template that caused the exception
         * @return Template The value of template
        */
        public Template getTemplate(){
                return this.template;
        }

        /**
         * Sets the kind of the exception
         * @param kind The new value
        */
        public void setKind(TemplateExceptionKind kind){
                this.kind = kind;
        }

        /**
         * Returns the kind of the exception
         * @return TemplateExceptionKind The value of kind
        */
        public TemplateExceptionKind getKind(){
                return this.kind;
        }

}
