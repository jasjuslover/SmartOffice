package com.example.plasma.smartoffice.network.response;

public class ParameterValue {
    private String Id;
    private String Value;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [Id = " + Id + "]";
    }
}
