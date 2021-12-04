package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.company.JavaListT.*; // Moja lista
import com.company.JavaSetT.*; // Mój Set

public class Mapa<K,V> implements Map<K,V>
{
    private K[] keys;
    private V[] values;
    private Integer keys_size;
    private Integer values_size;

    public Mapa(){
        keys = (K[]) new Object[0];
        values = (V[]) new Object[0];
        keys_size = 0;
        values_size = 0;
    }

    @Override
    public int size() {
        return keys_size;
    }

    @Override
    public boolean isEmpty() {
        return keys_size > 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for(int i = 0; i < keys.length; i++) if((K)key == keys[i]) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < values.length; i++) if((K)value == values[i]) return true;
        return false;
    }

    private Integer indexOfKey(Object key)
    {
        for(int i = 0; i< keys.length; i++) if((K)key == keys[i]) return i;
        return -1;
    }

    private Integer indexOfValue(Object value)
    {
        for(int i = 0; i < values.length; i++) if((K)value == values[i]) return i;
        return -1;
    }

    private K getKey(Integer i)
    {
        return keys[i];
    }

    private V getValue(Integer i)
    {
        return values[i];
    }

    @Override
    public V get(Object key) {
        int i = indexOfKey(key);
        if (i == -1) return null;
        return getValue(i);
    }

    @Override
    public V put(K key, V value) {

        int i_of_k = indexOfKey(key);

        if(i_of_k == -1)
        {
            keys = Arrays.copyOf(keys,++keys_size);
            values = Arrays.copyOf(values,++values_size);

            keys[keys_size - 1] = key;
            values[values_size - 1] = value;
            return null;
        }
        else
        {
            V pom = values[i_of_k];

            values[i_of_k] = value;
            return pom;
        }

    }

    private boolean addKey(K key)
    {
        this.keys = Arrays.copyOf(keys,++keys_size);
        this.keys[keys_size - 1] = key;
        return true;
    }

    private  boolean addValue(V value)
    {
        this.values = Arrays.copyOf(values,++values_size);
        this.values[values_size - 1] = value;
        return true;
    }

    @Override
    public V remove(Object key) {
        int i_of_k = indexOfKey(key);

        if(i_of_k != -1)
        {
            V V_pom = values[i_of_k];

            K[] keys_pom1;
            K[] keys_pom2;
            keys_pom1 = Arrays.copyOf(keys,i_of_k);
            keys_pom2 = Arrays.copyOfRange(keys,i_of_k + 1,keys_size);

            keys = keys_pom1;
            keys_size = keys.length;

            for(int i = 0; i < keys_pom2.length; i++)
            {
                addKey(keys_pom2[i]);
            }

            V[] values_pom1;
            V[] values_pom2;
            values_pom1 = Arrays.copyOf(values,i_of_k);
            values_pom2 = Arrays.copyOfRange(values,i_of_k + 1,values_size);

            values = values_pom1;
            values_size = values.length;

            for(int i = 0; i < values_pom2.length; i++)
            {
                addValue(values_pom2[i]);
            }

            return V_pom;
        }
        else
        {
            return null;
        }

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        Lista<K> new_keys = (Lista<K>) keys((Mapa<K, V>) m);
        Lista<V> new_values = (Lista<V>) values((Mapa<K,V>) m);
        for(int i = 0; i < m.size(); i++)
        {
            put(new_keys.get(i),new_values.get(i));
        }
    }

    @Override
    public void clear() {
        keys = (K[]) new Object[0];
        values = (V[]) new Object[0];
    }

    @Override
    public Set<K> keySet() {
        com.company.JavaSetT.SetT<K> ZbiorK = new com.company.JavaSetT.SetT<K>(keys);
        return ZbiorK;
    }

    @Override
    public Collection<V> values() {
        return new com.company.JavaListT.Lista<V>(values);
    }

    private Collection<V> values(Mapa<K,V> mapa) {
        return new com.company.JavaListT.Lista<V>(mapa.values);
    }

    private Collection<K> keys(Mapa<K,V> mapa) {
        return new com.company.JavaListT.Lista<K>(mapa.keys);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        com.company.JavaSetT.SetT<K> ZbiorK = new com.company.JavaSetT.SetT<K>(keys);
        com.company.JavaSetT.SetT<V> ZbiorV = new com.company.JavaSetT.SetT<V>(values);

        Entry<K,V> Zbior[] = (Entry<K,V>[]) new Object[]{ZbiorK,ZbiorV};
        return new SetT<Entry<K,V>>(Zbior);
    }
}
