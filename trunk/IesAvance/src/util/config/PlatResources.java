package util.config;

import java.util.*;

public class PlatResources extends ListResourceBundle 
{
    public Object[][] getContents() 
    {
        return contents;
    }

    static final Object[][] contents = {   
    			// LOCALIZE THIS
 				{"CaminhoRaiz", "D:\\src\\Eclipse\\NovaPlat"},
 				// END OF MATERIAL TO LOCALIZE
    };

}
