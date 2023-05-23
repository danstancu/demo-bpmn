package net.stancu.bpmn;

import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class BPMNService {

    @Inject
    Application application;

    public void test() {
        Process<? extends Model> demoProcess = application.get(Processes.class).processById("demo");
        Model model = demoProcess.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("a", "A");
        parameters.put("b", "B");
        model.fromMap(parameters);
        ProcessInstance<? extends Model> instance = demoProcess.createInstance(model);
        instance.start();
        int status = instance.status();
        Model result = instance.variables();
        Map<String, Object> stringObjectMap = result.toMap();
        System.out.println("tested");
    }
}
