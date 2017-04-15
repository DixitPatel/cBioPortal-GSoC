
package io.spring.batch.JobConfiguration;

import io.spring.batch.cBioPortal.CBioSegmentedData;
import io.spring.batch.cBioPortal.CBioSegmentedMetaData;
import io.spring.batch.gdc.GDCSegmentedData;
import io.spring.batch.gdc.GDCSegmentedFieldMapper;

import io.spring.batch.processor.SegmentedDataProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Dixit
 */
@Configuration
public class SegmentedDataJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;


	//Dummy Resource
	//names will be actually retrieved from API Calls
	@Value("classpath*:/data/GDC/*.seg.txt")
	private Resource[] inputFiles;

	public String[] gdcRESTAPI(String project_id){
		//dummy logic
		// rest API flow logic
		//retrieve file names for segmented data if not provided
		String [] filenames = new String[3];
		filenames[0] = "dummy1";
		filenames[1] = "dummy2";
		filenames[2] = "dummy3";
		return filenames;
	}

	public MultiResourceItemReader<GDCSegmentedData> extractData(String project_id) {

		String [] filenames = gdcRESTAPI(project_id);

		MultiResourceItemReader<GDCSegmentedData> reader = new MultiResourceItemReader<>();
		reader.setDelegate(gdcSegmentedDataReader());
		reader.setResources(inputFiles);
		return reader;
	}

	@Bean
	public FlatFileItemReader<GDCSegmentedData> gdcSegmentedDataReader() {
		FlatFileItemReader<GDCSegmentedData> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);

		DefaultLineMapper<GDCSegmentedData> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

		tokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_TAB);
		tokenizer.setNames(new String[] {"id", "chromosome", "start", "end","num_probes","seg_mean"});

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new GDCSegmentedFieldMapper());
		lineMapper.afterPropertiesSet();

		reader.setLineMapper(lineMapper);

		return reader;
	}



	@Bean
	public SegmentedDataProcessor processSegmentedData() { return new SegmentedDataProcessor(); }

	@Bean
	public FlatFileHeaderCallback segmentedDataHeader(){
		return new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				String header="ID\tchrom\tloc.start\tloc.end\tnum.mark\tseg.mean";
				writer.write(header);

			}
		};
	}

	public FieldExtractor<CBioSegmentedData> createFieldExtractor(){
		BeanWrapperFieldExtractor<CBioSegmentedData> ext = new BeanWrapperFieldExtractor<>();
		ext.setNames(new String[]{"id", "chromosome", "start", "end","num_mark","seg_mean"});
		return  ext;
	}


	@Bean
	public FlatFileItemWriter<CBioSegmentedData> segmentedDataWriter() {

		FlatFileItemWriter<CBioSegmentedData> dataWriter = new FlatFileItemWriter<>();

		dataWriter.setAppendAllowed(true);
		dataWriter.setHeaderCallback(segmentedDataHeader());
		dataWriter.setLineSeparator(System.lineSeparator());

		DelimitedLineAggregator<CBioSegmentedData> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter("\t");
		FieldExtractor<CBioSegmentedData> fe = createFieldExtractor();
		lineAggregator.setFieldExtractor(fe);
		dataWriter.setLineAggregator(lineAggregator);
		dataWriter.setResource(new FileSystemResource("/Users/Dixit/Documents/GSoC/github/cBioPortal-GSoC/sampleBatchDemo/src/main/resources/data/cBioPortal/birc_tcga_data_cna.seg"));

		return dataWriter;



	}


	@Bean
	public Step extractAndTransformStep() {
		return stepBuilderFactory.get("extractAndTransformStep")
				.<GDCSegmentedData, CBioSegmentedData>chunk(100)
				.reader(extractData("TCGA-BIRC"))
				.processor(processSegmentedData())
				.writer(segmentedDataWriter())
				.build();
	}


	@Bean
	public Job segmentedDatajob() {
		return jobBuilderFactory.get("segmentedDatajob")
				.start(extractAndTransformStep())
				.build();
	}
}
