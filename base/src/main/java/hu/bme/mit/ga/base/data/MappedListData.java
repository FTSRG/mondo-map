package hu.bme.mit.ga.base.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

public class MappedListData<K, V extends Number> implements BaseData {

    protected ListMultimap<K, V> typedValues;

    public MappedListData() {
        typedValues = ArrayListMultimap.create();
    }

    public ListMultimap<K, V> getValues() {
        return typedValues;
    }

    public void setTypedValues(ListMultimap<K, V> typedValues) {
        this.typedValues = typedValues;
    }

    public boolean put(K key, V value) {
        return typedValues.put(key, value);
    }

    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        return typedValues.putAll(multimap);
    }

    @Override
    public String toString() {
        return "MappedListData [typedValues=" + typedValues + "]";
    }

}
