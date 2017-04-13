package io.spring.batch.gdc;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;


/**
 * Created by Dixit
 */
public class GDCSegmentedFieldMapper implements FieldSetMapper<GDCSegmentedData> {

    @Override
    public GDCSegmentedData mapFieldSet(FieldSet fieldSet) throws BindException{
        return new GDCSegmentedData(
                fieldSet.readString("id"),
                fieldSet.readLong("chromosome"),
                fieldSet.readLong("start"),
                fieldSet.readLong("end"),
                fieldSet.readLong("num_probes"),
                fieldSet.readLong("seg_mean"));

    }


}


