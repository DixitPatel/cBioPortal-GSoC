package io.spring.batch.processor;

import io.spring.batch.cBioPortal.CBioSegmentedData;
import io.spring.batch.gdc.GDCSegmentedData;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dixit on 13/04/17.
 */
public class SegmentedDataProcessor implements ItemProcessor<GDCSegmentedData, CBioSegmentedData> {

    // sample
    @Override
    public CBioSegmentedData process(GDCSegmentedData gdcSegData) throws Exception {
        CBioSegmentedData cBioSegData = new CBioSegmentedData();
        cBioSegData.setId(gdcSegData.getId());
        cBioSegData.setChromosome(gdcSegData.getChromosome());
        cBioSegData.setStart(gdcSegData.getStart());
        cBioSegData.setEnd(gdcSegData.getEnd());
        cBioSegData.setNum_mark(gdcSegData.getNum_probes());
        cBioSegData.setSeg_mean(gdcSegData.getSeg_mean());

        return cBioSegData;

    }
}
