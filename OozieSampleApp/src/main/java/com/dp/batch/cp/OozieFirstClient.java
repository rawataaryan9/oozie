package com.dp.batch.cp;

import com.dp.batch.cp.config.OozieConfig;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by aman on 20/10/16.
 */
public class OozieFirstClient {

    private static Logger LOG = LoggerFactory.getLogger(OozieFirstClient.class);
    private static String NAMENODE = "nameNode";
    private static String JOBTRACKER = "jobTracker";
    private static String OUTPUT_DIR = "outputDir";
    private static String QUEUE_NAME = "queueName";
    private static String EXAMPLES_ROOT = "examplesRoot";

    public static void main(String[] args) {

        LOG.info("Oozie config file path is "+args[0]);
        OozieConfig config = new OozieConfig(args[0]);
        //oozie rest end point url
        JSONObject configObj = config.getParamsMap();

        OozieClient oozieClient = new OozieClient(configObj.getString("OOZIE_URL"));
        Properties props = oozieClient.createConfiguration();

        //Adding oozie workflow path
        props.setProperty(OozieClient.APP_PATH,configObj.getString("OOZIE_WORKFLOW_PATH"));
        props.setProperty(NAMENODE,configObj.getString("OOZIE_NAMENODE"));
        //props.setProperty(JOBTRACKER,configObj.getString("OOZIE_JOBTRACKER"));
        props.setProperty(OUTPUT_DIR,configObj.getString("OOZIE_OUTPUT_DIR"));
        props.setProperty(QUEUE_NAME,configObj.getString("OOZIE_QUEUE_NAME"));
        props.setProperty(EXAMPLES_ROOT,configObj.getString("OOZIE_EXAMPLES_ROOT"));

        String jobId = null;

        try {
             jobId = oozieClient.run(props);
             while(oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING){
                Thread.sleep(10000);
             }
            LOG.info("Workflow Job Submitted");

            LOG.info("Workflow Job Completed");
            LOG.info("Workflow Job state : "+oozieClient.getJobInfo(jobId));

        } catch (OozieClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }


}
