package com.github.transactpro.gateway.model.response;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

public class CsvResponse implements Iterable<CSVRecord> {
    private Iterable<CSVRecord> records;

    public void init(String data) throws IOException {
        Reader reader = new StringReader(data);
        records = CSVFormat.RFC4180
                .withFirstRecordAsHeader()
                .withIgnoreEmptyLines()
                .parse(reader);
    }

    @Override
    public Iterator<CSVRecord> iterator() {
        return records.iterator();
    }
}
