/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author laoui
 * 
 */
    
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ControlSaisie {
    
    

public class ControleSaisie {

       private  Matcher matcher;

    public boolean isString(String text) {



        if (text.matches("^[a-zA-Z]+$")) {

            return true;

        } 

            return false;

    }

     public  boolean isNull(String text){

         if(text == ""){

             return true; //null

         }

         return false ;//n'est pas vide

     }

          public  boolean isUsername(String text) {



        if (text.matches("^[A-Za-z0-9]+$+") ) {

            return true;

        } 

            return false;

    }

          public  boolean DateNullCS(String date){

            if(date == ""){

                return true ;

            }

              return false;

          }

      public  boolean adresse(String text) {



        if (text.matches("^[A-Z a-z 0-9]+$")) {

            return true;

        }

            return false;

    }

          public  boolean iscin(String text) {



        if (text.matches("^[0-9]+$")&& text.length()==8)  {

            return true;

        } else {

            return false;

        }

    }

                  public  boolean isTel(String text) {



        if (text.matches("^[0-9]+$")&& text.length()==8) {

            return true;

        } else {

            return false;

        }

    }



     private static final String EMAIL_PATTERN

            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                      private  Pattern pattern = Pattern.compile(EMAIL_PATTERN);

                          private static final String pwd=  "^[A-Za-z0-9]+$";

                                private  Pattern pattern1 = Pattern.compile(pwd);

     public  boolean valiemail(final String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }

      public  boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }

}
}
