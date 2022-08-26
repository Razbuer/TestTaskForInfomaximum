package dev.rusyaev.entity;

import java.util.Objects;

public class Address implements Comparable<Address> {
    private final String city;
    private final String street;
    private final short house;
    private final byte floor;

    public Address(String city, String street, short house, byte floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public short getHouse() {
        return house;
    }

    public byte getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (house != address.house) return false;
        if (floor != address.floor) return false;
        if (!Objects.equals(city, address.city)) return false;
        return Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (int) house;
        result = 31 * result + (int) floor;
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", floor=" + floor +
                '}';
    }

    @Override
    public int compareTo(Address o) {
        int result;

        result = o.city.compareTo(this.city);
        if (result != 0)
            return result;

        result = o.street.compareTo(this.street);
        if (result != 0)
            return result;
;
        if (o.house != this.house)
            return o.house - this.house;

        if (o.floor != this.floor)
            return o.floor - this.floor;

        return 0;
    }
}
