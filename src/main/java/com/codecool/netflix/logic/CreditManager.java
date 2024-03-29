package com.codecool.netflix.logic;

import com.codecool.netflix.data.Credit;
import com.codecool.netflix.data.Title;
import com.codecool.netflix.logic.reader.CreditReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CreditManager implements CsvItemCollection {
    private final CreditReader reader;
    private List<Credit> credits;

    public CreditManager(CreditReader reader) {
        this.reader = reader;
    }

    @Override
    public void init() throws IOException {
        credits = reader.readAll("/credits.csv");
    }

    public List<Title> getAllTitlesByCreditName(String name, List<Title> titles) {
        List<String> filmIds = credits
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .map(c -> c.getId())
                .collect(Collectors.toList());

        return titles.stream().filter(t -> filmIds.contains(t.getId())).collect(Collectors.toList());
    }

    public List<Credit> getCredits() {
        return this.credits;
    }
}
