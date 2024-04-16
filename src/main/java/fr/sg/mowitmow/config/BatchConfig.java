package fr.sg.mowitmow.config;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import fr.sg.mowitmow.listner.JobCompletionListener;
import fr.sg.mowitmow.tasklet.MowerMoverTasklet;

@Configuration
public class BatchConfig {
	
	@Autowired
	private MowerMoverTasklet mowerMoverTasklet;
	
	@Bean
	public Job processJob(final JobRepository jobRepository) {
		return new JobBuilder("processJob", jobRepository)
				.listener(listener())
				.flow(moveMowerStep(jobRepository, transactionManager())).end().build();
	}
	
	@Bean
	public Step moveMowerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("deleteFilesInDir", jobRepository)
					.tasklet(mowerMoverTasklet, transactionManager)
					.build();
	}
	
	
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
	
	@Bean
    public JobRepository jobRepository() throws Exception {
		JobRepositoryFactoryBean jobrepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobrepositoryFactoryBean.setDataSource(dataSource());
        jobrepositoryFactoryBean.setTransactionManager(transactionManager());
        jobrepositoryFactoryBean.afterPropertiesSet();
        return jobrepositoryFactoryBean.getObject();
    }
	
	@Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("/org/springframework/batch/core/schema-h2.sql")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
	
}
