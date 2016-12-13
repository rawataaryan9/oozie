package com.dp.batch.cp;

import com.dp.batch.cp.config.OozieConfig;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by aman on 21/10/16.
 */
public class TestConfig {

    public static void main(String[] args) {
        OozieConfig config = new OozieConfig("src/main/resources/config.json");

        JSONObject paramsMap = config.getParamsMap();
        System.out.println(paramsMap.getString("OOZIE_URL"));
        System.out.println(paramsMap.getString("OOZIE_WORKFLOW_PATH"));
        System.out.println(paramsMap.getString("OOZIE_NAMENODE"));
        System.out.println(paramsMap.getString("OOZIE_JOBTRACKER"));

    }

}
