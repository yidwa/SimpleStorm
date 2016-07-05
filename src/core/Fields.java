package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * Collection of unique named fields using in an ITuple
 */
public class Fields implements Iterable<String>, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<String> values;
    private Map<String, Integer> index = new HashMap<>();
    
    public Fields(String values) {
        this(Arrays.asList(values));
    }
    
    public Fields(List<String> values) {
        values = new ArrayList<>(values.size());
        for (String v : values) {
            if (values.contains(v))
                throw new IllegalArgumentException(
                    String.format("duplicate field '%s'", v)
                );
            values.add(v);
        }
        index();
    }
    
    /**
     * Select values out of tuple given a Fields selector
     * Note that this function can throw a NullPointerException if the 
     * fields in selector are not found in the _index
     *  c
     * @param selector Fields to select
     * @param tuple tuple to select from
     *
     */
    public List<Object> select(Fields selector, List<Object> tuple) {
        List<Object> ret = new ArrayList<>(selector.size());
        for(String s: selector) {
            ret.add(tuple.get(index.get(s)));
        }
        return ret;
    }

    public List<String> toList() {
        return new ArrayList<>(values);
    }
    
    /**
     * Returns the number of fields in this collection.
     */
    public int size() {
        return values.size();
    }

    /**
     * Gets the field at position index in the collection. 
     *  
     * @param index index of the field to return 
     *  
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size()) 
     */
    public String get(int index) {
        return values.get(index);
    }

    public Iterator<String> iterator() {
        return values.iterator();
    }
    
    /**
     * Returns the position of the specified named field.
     *  
     * @param field Named field to evaluate
     *  
     * @throws IllegalArgumentException - if field does not exist
     */
    public int fieldIndex(String field) {
        Integer ret = index.get(field);
        if(ret==null) {
            throw new IllegalArgumentException(field + " does not exist");
        }
        return ret;
    }
    
    /**
     * @return true if this contains the specified name of the field.
     */
    public boolean contains(String field) {
        return index.containsKey(field);
    }
    
    private void index() {
        for(int i=0; i<values.size(); i++) {
            index.put(values.get(i), i);
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }    
}