package com.pzeeman.weatherstations.config;


import com.pzeeman.weatherstations.model.Station;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@ContextConfiguration(classes = { BatchConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TestBatchConfig {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private ItemReader<Station> itemReader;

    @AfterAll
    public void cleanup() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters() {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addString("JobID", String.valueOf(System.currentTimeMillis()));
        return paramsBuilder.toJobParameters();
    }

    @Test
    public void given_whenJobExecuted_thenCompleted() throws Exception {
        // given

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();

        // then
        assertEquals(actualJobInstance.getJobName(),"readCSVFileJob");
        assertEquals(actualJobExitStatus.getExitCode(), "COMPLETED");
    }

    @Test
    public void given_whenStepLaunched_thenStepCompleted() throws Exception {
        // given

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step");
        List<StepExecution> actualStepExecutions = (List)jobExecution.getStepExecutions();
        StepExecution actualStepStepExecution = actualStepExecutions.get(0);

        // then
        assertEquals(actualStepStepExecution.getExitStatus(), ExitStatus.COMPLETED);
    }

    /***
     * @TODO - Write more tests for the FlatFileItemReader, DataSource, ItemProcessor, JdbcBatchItemWriter, LineMapper
     */

}
