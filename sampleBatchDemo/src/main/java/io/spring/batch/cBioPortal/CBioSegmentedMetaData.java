package io.spring.batch.cBioPortal;

/**
 * Created by Dixit on 13/04/17.
 */
public class CBioSegmentedMetaData {

    private  String cancer_id;
    private  String genetic_alteration_type;
    private  String datatype;
    private  String ref_genome_id;
    private  String description;
    private  String data_filename;
    private  String gene_panel;

    public void setCancer_id(String cancer_id) {
        this.cancer_id = cancer_id;
    }

    public void setGenetic_alteration_type(String genetic_alteration_type) {
        this.genetic_alteration_type = genetic_alteration_type;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public void setRef_genome_id(String ref_genome_id) {
        this.ref_genome_id = ref_genome_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setData_filename(String data_filename) {
        this.data_filename = data_filename;
    }

    public void setGene_panel(String gene_panel) {
        this.gene_panel = gene_panel;
    }

    @Override
    public String toString() {
        return "CBioSegmentedMetaData{" +
                "cancer_id='" + cancer_id + '\'' +
                ", genetic_alteration_type='" + genetic_alteration_type + '\'' +
                ", datatype='" + datatype + '\'' +
                ", ref_genome_id='" + ref_genome_id + '\'' +
                ", description='" + description + '\'' +
                ", data_filename='" + data_filename + '\'' +
                ", gene_panel='" + gene_panel + '\'' +
                '}';
    }


}
