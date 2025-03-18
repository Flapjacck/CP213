package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Uses GUI widgets and listeners to illustrate the use of inner classes.
 *
 * @author your name here
 * @version 2022-07-09
 */
@SuppressWarnings("serial")
public class InnerClassPanel extends JPanel {

    /**
     * An inner class that accesses the button.
     */
    private class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    final JButton source = (JButton) e.getSource();
	    InnerClassPanel.this.buttonPush = source.getText();
	    InnerClassPanel.this.label.setText(InnerClassPanel.this.buttonPush);
	    System.out.println(InnerClassPanel.this.buttonPush);
	}
    }

    /**
     * An inner class that accesses the check boxes. Updates the checkBoxesSelected
     * ArrayList depending on whether the matching check box is checked or not.
     * Requires examining the state change of the ItemEvent was SELECTED or
     * DESELECTED. Selected items are added to the checkBoxesSelected ArrayList,
     * deselected items are removed from the checkBoxesSelected ArrayList.
     */
    private class CheckBoxListener implements ItemListener {

	@Override
	public void itemStateChanged(final ItemEvent e) {

	    // your code here
	    // get the source of the event
            JCheckBox source = (JCheckBox) e.getSource();
            // if checked, add to list
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkBoxesSelected.add(source.getText());
            } else { // if unchecked, remove from list
                checkBoxesSelected.remove(source.getText());
            }
            // update label and print to console
            label.setText(CheckBoxesSelectedToString());
            System.out.println(CheckBoxesSelectedToString());

	}
    }

    /**
     * An inner class that accesses the check boxes.
     */
    private class RadioButtonListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
	    // get the selected radio button
            JRadioButton source = (JRadioButton) e.getSource();
            // store the selected button text
            radioButtonSet = source.getText();
            // update label and print to console
            label.setText(radioButtonSet);
            System.out.println(radioButtonSet);

	}
    }

    /**
     * An inner class that accesses the slider.
     */
    private class SliderListener implements ChangeListener {

	@Override
	public void stateChanged(final ChangeEvent e) {
	    final JSlider source = (JSlider) e.getSource();

	    if (!source.getValueIsAdjusting()) {
		// Verifies that the slider has stopped moving.

		// your code here
		// store slider value
                sliderSet = source.getValue();
                // update label and print to console
                label.setText("Slider Value: " + sliderSet);
                System.out.println("Slider Value: " + sliderSet);

	    }
	}
    }

    /**
     * An inner class that accesses the spinner.
     */
    private class SpinnerListener implements ChangeListener {

	@Override
	public void stateChanged(final ChangeEvent e) {

	    // your code here
	    // store the new value from spinner
            spinnerSet = spinner.getValue().toString();
            // update label and print to console
            label.setText("Spinner Value: " + spinnerSet);
            System.out.println("Spinner Value: " + spinnerSet);

	}
    }

    /**
     * An inner class that accesses the text field.
     */
    private class TextFieldListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

	    // your code here
	    // store the text entered in the field
            textEntry = textField.getText();
            // update label and print to console
            label.setText("Text: " + textEntry);
            System.out.println("Text: " + textEntry);

	}
    }

    // Constants
    private static final int START = 0;
    private static final int END = 100;
    private static final int INC = 5;
    private static final String[] MONTH_STRINGS = new DateFormatSymbols().getMonths();
    private static final SpinnerListModel MONTH_MODEL = new SpinnerListModel(MONTH_STRINGS);

    // Attributes
    private String buttonPush = "";
    private String radioButtonSet = "";
    private int sliderSet = START;
    private String spinnerSet = "";
    private String textEntry = "";

    // GUI elements
    private final JButton button = new JButton("Push Me");
    private final ArrayList<String> checkBoxesSelected = new ArrayList<>();
    private final JCheckBox ketchup = new JCheckBox("Ketchup");
    private final JLabel label = new JLabel();
    private final JCheckBox mustard = new JCheckBox("Mustard");
    private final JCheckBox onions = new JCheckBox("Onions");
    private final JSlider slider = new JSlider(JSlider.HORIZONTAL, START, END, INC);
    private final JSpinner spinner = new JSpinner(MONTH_MODEL);
    private final ButtonGroup starGroup = new ButtonGroup();
    private final JRadioButton starTrek = new JRadioButton("Star Trek");
    private final JRadioButton starWars = new JRadioButton("Star Wars");
    private final JTextField textField = new JTextField();

    /**
     * The panel constructor. Lays out the GUI elements then applies their
     * listeners.
     */
    public InnerClassPanel() {
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Lays out the panel GUI widgets in order from top to bottom.
     */
    private void layoutView() {
	// Add the radio buttons to the same button group.
	// This is a logical grouping, not a visible grouping.
	this.starGroup.add(this.starTrek);
	this.starGroup.add(this.starWars);
	// Define the panel border.
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	// Uses the GridLayout to place the GUI widgets in 17 rows and 1 column.
	this.setLayout(new GridLayout(17, 1, 5, 5));
	// Add the individual widgets to the panel.
	this.add(new JLabel("Button"));
	this.add(this.button);
	this.add(new JLabel("Slider"));
	this.add(this.slider);
	this.add(new JLabel("Text Field"));
	this.add(this.textField);
	this.add(new JLabel("Spinner"));
	this.add(this.spinner);
	this.add(new JLabel("Radio Buttons"));
	this.add(this.starTrek);
	this.add(this.starWars);
	this.add(new JLabel("Check Boxes"));
	this.add(this.mustard);
	this.add(this.onions);
	this.add(this.ketchup);
	this.add(new JSeparator(SwingConstants.HORIZONTAL));
	this.add(this.label);
    }

    /**
     * Assigns listeners to the panel GUI widgets. Each listener is an instantiation
     * of an inner class.
     */
    private void registerListeners() {
	this.button.addActionListener(new ButtonListener());

	// your code here
	// attach check box listeners
        this.mustard.addItemListener(new CheckBoxListener());
        this.onions.addItemListener(new CheckBoxListener());
        this.ketchup.addItemListener(new CheckBoxListener());
        // attach radio button listeners
        this.starTrek.addActionListener(new RadioButtonListener());
        this.starWars.addActionListener(new RadioButtonListener());
        // attach slider listener
        this.slider.addChangeListener(new SliderListener());
        // attach spinner listener
        this.spinner.addChangeListener(new SpinnerListener());
        // attach text field listener
        this.textField.addActionListener(new TextFieldListener());

    }

    /**
     * @return a String version of the checkBoxesSelected ArrayList attribute
     */
    public String CheckBoxesSelectedToString() {
	return Arrays.toString(this.checkBoxesSelected.toArray());
    }

    /**
     * @return this.buttonPush
     */
    public String getButtonPush() {
	return this.buttonPush;
    }

    /**
     * @return this.checkBoxesSelected
     */
    public ArrayList<String> getCheckBoxesSelected() {
	return this.checkBoxesSelected;
    }

    /**
     * @return this.radioButtonSet
     */
    public String getRadioButtonSet() {
	return this.radioButtonSet;
    }

    /**
     * @return this.sliderSet
     */
    public int getSliderSet() {
	return this.sliderSet;
    }

    /**
     * @return this.spinnerSet
     */
    public String getSpinnerSet() {
	return this.spinnerSet;
    }

    /**
     * @return this.textEntry
     */
    public String getTextEntry() {
	return this.textEntry;
    }

}
