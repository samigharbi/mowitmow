package fr.sg.mowitmow.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

	@Value("${file.input}")
	private String fileInput;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

	@RequestMapping("/invokejob")
	public String handle() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addString("fileName", fileInput).toJobParameters();

		jobLauncher.run(processJob, jobParameters);

		return "Batch job has been invoked";
	}
}
