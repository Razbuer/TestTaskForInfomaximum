package dev.rusyaev.utils;

import dev.rusyaev.entity.Address;

import java.util.*;

public class DecoratorCollectionForStatisticsAddresses<E> implements Collection<E> {
    private final Collection<E> collection;
    private final Map<String, Map<Byte, Long>> countAddressesOnFloor;
    private final Map<Address, Integer> doubleAddresses;

    public DecoratorCollectionForStatisticsAddresses(Collection<E> collection, Map<String, Map<Byte, Long>> countAddressesOnFloor, Map<Address, Integer> doubleAddresses) {
        this.collection = collection;
        this.countAddressesOnFloor = countAddressesOnFloor;
        this.doubleAddresses = doubleAddresses;
    }

    @Override
    public boolean add(E e) {
        if (e.getClass() == Address.class) {
            Address address = (Address) e;

            // Подсчёт количества адресов на каждом этаже
            byte floor = address.getFloor();
            String city = address.getCity();
            countAddressesOnFloor.putIfAbsent(city, new HashMap<>());
            Map<Byte, Long> cityOfCountAddressesOnFloor = countAddressesOnFloor.get(city);
            cityOfCountAddressesOnFloor.putIfAbsent(floor, 0L);
            cityOfCountAddressesOnFloor.put(floor, cityOfCountAddressesOnFloor.get(floor) + 1);

            // Подсчёт количества совпадений адресов
            if (collection.contains(address)) {
                doubleAddresses.putIfAbsent(address, 1);
                doubleAddresses.put(address, doubleAddresses.get(address) + 1);
            }
        }

        return collection.add(e);
    }

    public Map<String, Map<Byte, Long>> getCountAddressesOnFloor() {
        return countAddressesOnFloor;
    }

    public Map<Address, Integer> getDoubleAddresses() {
        return doubleAddresses;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return collection.addAll(c);
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }
}
