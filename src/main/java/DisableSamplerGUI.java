import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.*;
import java.awt.*;

    /**
     ZubovaDP - 2024
     */

    public class DisableSamplerGUI extends AbstractSamplerGui {
        private JTextField ObjectNameForDisable = null;
        private ButtonGroup OperationType = new ButtonGroup();
        public DisableSamplerGUI(){
            init();
        }

        @Override
        public void configure(TestElement element) {
            super.configure(element);
            ObjectNameForDisable.setText(element.getPropertyAsString(DisableSampler.pObjectNameForDisable));
        }

        private void init() {
            JPanel mainPanel = new JPanel(new GridBagLayout());
            ObjectNameForDisable = new JTextField(20);
            JLabel lObjectNameForDisable = new JLabel("ObjectNameForDisable");

            JRadioButton EnableAllButton = new JRadioButton("Enable all objects", false);
            EnableAllButton.setActionCommand("Enable all objects");
            OperationType.add(EnableAllButton);
            JRadioButton DisableAllButton = new JRadioButton("Disable all objects", true);
            DisableAllButton.setActionCommand("Disable all objects");
            OperationType.add(DisableAllButton);
            JRadioButton ToggleOfAvailabilityButton = new JRadioButton("Toggle of availability", true);
            ToggleOfAvailabilityButton.setActionCommand("Toggle of availability");
            OperationType.add(ToggleOfAvailabilityButton);


            GroupLayout layout = new GroupLayout(mainPanel);
            mainPanel.setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(lObjectNameForDisable)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(ObjectNameForDisable))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(EnableAllButton)
                                    .addComponent(DisableAllButton)
                                    .addComponent(ToggleOfAvailabilityButton)));
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(lObjectNameForDisable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ObjectNameForDisable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup()
                                    .addComponent(EnableAllButton)
                                    .addComponent(DisableAllButton)
                                    .addComponent(ToggleOfAvailabilityButton)));

            //toggleOfAvailability.add(ltoggleOfAvailability);
            //mainPanel.add(ltoggleOfAvailability);
           // enableAll.add(lenableAll);
            //mainPanel.add(lenableAll);
           // objectName.add(lobjectName);
            //mainPanel.add(lobjectName);
            add(mainPanel);
        }

        @Override
        public TestElement createTestElement() {
            // Создаем соответствующий сэмплер
            TestElement sampler = new DisableSampler();
            modifyTestElement(sampler);
            return sampler;
        }

        @Override
        public String getLabelResource() {
            return this.getClass().getSimpleName();
        }

        @Override
        public void modifyTestElement(TestElement sampler) {
            super.configureTestElement(sampler);
            if(sampler instanceof DisableSampler){
                DisableSampler testSampler = (DisableSampler) sampler;
                testSampler.setProperty(DisableSampler.pObjectNameForDisable,ObjectNameForDisable.getText());
                testSampler.setProperty(DisableSampler.pOperationType,OperationType.getSelection().getActionCommand().toString());
            }
        }

        @Override
        public String getStaticLabel() {
            // Устанавливаем отображаемое имя
            return "Disable sampler (GA)";
        }

        private void initFields(){
            ObjectNameForDisable.setText("");
        }

        @Override
        public void clearGui() {
            super.clearGui();
            initFields();
        }
    }