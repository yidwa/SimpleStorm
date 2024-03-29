package tuple;

import java.util.ArrayList;

/**
 * A convenience class for making tuple values using new Values("field1", 2, 3)
 * syntax.
 */
public class Values extends ArrayList<Object>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Values() {
        
    }
    
    public Values(Object... vals) {
        super(vals.length);
        for(Object o: vals) {
            add(o);
        }
    }
}
