package com.pzeeman.weatherstations.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.pzeeman.weatherstations.model.Station;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
// https://github.com/avinash28196/SpringBatch-LoadingCSVFileToH2Database
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classPath:/input/eng-climate-summary.csv")
    private Resource inputResource;

    /**
     * JobBuilderFactory(JobRepository jobRepository)  Convenient factory for a JobBuilder which sets the JobRepository automatically
     */
    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    /**
     * StepBuilder which sets the JobRepository and PlatformTransactionManager automatically
     */

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<Station, Station>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    /**
     * Prints the Logs in the console.
     * @return
     */

    @Bean
    public ItemProcessor<Station, Station> processor() {
        return new DBLogProcessor();
    }

    /**
     * FlatFileItemReader<T> Restartable ItemReader that reads lines from input setResource(Resource).
     * @return
     */

    @Bean
    public FlatFileItemReader<Station> reader() {
        FlatFileItemReader<Station> itemReader = new FlatFileItemReader<Station>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }



    /**
     * The data source object is defined and created depending upon the type of database we use.
     * In this example we are using H2 databse so we 'schema-h2.sql'  for example if we are using mysql database
     * we will be using 'schema-mysql.sql'
     *
     * schema-drop-h2.sql will drop the batch related jobs
     *
     * schema-h2.sql is responsible for creating a schema related to the batch job instances in h2 database.
     * BATCH_STEP_EXECUTION_CONTEXT
     * BATCH_JOB_EXECUTION_CONTEXT
     * BATCH_STEP_EXECUTION
     * BATCH_JOB_EXECUTION_PARAMS
     * BATCH_JOB_EXECUTION
     * BATCH_JOB_INSTANCE
     *
     * Station.sql will create HOTLE table in h2 database
     *
     */

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();

        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:stations.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    /**
     * The itemWriter object will set JDBC connection and sql statement is prepared for the batch action we want to perform in the database.
     * A convenient implementation for providing BeanPropertySqlParameterSource when the item has JavaBean properties that correspond to names used for parameters in the SQL statement.
     *
     */

    @Bean
    public JdbcBatchItemWriter<Station> writer() {

        JdbcBatchItemWriter<Station> itemWriter = new JdbcBatchItemWriter<Station>();

        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO Stations (STATION_NAME,PROVINCE,STATION_DATE,MEAN_TEMP,HIGHEST_MONTHLY_MAXI_TEMP,LOWEST_MONTHLY_MIN_TEMP) VALUES ( :Station_Name, :Province, :Station_Date, :Mean_Temp, :Highest_Monthly_Maxi_Temp, :Lowest_Monthly_Min_Temp)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Station>());
        return itemWriter;
    }

    /**
     * the lineMapper for mapping lines (strings) to domain objects typically used to map lines read from a file to domain objects on a per line basis.
     * lineTokenizer to split string obtained typically from a file into tokens. In our example we are using DelimitedLineTokenizer that is because we are using csv file.
     * fieldSetMapper to map data obtained from a FieldSet into an object.
     *
     */

    @Bean
    public LineMapper<Station> lineMapper() {
        DefaultLineMapper<Station> lineMapper = new DefaultLineMapper<Station>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Station> fieldSetMapper = new BeanWrapperFieldSetMapper<Station>();

        lineTokenizer.setNames(new String[]{"station_name", "province", "station_Date", "meanTemp", "highestMonthlyMaxTemp", "lowestMonthlyMinTemp" });
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3, 4, 5});
        fieldSetMapper.setTargetType(Station.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }


}
