package com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals;

/**
 * Package Name : com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals
 * Created by ajesh on 15-08-2018.
 * Project Name : WaferCodeChallenge
 */
public class Currencies {
    private String symbol;

    private String name;

    private String code;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Currencies [symbol = " + symbol + ", name = " + name + ", code = " + code + "]";
    }
}
