package io.spring.batch.cBioPortal;

/**
 * Created by Dixit on 13/04/17.
 */
public class CBioSegmentedData {
    private  String id;
    private  long chromosome;
    private  long start;
    private  long end;
    private  long num_mark;
    private  long seg_mean;

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

    public long getNum_mark() {
        return num_mark;
    }

    public long getSeg_mean() {
        return seg_mean;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChromosome(long chromosome) {
        this.chromosome = chromosome;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public void setNum_mark(long num_mark) {
        this.num_mark = num_mark;
    }

    public void setSeg_mean(long seg_mean) {
        this.seg_mean = seg_mean;
    }

    @Override
    public String toString() {
        return "CBioSegmentedData{" +
                "id='" + id + '\'' +
                ", chromosome=" + chromosome +
                ", start=" + start +
                ", end=" + end +
                ", num_mark=" + num_mark +
                ", seg_mean=" + seg_mean +
                '}';
    }
}
