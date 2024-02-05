import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.sampler.TestAction;
import org.apache.jmeter.sampler.DebugSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jorphan.collections.HashTree;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

    /**
     ZubovaDP - 2024
     */

    public class DisableSampler extends AbstractSampler {
        public final static String pObjectNameForDisable = "function";
        public final static String pOperationType = "function1";

        @Override
        public SampleResult sample(Entry entry) {
            SampleResult result = new SampleResult();
            result.sampleStart();
            /*// Выводим результат возврата метода функции, введенного в графический интерфейс
            int num;
            int num1;
            try{
                num = Integer.valueOf(String.valueOf(this.getProperty(ptoggleOfAvailability)));
                num1 = Integer.valueOf(String.valueOf(this.getProperty(penableAll)));
            } catch (Exception e){
                return null;
            }
            System.out.println(String.valueOf(num+1));
            System.out.println(String.valueOf(num1+1));*/

            Map map = new HashMap();
            GuiPackage guiInstance = GuiPackage.getInstance();

            HashTree plan = guiInstance.getTreeModel().getTestPlan();
            //log.info(plan.toString());

            String objectName = String.valueOf(this.getProperty(pObjectNameForDisable));
            String message = "Объект: "+objectName+ "\n\n";
            String message1 = "";
            int pos = 0;


            List testactionlist = guiInstance.getTreeModel().getNodesOfType(HTTPSamplerBase.class);
            //log.info(testactionlist.toString());
            for (Object HTTPSamplerBase : testactionlist) {
                JMeterTreeNode testActionSampler = (JMeterTreeNode) HTTPSamplerBase;
                if (testActionSampler.getName().equals(objectName)){
                    /*System.out.println("pOperationType = "+ String.valueOf(this.getProperty(pOperationType)));
                    System.out.println("equals(\"Enable all objects\") = "+ String.valueOf(this.getProperty(pOperationType).equals("Enable all objects")));
                    System.out.println("equals(\"Disable all objects\") = "+ String.valueOf(String.valueOf(this.getProperty(pOperationType)).equals("Disable all objects")));
                    System.out.println("Not equals(\"Disable all objects\") = "+ String.valueOf(!this.getProperty(pOperationType).equals("Disable all objects")));
                    System.out.println("equals(\"Toggle of availability\") = "+ String.valueOf(this.getProperty(pOperationType)).equals("Toggle of availability"));
                    */
                    if (String.valueOf(this.getProperty(pOperationType)).equals("Toggle of availability")){
                        //System.out.println("testActionSampler.isEnabled() = "+ String.valueOf(testActionSampler.isEnabled()));
                        testActionSampler.setEnabled(!testActionSampler.isEnabled());
                        //System.out.println("testActionSampler.isEnabled() new = "+ String.valueOf(testActionSampler.isEnabled()));
                    }
                    else{
                        /*System.out.println("   ");
                        System.out.println("equals(\"Enable all objects\") = "+ String.valueOf(this.getProperty(pOperationType).equals("Enable all objects")));
                        System.out.println("equals(\"Disable all objects\") = "+ String.valueOf(this.getProperty(pOperationType).equals("Disable all objects")));
                        System.out.println("Not equals(\"Disable all objects\") = "+ String.valueOf(!this.getProperty(pOperationType).equals("Disable all objects")));
                        */
                        testActionSampler.setEnabled(String.valueOf(this.getProperty(pOperationType)).equals("Enable all objects"));
                        /*System.out.println("   ");
                        System.out.println("   ");*/
                    }
                    //map.put(testActionSampler.isEnabled(), map.getOrDefault(testActionSampler.isEnabled(), 0) + 1);
                    map.put(testActionSampler.isEnabled(), (int) map.getOrDefault(testActionSampler.isEnabled(), 0) + 1);
                }
            }

            message1 = map.toString().length() == 2 ? "{Не найдено!}":map.toString();
            message = message + "Тип HTTPSampler: " + message1.substring(1,message1.length()-1) + "\n";
            map.clear();


            testactionlist = guiInstance.getTreeModel().getNodesOfType(TestAction.class);
            for (Object testAction : testactionlist) {
                JMeterTreeNode testActionSampler = (JMeterTreeNode) testAction;
                if (testActionSampler.getName().equals(objectName)){
                    if (String.valueOf(this.getProperty(pOperationType)).equals("Toggle of availability")){
                        testActionSampler.setEnabled(!testActionSampler.isEnabled());
                    }
                    else{
                        testActionSampler.setEnabled(String.valueOf(this.getProperty(pOperationType)).equals("Enable all objects"));
                    }
                    map.put(testActionSampler.isEnabled(), (int) map.getOrDefault(testActionSampler.isEnabled(), 0) + 1);
                }
            }
            message1 = map.toString().length() == 2 ? "{Не найдено!}":map.toString();
            message = message + "Тип testAction: " + message1.substring(1,message1.length()-1) + "\n";
            map.clear();


            testactionlist = guiInstance.getTreeModel().getNodesOfType(DebugSampler.class);
            for (Object DebugSampler : testactionlist) {
                JMeterTreeNode testActionSampler = (JMeterTreeNode) DebugSampler;
                if (testActionSampler.getName().equals(objectName)){
                    if (String.valueOf(this.getProperty(pOperationType)).equals("Toggle of availability")){
                        testActionSampler.setEnabled(!testActionSampler.isEnabled());
                    }
                    else{
                        testActionSampler.setEnabled(String.valueOf(this.getProperty(pOperationType)).equals("Enable all objects"));
                    }
                    map.put(testActionSampler.isEnabled(), (int) map.getOrDefault(testActionSampler.isEnabled(), 0) + 1);

                }
            }

            message1 = map.toString().length() == 2 ? "{Не найдено!}":map.toString();
            message = message + "Тип DebugSampler: " + message1.substring(1,message1.length()-1) + "\n";
            map.clear();

            GuiPackage.showMessage(message,"Изменение скрипта",1);
            //GuiPackage.showMessage("Объект: "+objectName+ "\nВидимость: " + smap.substring(1,smap.length()-1) ,"Изменение скрипта",1);
            //ctx.getEngine().askThreadsToStop()*/
            /*System.out.println(String.valueOf(this.getProperty(pObjectNameForDisable)));
            System.out.println(String.valueOf(this.getProperty(pOperationType)));*/
            result.setStopTest(true);
            result.sampleEnd();
            result.setSuccessful(true);
            return result;
        }
    }
