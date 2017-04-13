package io.spring.batch.gdc;

import org.springframework.batch.item.ResourceAware;

import org.springframework.core.io.Resource;

public class GDCSegmentedData implements ResourceAware{

    private  String id;
    private  long chromosome;
    private  long start;
    private  long end;
    private  long num_probes;
    private  long seg_mean;
    private Resource resource;

    public String getId() {
        return id;
    }

    public long getChromosome() {
        return chromosome;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getNum_probes() {
        return num_probes;
    }

    public long getSeg_mean() {
        return seg_mean;
    }

    public Resource getResource() {
        return resource;
    }

    public GDCSegmentedData(String id, long chromosome, long start, long end, long num_probes, long seg_mean){
        this.id=id;
        this.chromosome=chromosome;
        this.start=start;
        this.end=end;
        this.num_probes=num_probes;
        this.seg_mean=seg_mean;

    }

    @Override
    public String toString() {
        return "GDCSegmentedData{" +
                "id='" + id + '\'' +
                ", chromosome=" + chromosome +
                ", start=" + start +
                ", end=" + end +
                ", num_probes=" + num_probes +
                ", seg_mean=" + seg_mean +
                ", resource=" + resource +
                '}';
    }

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }



}
