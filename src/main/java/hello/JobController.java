/**
 * 
 */
package hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/job/importUserJob")
public class JobController {

	private Job importUserJob;
	private JobLauncher jobLauncher;
	
	@Autowired
	public JobController(Job importUserJob, JobLauncher jobLauncher)
	{
		this.importUserJob = importUserJob;
		this.jobLauncher = jobLauncher;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> run()
	throws Exception
	{
		//Hack to run with the same run.id as the initial job run
		Map<String, JobParameter> parameters = new HashMap<String,JobParameter>();
		parameters.put("run.id", new JobParameter(1L));
		jobLauncher.run(importUserJob,new JobParameters(parameters));
		return ResponseEntity.ok().build();
	}
	
}
