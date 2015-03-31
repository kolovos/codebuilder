package org.codebuilder.template;

public final class TemplateExceptionKind{

        private String value;

        // The enumeration members
        public static TemplateExceptionKind READ_EXCEPTION = new TemplateExceptionKind("Failed to read from source file"); // $member.description
        public static TemplateExceptionKind WRITE_EXCEPTION = new TemplateExceptionKind("Failed to write to target file"); // $member.description
        public static TemplateExceptionKind DUBLICATE_HAND_WRITTEN_REGION_EXCEPTION = new TemplateExceptionKind("Duplicate hand written region detected"); // $member.description

        /**
        * Private constructor to ensure that
        * instances can only be creaded from
        * within the context of the class
        * @param value String
        */
        private TemplateExceptionKind(String value){
                this.value = value;
        }

        /**
        * Returns the internal value
        * of the member
        * @return String the interal value
        */
        public String toString(){
                return value;
        }
}
